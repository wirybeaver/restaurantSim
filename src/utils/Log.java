package utils;

/**
 * Created by Administrator on 2018/4/20.
 */
public class Log implements Comparable{
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
    public int compareTo(Object o) {
        Log other = (Log) o;
        return this.time-other.time;
    }
}
