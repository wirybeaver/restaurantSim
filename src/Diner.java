import entity.OrderNode;
import entity.Table;
import utils.Log;
import utils.LogQueue;
import utils.LogUtil;

import java.util.Map;

public class Diner implements Runnable{
    private TableQueue tblq;
    private OrderQueue ordq;
    private int dinerId;
    private OrderNode order;
    private int arriveTime;

    public Diner(TableQueue tblq, OrderQueue ordq, int dinerId, int arriveTime, Map<Integer, Integer> params){
        this.dinerId = dinerId;
        this.tblq = tblq;
        this.ordq = ordq;
        this.arriveTime = arriveTime;
        order = new OrderNode(dinerId, 0, params);
    }

    public int getArriveTime() {
        return arriveTime;
    }

    @Override
    public void run() {
        try {
            LogQueue log = LogUtil.getInstance();
            Table table = tblq.seat();
            int time = Math.max(arriveTime, table.getTime());
//            log.addInfo(new Log(time, "Time "+time+": Diner "+dinerId+", whose arrive time is "+arriveTime
//                    +", seat on Table "+table.getId()+"\n"));
            System.out.format("Time %d: Diner %d arrive at time %d, seat on Table %d\n", time, dinerId, arriveTime, table.getId());
            order.setTime(time);
            ordq.submitOrder(order);
            time = ordq.takeFood(dinerId).getTime();
//            log.addInfo(new Log(time, "Time "+time+": Diner "+dinerId+" eat meal\n"));
            System.out.format("Time %d: Diner %d eat meal\n", time, dinerId);
            time += 30;
            Thread.sleep(30);
//            log.addInfo(new Log(time, "Time "+time+": Diner "+dinerId+" leave\n"));
            System.out.format("Time %d: Diner %d leave\n", time, dinerId);
            table.setTime(time);
            tblq.leave(table);
            if(ordq.snoring()){
//                log.printAscendByTime();
                System.out.println("*---------- Efficiency Result ----------*");
                System.out.format("Last Diner %d leave at time %d\n", dinerId, time);
                System.exit(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
