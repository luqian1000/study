package lock.volidate;


import java.util.concurrent.atomic.AtomicInteger;

class Data {
    //保证可见性，不保证原子性
    public volatile int num=0;
    //既保证原子性又保证可见性，
    //volatile保证可见性，cas保证原子性
    public AtomicInteger atomicInteger=new AtomicInteger();

    public void increase(){
        num++;
        atomicInteger.getAndIncrement();
    }
}
class IncreaseThread implements Runnable{
    Data data=null;
   public IncreaseThread(Data data){
       this.data=data;
   }
    @Override
    public void run() {
        data.increase();
    }
}


public class Volatile_不保证原子性 {
    public static void main(String[] args) {
        Data data = new Data();
        for (int i = 0; i < 100; i++) {
            new Thread(new IncreaseThread(data)).start();
        }
        System.out.println("num的值是"+data.num);
        System.out.println("atomicInteger的值是"+data.atomicInteger);
    }
}
