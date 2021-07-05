/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * 思路：
 *
 * a.如果两种跳法，1阶或者2阶，那么假定第一次跳的是一阶，那么剩下的是n-1个台阶，跳法是f(n-1);
 *
 * b.假定第一次跳的是2阶，那么剩下的是n-2个台阶，跳法是f(n-2)
 *
 * c.由a\b假设可以得出总跳法为: f(n) = f(n-1) + f(n-2)
 *
 * d.然后通过实际的情况可以得出：只有一阶的时候 f(1) = 1 ,只有两阶的时候可以有 f(2) = 2
 *
 */
public class JZ8ClimbStairs {
    /**
     * 类似于斐波那契数列
     * @param target
     * @return
     */
    public int jumpFloor(int target) {
        if(target<=2){
            return target;
        }
        return jumpFloor(target-1)+jumpFloor(target-2);
    }

    /**
     * 优化
     * @param target
     * @return
     */
    public int jumpFloor1(int target) {
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
