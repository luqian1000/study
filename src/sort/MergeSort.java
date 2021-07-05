package sort;//package sort;

/**
 * 归并排序，就是分治
 */
public class MergeSort {
    public void mergeSort(int[]array){
        if (array == null || array.length <= 0) {
            return;
        }
        int[]temArray=new int[array.length];
        sort(array,0,array.length-1,temArray);
    }

    private void sort(int[] array, int start, int end, int[] temArray) {
        if (start<end){
            int mid=(end+start)>>1;
            sort(array,start,mid,temArray);
            sort(array,mid+1,end,temArray);
            merge(array,start,mid,end,temArray);

        }
    }

    /**
     * 左右两边比较并合并
     * 1、假如array457824568 start为0，mid=3，end=8即为4578 24568
     * 2、合并的思路是找到左边开始的点即leftStart等于start为0，rightStart等于mid+1为4，将0和4分别赋值给left和right变量，
     * 不能直接修改start和end的值，因为这是这段array的范围指向。
     * 3、比较左边和右边序列的值开始排序，例如开始左边的4与右边的2开始比较，将小的依次存入备份数组temArray中，同时备份数组的长度temIndex++，且赋给备份数组的左边或右边序列的left或rihgt加1，
     * 当left<=mid&&right<=end即两者都有值未比较完时走比较逻辑，当有任何一边的序列无值可比较时，循环遍历另一个序列将值插入到备份数组中，可以直接插入的原因是对于两个序列自己来说元素是排好序了的。
     * 4、最后合并备份数组的值到原数组中，因为start和end是原数组中数据的下标，递归中下标没有交集所以可以将备份数组中的值补充到array的start->end段，
     * @param array
     * @param start
     * @param mid
     * @param end
     * @param temArray
     */
    private void merge(int[] array, int start, int mid, int end, int[] temArray) {

        int left =start;
        int right =mid+1;
        int temIndex=0;
        while (left<=mid&&right<=end){
            if (array[left]<array[right]){
                temArray[temIndex++]=array[left++];
            }else {
                temArray[temIndex++]=array[right++];
            }
        }
        while (left<=mid){
            for (int i = left; i <=mid ; i++) {
                temArray[temIndex++]=array[i];
            }
            break;
        }
        while (right<=end){
            for (int i = right; i <=end ; i++) {
                temArray[temIndex++]=array[i];
            }
            break;
        }
        //合并备份数组的值到原数组中，必须用temIndex，不能用temArray.length,因为temArray被所有递归调用都共用，
        // 这次给temArray赋的值可能就用了2个但是上次递归可能存了3个元素到temArray，这次并没有完全覆盖掉
        for (int i = 0; i < temIndex; i++) {
            array[start++]=temArray[i];
        }
    }

    public static void main(String[] args) {
        int[]test=new int[]{6,5,3};
        MergeSort mergeSort=new MergeSort();
        mergeSort.mergeSort(test);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
}


