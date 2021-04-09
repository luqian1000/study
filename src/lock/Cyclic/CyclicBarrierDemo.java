package lock.Cyclic;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class CyclicBarrierDemo {
//    public static void main(String[] args) {
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
//            System.out.println(" 召唤神龙 ");
//        });
//        for (int i = 1; i <= 7; i++) {
////            i
////            final int temp = i;
//            final int temp = i;
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName() + " \t 收集到第 " + temp + " 颗龙珠 ");
//                try {
//                    cyclicBarrier.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (BrokenBarrierException e) {
//                    e.printStackTrace();
//                }
//            }, String.valueOf(i)).start();
//            System.out.println("--");
//        }
//    }
public static void main(String[] args) throws InterruptedException {
//    long i1 = 4 >> 3;
//    System.out.println(i1);
    short s1=1;
//    s1=s1+1;
    //构造器：设置屏障放开前做的事情
    CyclicBarrier barrier3 = new CyclicBarrier(7, () -> {
        System.out.println("屏障放开，[屏障线程]先运行！");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[屏障线程]的事情做完了!");
    });
    for (int i = 0; i < 7; i++) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 等待屏障放开");
            try {
                barrier3.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "开始干活...干活结束");
        }).start();
    }
}

}
        

