import java.util.Stack;

/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 * 示例1
 * 输入
 * {1,2,3}
 * 返回值
 * {3,2,1}
 */
public class JZ15 {
    /**
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        if(head==null){
            return head;
        }
        ListNode newNode=null;
        //新node的最后的节点
        ListNode currentNode=null;
        Stack<Integer> stack = new Stack();
        stack.push(head.val);
        while (head.next!=null){
            head= head.next;
            stack.push(head.val);
        }
        while (!stack.isEmpty()){
            if (newNode==null){
                newNode=new ListNode(stack.pop());
                currentNode=newNode;
            }else {
                ListNode node = new ListNode(stack.pop());
                currentNode.next=node;
                currentNode=node;
            }
        }
        return newNode;
    }

    /**
     * 三个指针，一个指针c用来指向当前元素，一个指针p指向当前元素的前一个元素，一个指针n指向当前元素的后一个元素。
     * 将当前元素c的next指向前一个元素p，完成反转，之后将当前元素c赋值给指针p，指针n指向的元素赋给p，n的next元素赋给n；
     * @param head
     * @return
     */
    public ListNode optimizeReverseList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode prve=null;
        ListNode current=head;
        ListNode next=head.next;
        while (current!=null){
            current.next=prve;
            prve=current;
            current=next;
            if (next!=null){
                next=next.next;
            }
        }
        return prve;
    }

}
