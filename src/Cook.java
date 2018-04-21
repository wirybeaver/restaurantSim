import entity.Machine;
import entity.OrderNode;
import utils.Log;
import utils.LogQueue;
import utils.LogUtil;

import java.util.HashSet;

public class Cook implements Runnable{
    private final int cookId;
    private final OrderQueue ordq;
    private final MachineQueue mchq;
    private HashSet<Integer> unDoneJobs;
    public Cook(int cookId, OrderQueue ordq, MachineQueue mchq) {
        this.cookId = cookId;
        this.ordq = ordq;
        this.mchq = mchq;
        this.unDoneJobs = new HashSet<Integer>(4);
    }

    @Override
    public void run() {
        try{
            LogQueue log = LogUtil.getInstance();
            while(true){
                unDoneJobs.clear();
                OrderNode ord = ordq.takeOrder(cookId);
                for(int i = 0; i<4; i++){
                    if(!ord.isDone(i)){unDoneJobs.add(i);}
                }
//                log.addInfo(new Log(ord.getTime(), "Time "+ ord.getTime()+": Cook "+ cookId
//                        +" is serving for Diner "+ord.getDinerId()+"\n"));
                System.out.format("Time %d: Cook %d is serving for Diner %d\n", ord.getTime(), cookId, ord.getDinerId());
                while(!unDoneJobs.isEmpty()){
                    Machine machine = mchq.getMachine(unDoneJobs);
                    int foodId = machine.getId();
                    int time = Math.max(machine.getTime(), ord.getTime());
//                    log.addInfo(new Log(time, "Time "+ time + ": Machine " +machine.getName()+" is used by Cook " +cookId
//                            + " for serving Diner " +ord.getDinerId()+"\n"));
                   System.out.format("Time %d: Machine %s is used by Cook %d for serving Diner %d\n",
                           time, machine.getName(), cookId, ord.getDinerId());
                    time += machine.getWorkGap();
                    Thread.sleep(machine.getWorkGap());
                    ord.setTime(time);
                    machine.setTime(time);
                    ord.decreaseByOne(foodId);
                    if(ord.isDone(foodId)){
                        unDoneJobs.remove(foodId);
                    }
                    mchq.releaseMachine(machine);
                }
                ordq.offerFood(ord);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
