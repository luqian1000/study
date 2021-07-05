import java.util.ArrayList;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */

/**
 * 好好理解一下
 */
public class J19printMatrix {
    public static void main(String[] args) {
        J19printMatrix j19printMatrix=new J19printMatrix();
        int[][] mara=new int[][]{{1},{2},{3},{4}};
        ArrayList<Integer> integers = j19printMatrix.printMatrix(mara);
        System.out.println(integers);

    }
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        //开始行
        int startRow =0;
        //结束行
        int endRow=matrix.length-1;
        //开始列
        int startColum=0;
        //结束列
        int endColum=matrix[0].length-1;
        ArrayList<Integer> result=new ArrayList<>();
        while (true){
            //打印首行
            for (int i = startColum; i <=endColum ; i++) {
                //遍历第startRow行
                result.add(matrix[startRow][i]);
            }

            //开始行加一
            startRow++;
            if (startRow>endRow){
                break;
            }
            //打印右边的列
            for (int i = startRow; i <=endRow; i++) {
                result.add(matrix[i][endColum]);
            }
            //结束列减一
            endColum--;
            if (startColum>endColum){
                break;
            }
            //打印尾行
            for (int i = endColum; i >=startColum; i--) {
                result.add(matrix[endRow][i]);
            }
            //尾行减一
            endRow--;
            if (startRow>endRow){
                break;
            }
            //打印左边的列
            for (int i = endRow; i >=startRow; i--) {
                result.add(matrix[i][startColum]);
            }
            //尾行减一
            startColum++;
            if (startColum>endColum){
                break;
            }
        }
        return result;




    }
}
