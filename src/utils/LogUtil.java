package utils;

/**
 * Created by Administrator on 2018/4/20.
 */
public class LogUtil {
    // Singleton Design pattern
    private final static LogQueue logq = new LogQueue();
    public static LogQueue getInstance(){
        return logq;
    }
}
