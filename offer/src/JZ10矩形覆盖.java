/**
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * 比如n=3时，2*3的矩形块有3种覆盖方法：
 *思路
 *
 * n块矩形有f(n)种覆盖方法。进行逆向分析，要完成最后的搭建有两种可能。
 * 1、第一次竖着一个板子进去
 * 2、第一次横着两个板子进去
 * 第一种情况等价于情形1中阴影部分的n-1块矩形有多少种覆盖方法，为f(n-1);
 *
 * 第二种情况等价于情形2中阴影部分的n-2块矩形有多少种覆盖方法，为f(n-2);
 *
 * 故f(n) = f(n-1) + f(n-2)，还是一个斐波那契数列。。。。
 * n=1 1种
 * n=2 2种
 */
public class JZ10矩形覆盖 {
    /**
     * 直接自下而上求
     * @param target
     * @return
     */
    public int rectCover(int target) {
        if(target<=2){
            return target;
        }
        int first=1;
        int second=2;
        int third=1;
        for (int i = 2; i < target; i++) {
            first=first+second;
            second=first-third;
            third=second;
        }
        return first;


    }
}
