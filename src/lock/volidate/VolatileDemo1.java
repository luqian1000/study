package lock.volidate;

/**
 * 保证可见性实例
 */
public class VolatileDemo1 {

    public static volatile boolean flag=false;

    public static void main(String[] args) throws InterruptedException {
        //线程一
        new Thread(()->{
            while (!flag)
            {
            }
            System.out.println("我现在是:"+flag);
        }).start();
        Thread.sleep(10);
        //线程二
        new Thread(()->
        {
            flag=true;
            System.out.println("我把flag修改成"+flag);
        }).start();
    }
}