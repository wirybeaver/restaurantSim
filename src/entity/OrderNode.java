package entity;

public class OrderNode implements Comparable{
    private final static int[] taskloads = new int[]{5, 3, 2, 1};
    private int[] foods = new int[4];
    private final int dinerId;
    private final int initLoad;
    private int time;
    public OrderNode(int dinerId, int time, int hambuger, int frier, int coke, int iceream){
        foods[0] = hambuger;
        foods[1] = frier;
        foods[2] = coke;
        foods[3] = iceream;
        this.dinerId = dinerId;
        this.time = time;
        int sum = 0;
        for(int i = 0; i<taskloads.length; i++){
            sum += foods[i]*taskloads[i];
        }
        initLoad = sum;
    }

    @Override
    public int compareTo(Object o) {
        OrderNode other = (OrderNode)o;
        return this.time+this.initLoad-other.time-other.initLoad;
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

    public boolean allDone(){
        boolean ans = true;
        for(int x : foods){
            ans = ans && x==0;
        }
        return ans;
    }

    public boolean isDone(int foodId){
//        if(foodId > 3 || foodId <0){throw new InternalError("System Error in entity.OrderNode decreaseByOne()");}
        return foods[foodId] == 0;
    }

}
