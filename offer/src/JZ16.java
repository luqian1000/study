/**
 * 题目描述
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * 示例1
 * 输入
 * {1,3,5},{2,4,6}
 * 返回值
 * {1,2,3,4,5,6}
 */
public class JZ16 {
    public ListNode Merge(ListNode list1,ListNode list2) {

        ListNode result=new ListNode(0);
        ListNode resultCurrent=result;
        if (list1==null&&list2==null){
            return null;
        }
        while (list1!=null||list2!=null){
            int list1Val = Integer.MAX_VALUE;
            int list2Val = Integer.MAX_VALUE;
            if (list1!=null){
                list1Val=list1.val;
            }
            if (list2!=null){
                list2Val=list2.val;
            }
            if (list1Val>=list2Val){
                list2=list2.next;
                resultCurrent.next=new ListNode(list2Val);
                resultCurrent=resultCurrent.next;

            }else {
                list1=list1.next;
                resultCurrent.next=new ListNode(list1Val);
                resultCurrent=resultCurrent.next;
            }
        }
        return result.next;
    }
}
