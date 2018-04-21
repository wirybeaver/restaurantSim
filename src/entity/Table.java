package entity;

public class  Table implements Comparable<Table>{
    private final int id;
    private int time = 0;
    public Table(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int compareTo(Table o) {
        return this.time - o.time;
    }
}
