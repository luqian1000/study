import java.util.*;

//输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
//        示例1
//        输入
//        复制
//        {67,0,24,58}
//        返回值
//        复制
//        [58,24,0,67]
/*public class ListNode {
    int val;
    ListNode next = null;
    ListNode(int val) {
        this.val = val;
    }
}*/
public class _JZ3链表从后往前输出 {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Deque<Integer> stack=null;
        ArrayList<Integer> arrayList=new ArrayList<>();
        while (listNode!=null&&listNode.next!=null){
            if (stack==null){
                stack=new LinkedList<>();
            }
            stack.push(listNode.val);
            listNode=listNode.next;
        }
        if (stack!=null){

            while (!stack.isEmpty()){
                arrayList.add(stack.poll());
            }
        }
        return arrayList;
    }
}
