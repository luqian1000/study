package tree;

import tree.printer.BinaryTreeInfo;
import tree.printer.BinaryTrees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉搜索树
 * @param <E>
 */
public class BlanceTreeMyselfImpl2<E> implements TreeMyself<E> , BinaryTreeInfo {
    private int size;
    private Node root;

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node)node).e;
    }

    class Node<E> {
        E e;
        Node<E> left;
        Node<E> right;
        Node<E> parent;
        int height=1;
        private int getChildNodeAmount(){
            if (left!=null&&right!=null){
                return 2;
            }else if (left!=null||right!=null){
                return 1;
            }else {
                return 0;
            }
        }
        private boolean isLeft() {
            return parent==null?false:parent.left==this;
        }
        private boolean isRight() {
            return parent==null?false:parent.right==this;
        }
        private boolean isRoot() {
            return parent==null?true:false;
        }

        public Node(Node parent, E e) {
            this.parent = parent;
            this.e = e;
        }

        public boolean isBlance() {
            int leftHight=left==null?0:left.height;
            int rightHight=right==null?0:right.height;
            if (Math.abs(leftHight-rightHight)>1){
                return false;
            }
            return true;
        }

        public Node tallerChild() {
            int leftHight=left==null?0:left.height;
            int rightHight=right==null?0:right.height;
            return leftHight>rightHight?left:right;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {
        //判空
        if (e==null){
            return false;
        }

        if (size==0){
            root=new Node(null,e);
            size++;
            addAfter(root);
            return true;
        }else {
            //现有的节点,需要被比较的节点
            Node compareNode=root;
            //要插入的节点的最终父节点
            Node targetNode=compareNode;
            //记录比较的值知道往哪插入
            int compareValue=0;
            //根据e的大小循环更改compareNode的值
            while (compareNode!=null){
                //记录最后一个不为空的compareNode的值给targetNode，即为最终要插入的位置的父节点
                targetNode=compareNode;
                compareValue = ((Comparable) compareNode.e).compareTo(e);
                //现有的节点值比要插入的大，往左走
                if (compareValue>0){
                    compareNode=compareNode.left;
                    //现有的节点值比要插入的小，往右走
                }else if (compareValue<0){
                    compareNode=compareNode.right;
                    //等于0说明要替换该值，不再遍历，替换targetNode节点的元素值退出
                }else {
                    // 相等覆盖原节点的元素值，不把needAddNode赋给targetNode的原因是targetNode已维护好左右父节点
                    //不能不覆盖，原因是如果e是对象的话用compareTo比较的相等不一定是真相等，跟对象实现的compareTo有关，
                    // 所以还是覆盖比较好
                    targetNode.e=e;
                    return true;
                }
            }
            Node needAddNode=new Node(targetNode,e);
            // 上面只是找到了要添加位置的父节点, 下面要将元素添加进去
            if (compareValue>0){
                targetNode.left=needAddNode;
            }else if (compareValue<0){
                targetNode.right=needAddNode;
            }
            size++;
            addAfter(needAddNode);
            return true;
        }

    }

    private void addAfter(Node insertNode) {
        Node parent=insertNode;
        while (parent!=null){
            if (parent.isBlance()){
                updateHight(parent);
            }else {
                reblance(parent);
            }
            parent=parent.parent;
        }
    }

    private void reblance(Node node) {
        if (node.right==node.tallerChild()){
            //右右
            if (node.right.right==node.right.tallerChild()){
                //左旋
                leftRote(node);
            //右左
            }else {
                rightRote(node.right);
                leftRote(node);
            }
        }else if (node.left==node.tallerChild()){
            //左右
            if (node.left.right==node.left.tallerChild()){
                leftRote(node.left);
                rightRote(node);
            //左左
            }else if (node.left.left==node.left.tallerChild()){
                rightRote(node);
            }
        }
    }

    private void rightRote(Node grand) {
        Node parent = grand.left;
        Node child=parent.right;
        parent.parent=grand.parent;
        if (grand==root){
            root=parent;
        }
        grand.left=child;
        parent.right=grand;
        if (child!=null){
            child.parent=grand;
        }
        grand.parent=parent;
        updateHight(grand);
        updateHight(parent);
    }

    private void leftRote(Node grand) {
        Node parent = grand.right;
        Node child=parent.left;
        parent.parent=grand.parent;
        if (grand==root){
            root=parent;
        }
        grand.right=child;
        parent.left=grand;
        if (child!=null){
            child.parent=grand;
        }
        grand.parent=parent;
        updateHight(grand);
        updateHight(parent);
    }

    private void updateHight(Node parent) {
        int leftHight=parent.left==null?0:parent.left.height;
        int rightHight=parent.right==null?0:parent.right.height;
        parent.height=Math.max(leftHight,rightHight)+1;
    }

    @Override
    /**
     * 思路:根插入类似，只是少了插入操作，且循环去找比较值为0的节点
     */
    public boolean contains(E e) {
        if (size==0||e==null){
            return false;
        }
        //现有的节点,需要被比较的节点
        Node compareNode=root;
        //记录比较的值
        int compareValue=0;
        //根据e的大小循环更改compareNode的值
        while (compareNode!=null){
            compareValue = ((Comparable) compareNode.e).compareTo(e);
            //现有的节点值比要找的大，往左走
            if (compareValue>0){
                compareNode=compareNode.left;
                //现有的节点值比要找的小，往右走
            }else if (compareValue<0){
                compareNode=compareNode.right;
                //等于0说明存在e，返回true
            }else {
                return true;
            }
        }
        //走到这说明没找到
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(E e) {
        //找e
        Node node= node(e);
        return remove(node);
    }

    private boolean remove(Node node) {
        if (node!=null){
            //该节点的度
            int childAmount = node.getChildNodeAmount();
            //度为0
            if (childAmount==0){
                if (node.isRoot()){
                    root=null;
                }else if (node.isLeft()){
                    node.parent.left=null;
                    //不用置空，会被自动gc
                    //node.parent=null;
                }else {
                    node.parent.right=null;
                    //node.parent=null;
                }
                size--;
                removeAfter(node);
                //度为1
            }else if(childAmount==1){
                //删除node节点后要维护的其子节点
                Node replceNode=node.left!=null?node.left:node.right;
                //如果要删除的节点是叶子节点且它的度为1则把要删除的节点的父节点赋给要维护的子节点的parent变量
                if (node.isRoot()){
                    replceNode.parent=node.parent;
                }else if (node.isRight()){
                    node.parent.right=replceNode;
                }else {
                    node.parent.left=replceNode;
                }
                size--;
                removeAfter(node);
                //度为2
            }else {
                Node successor=successor(node);
                node.e=successor.e;
                remove(successor);
            }
        }
        return false;
    }
    //检查被删除的节点的父及祖先是否平衡
    //一个细节是上面的删除不要把node的父置空不然无法向上找父及祖先
    private void removeAfter(Node node) {
        while ((node=node.parent)!=null){
            if (node.isBlance()){
                updateHight(node);
            }else {
                reblance(node);
            }
        }
    }

    private Node successor(Node node) {
        Node successor = node.right;
        while (successor!=null){
            if (successor.left!=null){
                successor=successor.left;
            }else {
                break;
            }
        }
        return successor;
    }



    private Node node(E e) {
//        Node target=null;
        if (size==0||e==null){
            return null;
        }
        //现有的节点,需要被比较的节点
        Node compareNode=root;
        //记录比较的值
        int compareValue=0;
        //根据e的大小循环更改compareNode的值
        while (compareNode!=null){
            compareValue = ((Comparable) compareNode.e).compareTo(e);
            //现有的节点值比要找的大，往左走
            if (compareValue>0){
                compareNode=compareNode.left;
                //现有的节点值比要找的小，往右走
            }else if (compareValue<0){
                compareNode=compareNode.right;
                //等于0说明存在e，返回true
            }else {
                return compareNode;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    private void preOrderTraversal(Node node) {
        //前序遍历先遍历根节点，所以进来先输出当前Node的值
        if (node != null) {
            System.out.println(node.e);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    @Override
    public void preOrder() {
        //递归实现 begin
        {
            preOrderTraversal(root);
        }
        //递归实现 stop
        //非递归实现 begin
        //
        {
            Node nodeRoot = root;
            //存放遍历到的节点
            Stack<Node> nodeStack = new Stack();
            while (nodeRoot != null || !nodeStack.isEmpty()) {
                while (nodeRoot != null) {
                    //将遍历到的节点入栈，后面再取出
                    nodeStack.push(nodeRoot);
                    //打印当前节点
                    System.out.println(nodeRoot.e);
                    //打印出子树的根节点的值之后往左走，再把左子树节点放到栈中，
                    // 再打印出来直到nodeRoot为空
                    nodeRoot = nodeRoot.left;
                }
                // nodeRoot为空说明左子树遍历完了，取出栈里的节点往右走，
                // 然后执行while循环遍历右子树的左子树
                Node pop = nodeStack.pop();
                nodeRoot = pop.right;
            }
            
        }
        //非递归实现 stop
    }

    @Override
    //后序遍历
    public void postOrder() {

        //递归实现 begin
        {
            postOrderTraversal(root);
        }
        //递归实现 stop
        //非递归实现 begin
        /**
         * 后续遍历
         * 思路，用两个栈，一个栈用来存放遍历过的根节点方便回去，另一个是结果栈，结果栈从上到下的顺序最后要是实际结果的倒序。
         * 后序遍历的方法是左->右->根，那存放到结果栈的数据从上往下应该是根->右->左，而根->右->左又可以近似看成前序遍历
         * ⚠️相当于把前序遍历先访问右节点的结果（正常前序遍历是根->左->右，现在是根->右->左）存到结果栈里，
         * 结果栈再取出来就是后序遍历的结果
         */
        {
            if (size==0){
                return;
            }
            Stack src=new Stack();
            Stack res=new Stack();
            Node node=root;
            while (node!=null||!src.isEmpty()){
                while (node!=null){
                    src.add(node);
                    //将前序遍历的结果存到结果栈，可以比较前序遍历，在前序遍历里这里要打印的现在是add到结果栈
                    res.add(node);
                    node=node.right;
                }
                //右子树遍历完往左遍历
                Node pop = (Node) src.pop();
                node=pop.left;
            }
            while (!res.isEmpty()){
                System.out.println(((Node)res.pop()).e);
            }
        }
        //非递归实现 stop

    }

    private void postOrderTraversal(Node root) {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.println(root.e);
        }
    }

    @Override
    public void inorderTraversal() {
        //递归
        {
            inorderTraversal(root);
        }
        //递归

        //非递归
        {
            if (size==0){
                return;
            }
            Node node=root;
            Stack nodeStack=new Stack();
            while (node!=null||!nodeStack.isEmpty()){
                while (node!=null){
                    nodeStack.add(node);
                    node=node.left;
                }
                Node pop = (Node)nodeStack.pop();
                System.out.println(pop.e);
                node=pop.right;
            }
        }
        //非递归
    }

    @Override
    /**
     * 思路：
     * 1、将子树根节点入队；
     * 2、之后从队列取出根节点，然后获取子树左子树的根节点再入队，再获取右子树的根节点入队
     * 3、然后打印当前出队的根节点。
     * 4、重复步骤2、3
     */
    public void levelOrder() {
        if (size==0){
            return;
        }
        Node node=root;
        Queue queue=new LinkedList();
        queue.offer(node);
        while (!queue.isEmpty()){
            node=(Node)queue.poll();
            if (node.left!=null){
                queue.offer(node.left);
            }
            if (node.right!=null){
                queue.offer(node.right);
            }
            System.out.println(node.e);
        }
    }

    private void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.println(root.e);
            inorderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        BlanceTreeMyselfImpl2<Integer> treeMyself = new BlanceTreeMyselfImpl2<>();
        Integer[]integers=new Integer[]{16,18,11,10,15,17,9};
        for (Integer integer : integers) {
            treeMyself.add(integer);
        }
        System.out.println(treeMyself);
        System.out.println(treeMyself.contains(3));
        System.out.println(treeMyself.contains(4));
        BinaryTrees.print(treeMyself);
        System.out.println();
        treeMyself.remove(18);
        BinaryTrees.print(treeMyself);
        System.out.println();
        treeMyself.levelOrder();
        System.out.println("上面层序-------");
        BinaryTrees.print(treeMyself);

    }
}
