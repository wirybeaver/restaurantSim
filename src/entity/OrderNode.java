package entity;

import enums.FoodEnum;

import java.util.Map;

public class OrderNode implements Comparable<OrderNode>{
    private int[] foods = new int[FoodEnum.capacity()];
    private final int dinerId;
    private final int initLoad;
    private int time;
    public OrderNode(int dinerId, int time, Map<Integer, Integer> params){
        for(Integer x : params.keySet()){
            foods[x] = params.get(x);
        }
        this.dinerId = dinerId;
        this.time = time;
        int sum = 0;
        for(int i = 0; i<foods.length; i++){
            FoodEnum fd = FoodEnum.indexOf(i);
            int taskLoad = (fd == null)? 0 : fd.getMakingTime();
            sum += foods[i]*taskLoad;
        }
        initLoad = sum;
    }

    @Override
    public int compareTo(OrderNode o) {
        return this.time+this.initLoad-o.time-o.initLoad;
    }

    public int getDinerId() {
        return dinerId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void decreaseByOne(int foodId){
//        if(foodId > 3 || foodId <0){throw new InternalError("System Error in entity.OrderNode decreaseByOne()");}
//        if(foods[foodId] >0){
            foods[foodId] --;
//        }
    }

    public boolean isDone(int foodId){
//        if(foodId > 3 || foodId <0){throw new InternalError("System Error in entity.OrderNode decreaseByOne()");}
        return foods[foodId] == 0;
    }

}
