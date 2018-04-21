import entity.Table;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TableQueue {
    private int capacity;
    private Lock lock;
    private Condition OkToSeat;
    private PriorityQueue<Table> tables;
    TableQueue(int capacity){
        this.capacity =capacity;
        lock = new ReentrantLock();
        OkToSeat = lock.newCondition();
        tables = new PriorityQueue<>();
        for(int i =0; i<capacity; i++){
            tables.offer(new Table(i));
        }
    }

    public Table seat() throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while(tables.isEmpty()){
                OkToSeat.await();
            }
            return tables.poll();
        }
        finally {
            lock.unlock();
        }
    }

    public void leave(Table tbl) throws InterruptedException {
        lock.lockInterruptibly();
        try{
            tables.offer(tbl);
            OkToSeat.signalAll();
        }
        finally {
            lock.unlock();
        }
    }
}


