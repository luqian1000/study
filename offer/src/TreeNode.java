
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    public static TreeNode createTreeNode(int[] treeNodes) {
        TreeNode root=null;
        TreeNode last=null;
        for (int treeNode : treeNodes) {
            if (root==null){
                root=new TreeNode(treeNode);
                last=root;

            }else {
//                last.=new
            }
        }
        return null;
    }
}

