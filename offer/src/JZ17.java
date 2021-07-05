import java.util.LinkedList;
import java.util.Queue;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * 示例1
 * 输入
 * 复制
 * {8,8,#,9,#,2,#,5},{8,9,#,2}
 * 返回值
 * 复制
 * true
 */
public class JZ17 {
    public static void main(String[] args) {
        JZ17 jz17=new JZ17();
        int []treeNodes=new int[]{1,2,4,5,6};
        TreeNode root1=TreeNode.createTreeNode(treeNodes);
    }
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root1==null||root2==null){
            return false;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root1);
        while (!queue.isEmpty()){
            TreeNode currentNode =  queue.poll();
            if (currentNode.val== root2.val){
                if (judgeIsSub(currentNode,root2)){
                    return true;
                }
                
            }
            if (currentNode.left!=null){
                queue.offer(currentNode.left);
            }
            if (currentNode.right!=null){
                queue.offer(currentNode.right);
            }
        }

        return false;
    }

    private boolean judgeIsSub(TreeNode root1Node, TreeNode root2) {
        Queue<TreeNode> queue1=new LinkedList<>();
        Queue<TreeNode> queue2=new LinkedList<>();
        queue1.offer(root1Node);
        queue2.offer(root2);
        while (!queue1.isEmpty()||!queue2.isEmpty()){
            TreeNode current1Node =  null;
            TreeNode current2Node =  null;
            if (!queue1.isEmpty()){
                current1Node=queue1.poll();
            }
            if (!queue2.isEmpty()){
                current2Node=queue2.poll();
            }
            if (current2Node==null){
                return true;
            }
            if (current1Node==null||current1Node.val!= current2Node.val){
                return false;
            }

            if (current2Node.left!=null){
                if (current1Node.left!=null){
                    queue1.offer(current1Node.left);
                }else {
                    return false;
                }
                queue2.offer(current2Node.left);
            }
            if (current2Node.right!=null){
                if (current1Node.right!=null){
                    queue1.offer(current1Node.right);
                }else {
                    return false;
                }
                queue2.offer(current2Node.right);
            }
        }
        return false;
    }
}
