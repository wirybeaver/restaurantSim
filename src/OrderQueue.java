import entity.OrderNode;

import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderQueue {
    private volatile int size;
    private final PriorityQueue<OrderNode> waitingjobs;
    private final HashMap<Integer, OrderNode> donejobs;
    private Lock lock;
    private Condition OkToEat;
    private Condition OkToCook;
    OrderQueue(int size){
        this.size = size;
        lock = new ReentrantLock();
        OkToEat = lock.newCondition();
        OkToCook = lock.newCondition();
        waitingjobs = new PriorityQueue<>();
        donejobs = new HashMap<>();
    }

    public void submitOrder(OrderNode order) throws InterruptedException {
        lock.lockInterruptibly();
        try{
            waitingjobs.offer(order);
            OkToCook.signal();
            OkToEat.await();
        }
        finally{
            lock.unlock();
        }
    }

    public OrderNode takeOrder(int cookId) throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while(waitingjobs.isEmpty()){
//                System.out.format("Cook %d is waiting \n", cookId);
                OkToCook.await();
            }
//            System.out.format("cook %d take order \n", cookId);
            return waitingjobs.poll();
        }
        finally{
            lock.unlock();
        }
    }

    public void offerFood(OrderNode order) throws InterruptedException {
        lock.lockInterruptibly();
        try{
            donejobs.put(order.getDinerId(), order);
            OkToEat.signalAll();
        }
        finally {
            lock.unlock();
        }
    }

    public OrderNode takeFood(int dinerId) throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while(!donejobs.containsKey(dinerId)){
                OkToEat.await();
            }
            size--;
            return donejobs.remove(dinerId);
        }
        finally{
            lock.unlock();
        }
    }

    public boolean snoring(){
        return size == 0;
    }
}



