import entity.OrderNode;
import entity.Table;
import utils.Log;
import utils.LogQueue;
import utils.LogUtil;

/**
 * Created by Administrator on 2018/4/15.
 */
public class Diner implements Runnable{
    private TableQueue tblq;
    private OrderQueue ordq;
    private int dinerId;
    private OrderNode order;
    private int arriveTime;

    public Diner(TableQueue tblq, OrderQueue ordq, int dinerId, int arriveTime, int hambuger, int frier, int coke, int iceream){
        this.dinerId = dinerId;
        this.tblq = tblq;
        this.ordq = ordq;
        this.arriveTime = arriveTime;
        order = new OrderNode(dinerId, 0, hambuger, frier, coke, iceream);
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
            log.addInfo(new Log(time, "Diner "+dinerId+" arrive at time "+arriveTime
                    +", seat on Table "+table.getId()+" at time "+time+"\n"));
//            System.out.format("Diner %d arrive at time %d, seat on Table %d at time %d\n", dinerId, arriveTime, table.getId(), time);
            order.setTime(time);
            ordq.submitOrder(order);
            time = ordq.takeFood(dinerId).getTime();
            log.addInfo(new Log(time, "Diner "+dinerId+" eat at time " +time+"\n"));
//            System.out.format("Diner %d eat at time %d\n", dinerId, time);
            time += 30;
            log.addInfo(new Log(time, "Diner "+dinerId+" leave at time " +time+"\n"));
//            System.out.format("Diner %d leave at time %d\n", dinerId, time);
            table.setTime(time);
            tblq.leave(table);
            if(ordq.snoring()){
                log.printAscendByTime();
                System.out.println("*---------- Efficiency Result ----------*");
                System.out.format("Last Diner %d leave at time %d\n", dinerId, time);
                System.exit(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
