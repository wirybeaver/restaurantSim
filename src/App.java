import enums.FoodEnum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

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
        Map<Integer, Integer> params= new HashMap<>();
        FoodEnum[] fds = new FoodEnum[]{FoodEnum.HAMBUGER, FoodEnum.FRIES, FoodEnum.COKE, FoodEnum.ICECREAM};
        for(int i = 0; i<diners.length; i++){
            params.clear();
            StringTokenizer tkzer = new StringTokenizer(file.readLine(), " \n\t");
            int arriveTime = Integer.parseInt(tkzer.nextToken());
            for(int j=0; j<fds.length; j++){
                int num = Integer.parseInt(tkzer.nextToken());
                params.put(fds[j].getId(), num);
            }
            diners[i] = new Diner(tblq, ordq, i, arriveTime, params);
        }
        for(int i = 0; i<cookNum; i++){
            Cook cook = new Cook(i,ordq, mchq);
            Thread cookThd = new Thread(cook);
            cookThd.start();
        }

        for(int i = 0; i<diners.length; i++){
            Thread dinerThd = new Thread(diners[i]);
            dinerThd.start();
            Thread.sleep(diners[i].getArriveTime());
        }
    }
}
