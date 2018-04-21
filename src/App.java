import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2018/4/19.
 */
public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
//        BufferedReader file = new BufferedReader(new FileReader(args[0]));
        BufferedReader file = new BufferedReader(new FileReader("data1.txt"));
        int dinerNum = Integer.parseInt(file.readLine().trim());
        int tableNum = Integer.parseInt(file.readLine().trim());
        int cookNum = Integer.parseInt(file.readLine().trim());

        TableQueue tblq = new TableQueue(tableNum);
        OrderQueue ordq = new OrderQueue(dinerNum);
        Diner[] diners = new Diner[dinerNum];
        MachineQueue mchq = new MachineQueue();
        for(int i = 0; i<diners.length; i++){
            StringTokenizer tkzer = new StringTokenizer(file.readLine(), " \n\t");
            diners[i] = new Diner(tblq, ordq, i, Integer.parseInt(tkzer.nextToken()), Integer.parseInt(tkzer.nextToken()),
                    Integer.parseInt(tkzer.nextToken()), Integer.parseInt(tkzer.nextToken()), Integer.parseInt(tkzer.nextToken()));
        }
        for(int i = 0; i<cookNum; i++){
            Cook cook = new Cook(i,ordq, mchq);
            Thread cookThd = new Thread(cook);
            cookThd.start();
        }

        for(int i = 0; i<diners.length; i++){
            Thread dinerThd = new Thread(diners[i]);
            dinerThd.start();
            Thread.sleep(3);
        }
    }
}
