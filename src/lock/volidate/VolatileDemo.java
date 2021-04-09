package lock.volidate;

/**
 * 保证可见性实例
 *
 *
 * valitile dome
 * volatile 关键字保证了操作的可见性
 * valitie 最好用于一写多读的情况下
 * {@link VolatileDemo2} 测试volatile不能保证对变量的操作是原子性
 * @author zyt
 * @create 2018-04-15 12:53
 **/
public class VolatileDemo extends Thread{
    //设置类静态变量,各线程访问这同一共享变量
    private  static  Boolean flag = false;
    //无限循环,等待flag变为true时才跳出循环
    public void run() {
        while (!flag){
        };
        System.out.println("停止了");
    }

    public static void main(String[] args) throws Exception {
        new VolatileDemo().start();
        new VolatileDemo().start();
        new VolatileDemo().start();
        System.out.println("当前flag是"+flag);
        //sleep的目的是等待线程启动完毕,也就是说进入run的无限循环体了
        Thread.sleep(1000);
        flag = true;
        System.out.println("当前flag是"+flag);
    }
}

