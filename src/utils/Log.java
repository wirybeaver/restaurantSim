package utils;

public class Log implements Comparable<Log>{
    int time;
    String info;

    public Log(int time, String info) {
        this.time = time;
        this.info = info;
    }

    public int getTime() {
        return time;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public int compareTo(Log o) {
        return this.time-o.time;
    }
}
