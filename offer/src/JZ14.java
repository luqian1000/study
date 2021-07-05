import java.util.Stack;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 *
 * 保证base和exponent不同时为0。不得使用库函数，同时不需要考虑大数问题，也不用考虑小数点后面0的位数。
 *
 *
 * 输入
 * 2.00000,3
 * 返回值
 * 8.00000
 */
public class JZ14 {


    public static void main(String[] args) {
        JZ14 JZ14=new JZ14();
        ListNode pHead=new ListNode(1);
        ListNode p2=new ListNode(2);
        pHead.next=p2;
        ListNode p3=new ListNode(3);
        p2.next=p3;
        ListNode p4=new ListNode(4);
        p3.next=p4;
        ListNode p5=new ListNode(5);
        p4.next=p5;
        ListNode listNode = JZ14.FindKthToTail1(pHead, 6);
        System.out.println(listNode);
    }
    public ListNode FindKthToTail (ListNode pHead, int k) {


        // write code here
        if (pHead==null||k<=0){
            return null;
        }
        Stack stack=new Stack();
        stack.push(pHead);
        int i=1;
        while (pHead.next!=null){
            stack.push(pHead.next);
            pHead=pHead.next;
            i++;
        }
        if (k>i){
            return null;
        }
        for (int j = 0; j < k; j++) {
            pHead=(ListNode)stack.pop();
        }
        return pHead;
    }

    /**
     * 优化 ；快慢指针，俩指针都在链表头节点，快指针先走k步，之后两者一块走，直到快指针走到null的
     * 位置，当前慢指针所在位置的链表节点值就是当前要求的值
     * @param pHead
     * @param k
     * @return
     */
    public ListNode FindKthToTail1 (ListNode pHead, int k) {
        if (pHead==null||k<=0){
            return null;
        }
        ListNode first=pHead;
        ListNode slow=pHead;
        for (int i = 0; i < k; i++) {
            if (i+2!=k&&first==null){
                return null;
            }
            first=first.next;
        }
//        ListNode result=null;
        while (first!=null){
            first=first.next;
            slow= slow.next;


        }
        return slow;


    }
}
