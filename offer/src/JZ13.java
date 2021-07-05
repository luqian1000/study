import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
输入、
[1,2,3,4]
返回值
[1,3,2,4]
 */
public class JZ13 {
    public static void main(String[] args) {
        JZ13 jz13=new JZ13();
        int []array=new int[]{1,4,6,7};
        int[] ints = jz13.reOrderArray(array);
        System.out.println(ints);
    }
    /**
     * 思路：建两个队列LinkList一个放奇数一个放偶数最后合并，建队列是为了用队列里的poll方法，可以出队，用arrayList没有出队方法，不好处理
     *
     *
     * @param array int整型一维数组
     * @return int整型一维数组
     */
    public int[] reOrderArray (int[] array) {
        // write code here
        //奇数
        Queue<Integer> oddNums=new LinkedList<>();
        Queue<Integer> evenNums=new LinkedList<>();
        if (array==null||array.length<=0){
            return array;
        }
        for (int i : array) {
            if (i%2==0){
                evenNums.add(i);
            }else {
                oddNums.add(i);
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (oddNums.size()<=0){
                array[i]=evenNums.poll();
            }else {
                array[i]=oddNums.poll();
            }

        }
        return array;
    }
}
