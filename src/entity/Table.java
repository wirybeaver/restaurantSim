package entity;

public class  Table implements Comparable{
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
    public int compareTo(Object o) {
        Table other = (Table) o;
        return this.time - other.time;
    }
}
