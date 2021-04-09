package queue;

public interface QueueMyself<E> {
    //获取队列长度
    int size();
    //入队
    boolean enQueue(E element);
    //出队（移除）
    E deQueue();
    //获取队头
    boolean front();
}
