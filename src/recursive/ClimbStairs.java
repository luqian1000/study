package recursive;

/**
 * 跳台阶问题
 * 现有n阶台阶每次都能走一个或两个台阶，问走完n阶台阶总共有多少种走法
 * 递归 第一步有两种走法：1和2，则还剩n-1和n-2阶台阶分别对应f(n-1)和f(n-2)种走法，
 * 则两种走法的和就是总共的走法，即f(n)=f(n-1)+f(n-2)
 *
 * 最终得出的是类似一个斐波那契数列：
 *                  |  1，n = 1
 * f(n)   =       |  2, n = 2
 *                  |  f(n-1) + f(n -2), n >2
 */
public class ClimbStairs {
    //😚😚第一种做法，效率最低，因为自上而下会有很多重复的计算
    //算了f(5)+f(4)  又算f(4)+f(3)
    //时间复杂度 T(n)=T(n-1)+T(n-2)+O(1)=2^n
    //空间复杂度：递归深度*辅助空间=n*1=n
    public int climb1(int n){
        if (n<=2){
            return n;
        }
        return climb1(n-1)+climb1(n-2);
    }
    //将每次climb2(n)的计算结果都存下来，下次计算前去数组查一下看有没有，有的话直接使用
    //时间复杂度 O（n）空间复杂度o（n）
    public int climb2(int n){
       int []array=new int[n+1];
       array[1]=1;
       array[2]=2;
       return climb2(n,array);
    }
    public int climb2(int n,int[]array){
        if (n<=2){
            return n;
        }
        if (array[n-1]==0){
            array[n-1]=climb2(n-1,array);
        }
        if (array[n-2]==0){
            array[n-2]=climb2(n-2,array);
        }
        return array[n-1]+array[n-2];
    }

    //将每次climb3(n)自下而上计算，first和second分别表示要加的两个数
    //时间复杂度 O（n）空间复杂度o（1）
    public int climb3(int n){
        if (n<=2){
            return 1;
        }
        int first=1;
        int second=1;
        //i从0开始第三个应该是i=2
        for (int i = 2; i < n; i++) {
            first=first+second;
            second=first-second;
        }
        return first+second;
    }

    public static void main(String[] args) {
        ClimbStairs climbStairs=new ClimbStairs();
        Times.test("climb2", new Times.Task() {
            @Override
            public void execute() {
                System.out.println(climbStairs.climb2(50));
            }
        });
        Times.test("climb1", new Times.Task() {
            @Override
            public void execute() {
                System.out.println(climbStairs.climb1(30));
            }
        }); Times.test("climb3", new Times.Task() {
            @Override
            public void execute() {
                System.out.println(climbStairs.climb3(50));
            }
        });

    }

}
