//题目描述
//        在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
//        [
//        [1,2,8,9],
//        [2,4,9,12],
//        [4,7,10,13],
//        [6,8,11,15]
//        ]
//        给定 target = 7，返回 true。
//
//        给定 target = 3，返回 false。
//
//
//        示例1
//        输入
//        复制
//        7,[[1,2,8,9],[2,4,9,12],[4,7,10,13],[6,8,11,15]]
//        返回值
//        复制
//        true
//        说明
//        存在7，返回true
//        示例2
//        输入
//        复制
//        3,[[1,2,8,9],[2,4,9,12],[4,7,10,13],[6,8,11,15]]
//        返回值
//        复制
//        false
//        说明
//        不存在3，返回false
//    [1,2,8,9],
//        [2,4,9,12],
//        [4,7,10,13],
//        [6,8,11,15]
public class JZ1 {
    //思路，从左下角开始遍历，目标值比遍历到的大则往右找，否则往上找
    //不从左上的原因是左上只能往右和下走，但右和下都比左上大，不容易控制往哪个方向走

    //上面思路是对的 Find是我的实现，我的实现是对的但有个问题就是遍历到的值比目标值大则往上找，往上找的时候总是从上一行的第一个开始
    //优化一下就从正上方开始，优化见Find1
    public boolean Find(int target, int [][] array) {
        //行数
        int length = array.length;
        for (int i = length-1; i >= 0; i--) {
            //每行的长度
            int length1 = array[i].length;
            for (int j = 0; j <length1; j++) {
                //如果从最后一行的左边开始向右遍历，遍历到的值比目标值大则往上找
                if (array[i][j]>target){
                    break;
                    //如果从最后一行的左边开始向右遍历，遍历到的值比目标值大则往右找
                }else if (array[i][j]<target){
                    continue;
                }else {
                    return true;
                }

            }
        }
        return false;

    }

    /**
     * 优化
     *
     * @param target
     * @param array
     * @return
     */
    public boolean Find1(int target, int [][] array) {
        //行数
        int row = array.length;
        //列数
        int len = array[0].length;

        int x=row-1;
        int y=0;
        while (y<len&&x>=0){
            //当前值大于目标值，向上
            if (array[x][y]>target){
                x--;
            }else if (array[x][y]<target){
                y++;
            }else {
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {
//        [[1,2,8,9],[2,4,9,12],[4,7,10,13],[6,8,11,15]]
        int [][]arrays=new int[][]{{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        int target=1;
        JZ1 jz1=new JZ1();
        System.out.println(jz1.Find(target, arrays));

        System.out.println(arrays.length);
        System.out.println(arrays[1].length);
    }
}
