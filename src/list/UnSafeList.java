package list;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class UnSafeList {
    public static void main(String[] args) throws InterruptedException {
//        CopyOnWriteArrayList
        List<Integer> list=new ArrayList();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                list.add(3);
                System.out.println(list);
            }).start();
        }
    }
}
