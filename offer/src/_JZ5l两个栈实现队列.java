import java.util.Stack;

/**
 *用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class _JZ5l两个栈实现队列 {
    //push
    Stack<Integer> stack1 = new Stack<Integer>();
    //pop
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()){
            if (!stack1.isEmpty()){
                int size = stack1.size();
                for (int i = 0; i < size; i++) {
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }else {
                return -1;
            }
        }else {
            return stack2.pop();
        }
    }

    public static void main(String[] args) {
        _JZ5l两个栈实现队列 test=new _JZ5l两个栈实现队列();
        test.push(1);
        test.push(2);
        test.push(3);
        System.out.println(test.pop());
        System.out.println(test.pop());
    }
}
