package lock.prodconsume;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者 传统版
 */
public class ProdConsume {
    private int shareData=0;
    private Lock lock=new ReentrantLock();
    Condition condition=lock.newCondition();

    public void prod() throws InterruptedException {

        lock.lock();
        try {
            //生产
            while (shareData!=0){
                condition.await();
            }
            System.out.println("生产者生产");
            shareData++;
            System.out.println(shareData);
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }
    public void consum() throws InterruptedException {
        lock.lock();
        try {
            //生产
            while (shareData==0){
                condition.await();
            }
            System.out.println("消费者消耗");
            shareData--;
            System.out.println(shareData);
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ProdConsume prodConsume=new ProdConsume();
        new Thread(new ProdThread(prodConsume)).start();
        new Thread(new ConsumeThread(prodConsume)).start();
//        new Thread(new ConsumeThread(prodConsume)).start();
    }


}
class ProdThread implements Runnable{
    ProdConsume prodConsume=null;
    public ProdThread(ProdConsume prodConsume){
        this.prodConsume=prodConsume;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                prodConsume.prod();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ConsumeThread implements Runnable{
    ProdConsume prodConsume=null;
    public ConsumeThread(ProdConsume prodConsume){
        this.prodConsume=prodConsume;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                prodConsume.consum();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}