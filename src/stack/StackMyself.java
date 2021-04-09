package stack;

public interface StackMyself<E> {
    //栈长度
    int size();
    //往栈里添加数据
    boolean push(E element);
    //出栈（移除栈顶元素）
    E pop();
    //获取栈顶元素
    E top();
}
