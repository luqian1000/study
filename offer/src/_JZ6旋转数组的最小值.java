import java.util.Arrays;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * [3,4,5,1,2]  返回值 1
 */
public class _JZ6旋转数组的最小值 {
    public static void main(String[] args) {
//        minNumberInRotateArray(null);
    }
    //1234567   6712345
    public int minNumberInRotateArray(int [] array) {

        int min = 0;
        if (array.length == 1) {
            min = array[0];
        } else if (array.length == 2) {
            min = array[0] < array[1] ? array[0] : array[1];
        } else {
            int i = array.length / 2;
            if (array[i - 1] > array[i]) {
                min = array[i];
            } else {
                if (array[0] > array[i - 1]) {
                    min = minNumberInRotateArray(Arrays.copyOfRange(array, 0, i));
                } else {
                    min = minNumberInRotateArray(Arrays.copyOfRange(array, i, array.length));
                }
            }
        }
        return min;
    }
    //1234567   7123456  3456712
    //111101
    public int minNumberInRotateArray1(int [] array) {
        int mid = array.length / 2;
        //第一个大于最后一个
        if (array[0]>array[array.length-1]){

        }

return 0;
    }
}
