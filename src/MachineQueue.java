import entity.Machine;
import enums.FoodEnum;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MachineQueue{
    private Machine[] machineArray;
    private PriorityQueue<Machine> idleCandidates;
    private Lock lock;
    private Condition OkToCook;
    public MachineQueue(){
        idleCandidates= new PriorityQueue<Machine>();
        lock = new ReentrantLock();
        OkToCook = lock.newCondition();
        int i = 0;
        FoodEnum[] foodEnums= FoodEnum.values();
        machineArray = new Machine[foodEnums.length];
        for(FoodEnum food : foodEnums){
            machineArray[i++] = new Machine(food.getId(), food.getName(), food.getMakingTime());
        }
    }

    public Machine getMachine(HashSet<Integer> unDonejobs) throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while(busyForAllUndoneFoods(unDonejobs)){
                OkToCook.await();
            }
            idleCandidates.clear();
            for(int x : unDonejobs){
                if(isBusy(x)){continue;}
                idleCandidates.offer(machineArray[x]);
            }
            Machine machine = idleCandidates.poll();
            machine.setBusy(true);
            return machine;
        }
        finally{
            lock.unlock();
        }
    }

    public void releaseMachine(Machine machine) throws InterruptedException {
        lock.lockInterruptibly();
        try{
            machine.setBusy(false);
            OkToCook.signalAll();
        }
        finally {
            lock.unlock();
        }
    }

    private boolean isBusy(int machineId){
        return machineArray[machineId].getBusy();
    }

    private boolean busyForAllUndoneFoods(HashSet<Integer> unDoneJobs){
        boolean ans = true;
        for(int x : unDoneJobs){
            ans &= isBusy(x);
        }
        return ans;
    }

}


















