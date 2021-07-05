import java.io.BufferedReader;
import java.util.Arrays;

//输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
// 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
// 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
// 输入
//        [1,2,3,4,5,6,7],[3,2,4,1,6,5,7]

//      前序 根1 左 234，右 567
        // 前序 根2 左3，右4 ； 根5 左6 右7
//      中序 左 324 右 657
// 返回值
//        {1,2,5,3,4,6,7}
public class _JZ4重建二叉树 {
    /**
     * Definition for binary tree
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
//        BufferedReader
        if (pre.length<=0){
            return null;
        }
        TreeNode treeNode=new TreeNode(pre[0]);
        //找出中序遍历中根节点的位置
        int i=0;
        for (int j = 0; j <in.length ; j++) {
            if (in[j]==pre[0]){
                i=j;
            }
        }
        int[] leftIn = Arrays.copyOfRange(in, 0, i);
        int[] rightIn = Arrays.copyOfRange(in, i+1, in.length);
        int[] leftPre = Arrays.copyOfRange(pre, 1, leftIn.length + 1);
        int[] rightPre = Arrays.copyOfRange(pre, leftIn.length + 1, pre.length);
        treeNode.left=reConstructBinaryTree(leftPre,leftIn);
        treeNode.right=reConstructBinaryTree(rightPre,rightIn);
        return treeNode ;
    }

    public static void main(String[] args) {
//        int[] pre=new int[]{1,2,3,4,5,6,7};
        int[] pre=new int[]{};
//        int[] in=new int[]{3,2,4,1,6,5,7};
        int[] in=new int[]{};
        _JZ4重建二叉树 jz4重建二叉树=new _JZ4重建二叉树();
        TreeNode treeNode = jz4重建二叉树.reConstructBinaryTree(pre, in);
        System.out.println(treeNode);
    }

}
