package queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> strings = new ArrayBlockingQueue<>(10);
        ReentrantLock lock=new ReentrantLock();
        lock.lock();
        try {
            strings.put("s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();

    }
}
