package lock.prodconsume;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者 阻塞队列版
 */
public class ProdConsumeBlockQueue {
    private BlockingQueue blockingQueue;
    private boolean flag=true;

    public ProdConsumeBlockQueue(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void prod() throws InterruptedException {

        System.out.println("生产者");
        while (flag){
            if (blockingQueue.isEmpty()){
                System.out.println("生产");
                blockingQueue.add("1");
            }
        }

    }
    public void consum() throws InterruptedException {
        System.out.println("消费者");
        while (flag){
            if (!blockingQueue.isEmpty()){
                Object poll = blockingQueue.poll(2, TimeUnit.SECONDS);
                System.out.println("消费");
                System.out.println(poll);
            }
        }
    }

    public static void main(String[] args) {
        ProdConsumeBlockQueue prodConsume=new ProdConsumeBlockQueue(new ArrayBlockingQueue<String>(5));
        new Thread(new ProdsThread(prodConsume)).start();
        new Thread(new ConsumesThread(prodConsume)).start();
//        AtomicInteger
    }


}

class ProdsThread implements Runnable{
    ProdConsumeBlockQueue prodConsume=null;
    public ProdsThread(ProdConsumeBlockQueue prodConsume){
        this.prodConsume=prodConsume;
    }

    @Override
    public void run() {
        try {
//            for (int i = 0; i < 10; i++) {
                prodConsume.prod();
//            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ConsumesThread implements Runnable{
    ProdConsumeBlockQueue prodConsume=null;
    public ConsumesThread(ProdConsumeBlockQueue prodConsume){
        this.prodConsume=prodConsume;
    }

    @Override
    public void run() {
        try {
//            for (int i = 0; i < 10; i++) {
                prodConsume.consum();
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}