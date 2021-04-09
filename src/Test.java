import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {
    public static void main(String[] args) {
        Student s1 = new Student("⼩张");
        Student s2 = new Student("⼩李");
        Test test = new Test();
        test.swap(s1, s2);
        Student x=s1;
        Student y=s1;
        Student temp = x;
        x = y;
        y = temp;
        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());
    }
    public  void swap(Student x, Student y) {
        Student temp = x;
        x = y;
        y = temp;
        System.out.println("x:" + x.getName());
        System.out.println("y:" + y.getName());
    }


}
