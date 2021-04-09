package lock.lock;

//import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import javax.xml.crypto.Data;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CASTest {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference=new AtomicReference<>();
        User user=new User();
        user.setUserName("3");
        atomicReference.set(user);
        User user2=new User();
        user2.setUserName("4");
        boolean b = atomicReference.compareAndSet(user, user2);
        System.out.println(b);
        String s="e";
    }
}
class User{
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}