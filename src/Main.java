import com.sun.org.apache.xml.internal.security.Init;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
    static int n = 10;

    public static void main(String[] args) {
        //Data set 1 for
        //  50% of all Process working finish in (10) milisec
        //  30% of all Process working finish in (30) milisec
        //  20% of all Process working finish in (50) milisec
        ArrayList<Integer> data_set1 = new ArrayList<>();

        //Data set 2 for
        //  30% of all Process working finish in (10) milisec
        //  40% of all Process working finish in (30) milisec
        //  30% of all Process working finish in (50) milisec
        ArrayList<Integer> data_set2 = new ArrayList<>();

        //Data set 3 for
        //  10% of all Process working finish in (10) milisec
        //  20% of all Process working finish in (30) milisec
        //  70% of all Process working finish in (50) milisec
        ArrayList<Integer> data_set3 = new ArrayList<>();

        //Init Data_set1
        Init_Data(data_set1 , 50,10,30,30,20,50);
        System.out.println("---------------------------------------------");
        System.out.println("Data set 1 ");
        System.out.println(data_set1);
        System.out.println("---------------------------------------------");

        System.out.println("---------------------------------------------");
        System.out.println("First Come First Served (FCFS)");
        System.out.println("---------------------------------------------");
        FCFS(data_set1);

        System.out.println("---------------------------------------------");
        System.out.println("Shortest-Job-First (SJF)");
        System.out.println("---------------------------------------------");
        SJF(data_set1);

        RR(data_set1);

        //Init Data_set2
        Init_Data(data_set2 , 30,10,40,30,30,50);
        System.out.println("---------------------------------------------");
        System.out.println("Data set 2 ");
        System.out.println(data_set2);
        System.out.println("---------------------------------------------");

        System.out.println("---------------------------------------------");
        System.out.println("First Come First Served (FCFS)");
        System.out.println("---------------------------------------------");
        FCFS(data_set2);

        System.out.println("---------------------------------------------");
        System.out.println("Shortest-Job-First (SJF)");
        System.out.println("---------------------------------------------");
        SJF(data_set2);

        RR(data_set2);

        //Init Data_set2
        Init_Data(data_set3 , 10,10,20,30,70,50);
        System.out.println("---------------------------------------------");
        System.out.println("Data set 3 ");
        System.out.println(data_set3);
        System.out.println("---------------------------------------------");

        System.out.println("---------------------------------------------");
        System.out.println("First Come First Served (FCFS)");
        System.out.println("---------------------------------------------");
        FCFS(data_set3);

        System.out.println("---------------------------------------------");
        System.out.println("Shortest-Job-First (SJF)");
        System.out.println("---------------------------------------------");
        SJF(data_set3);

        RR(data_set3);
    }

    public static void FCFS(ArrayList<Integer> var){
        int Process = 0;
        int Waiting_Time = 0;

        System.out.println("Process \tBurst_Time \tWaiting_Time");
        while (Process < var.size()){
            System.out.println("Process " + Process +"\t\t" + var.get(Process) + "\t\t\t" + Waiting_Time);
            if(Process == var.size()-1) {
                Process++;
                continue;
            }
            else {
                Waiting_Time += var.get(Process);
                Process++;
            }
        }
        System.out.println("Average Time is " + Waiting_Time/var.size());
    }

    public static void SJF(ArrayList<Integer> var){
        ArrayList<Integer> tmp = var;
        Collections.sort(tmp);
        FCFS(tmp);
    }

    public static void RR(ArrayList<Integer> var){
        ArrayList<Integer> tmp = var;
        int TimeQuantum = 30;
        boolean isEnd = false;
        int Waiting_Time = 0;
        System.out.println("---------------------------------------------");
        System.out.println("Round Robin (RR)");
        System.out.println("---------------------------------------------");
        System.out.println("Process \tBurst_Time \tWaiting_Time");
        while (!isEnd) {
            isEnd = true;
            for(int i = 0 ; i < tmp.size(); i++) {
                if(tmp.get(i) >= TimeQuantum) {
                    int new_data = tmp.get(i) - TimeQuantum;
                    tmp.set(i,new_data);
                    isEnd = false;
                    Waiting_Time += TimeQuantum;
                    System.out.println("Process " + i +"\t\t" + tmp.get(i) + "\t\t\t" + Waiting_Time);
                }
                else  if(tmp.get(i) == 0){
                    continue;
                }
                else {
                    System.out.println("Process " + i +"\t\t" + tmp.get(i) + "\t\t\t" + Waiting_Time);
                    Waiting_Time += tmp.get(i);
                    var.set(i,0);
                }
            }
        }
        System.out.println("Average Time is " + Waiting_Time / var.size());
    }

    public static void Init_Data(ArrayList<Integer> var , int percent1 , int proc_finish1 ,int percent2 , int proc_finish2 ,int percent3 , int proc_finish3){
        float count_1 = 0;
        float count_2 = 0;
        float count_3 = 0;
        for(int i = 0; i < n ; i++ ){
            int ran = getRandomNumberInRange(1,3);
            switch (ran) {
                case 1 :
                    if(count_1 >= ((float)n/100)*percent1 ) {
                        ran++;
                    }
                    var.add(getRandomNumberInRange(proc_finish1-9,proc_finish1));
                    count_1++;
                    break;
                case 2:
                    if(count_2 >= ((float)n/100)*percent2 ) {
                        ran++;
                    }
                    var.add(getRandomNumberInRange(proc_finish2-10,proc_finish2));
                    count_2++;
                    break;
                case 3:
                    if(count_3 >= ((float)n/100)*percent3 ) {
                        ran++;
                    }
                    var.add(getRandomNumberInRange(proc_finish3-10,proc_finish3));
                    count_3++;
                    break;
                default: break;
            }
        }
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
