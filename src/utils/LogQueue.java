package utils;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by Administrator on 2018/4/20.
 */
public class LogQueue {
    private final LinkedList<Log> logs = new LinkedList<>();
    public synchronized void addInfo(Log log){
        logs.add(log);
    }
    public synchronized void printAscendByTime(){
        Collections.sort(logs);
        StringBuilder sb = new StringBuilder();
        for(Log x : logs){
            sb.append(x.getInfo());
        }
        System.out.print(sb.toString());
    }
}
