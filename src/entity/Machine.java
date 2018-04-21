package entity;

public class Machine implements Comparable{
    private final int id;
    private final String name;
    private int time = 0;
    private final int workGap;
    private volatile boolean  busy = false;

    public Machine(int id, String name, int workGap){
        this.id = id;
        this.name = name;
        this.workGap = workGap;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean getBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWorkGap() {
        return workGap;
    }

    @Override
    public int compareTo(Object o) {
        Machine other = (Machine) o;
        return this.time+this.workGap-other.time-other.workGap;
    }
}
