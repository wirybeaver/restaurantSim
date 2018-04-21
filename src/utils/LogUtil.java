package utils;

public class LogUtil {
    // Singleton Design pattern
    private final static LogQueue logq = new LogQueue();
    public static LogQueue getInstance(){
        return logq;
    }
}
