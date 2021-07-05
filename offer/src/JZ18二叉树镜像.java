/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 比如：    源二叉树
 *             8
 *            /  \
 *           6   10
 *          / \  / \
 *         5  7 9 11
 *         镜像二叉树
 *             8
 *            /  \
 *           10   6
 *          / \  / \
 *         11 9 7  5
 * 示例1
 * 输入
 * 复制
 * {8,6,10,5,7,9,11}
 * 返回值
 * 复制
 * {8,10,6,11,9,7,5}
 */
public class JZ18二叉树镜像 {
    //思路：
    public TreeNode Mirror (TreeNode pRoot) {
        // write code here
        if (pRoot==null){
            return null;
        }
        TreeNode left = pRoot.left;
        pRoot.left=pRoot.right;
        pRoot.right=left;
        Mirror(pRoot.left);
        Mirror(pRoot.right);
        return pRoot;

    }
}
