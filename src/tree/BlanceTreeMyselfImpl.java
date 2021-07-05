package tree;

import tree.printer.BinaryTreeInfo;
import tree.printer.BinaryTrees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

/**
 * 二叉搜索树
 * @param <E>
 */
public class BlanceTreeMyselfImpl<E> implements TreeMyself<E> , BinaryTreeInfo {
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
        public boolean isLeftNode(){
            return parent!=null&&parent.left==this;
        }
        public boolean isRightNode(){
            return parent!=null&&parent.right==this;
        }
        public Node(Node parent, E e) {
            this.parent = parent;
            this.e = e;
        }

        public boolean isBlance() {

            int leftHeight=left==null?0:left.height;
            int rightHeight=right==null?0:right.height;
            int abs = Math.abs(leftHeight - rightHeight);
            if (abs==1||abs==0){
                return true;
            }
            return false;
        }

        public boolean isLeftChild(){ // 判断自己是不是左子树
            return parent!=null && this==parent.left;
        }
        public boolean isRightChild(){ // 判断自己是不是右子树
            return parent!=null && this==parent.right;
        }
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((Node<E>) left).height;
            int rightHeight = right == null ? 0 : ((Node<E>) right).height;
            if (leftHeight > rightHeight) return left;
            if (rightHeight > leftHeight) return right;
            // 高度一样则返回同方向的，左子节点则返回左，否则返回右
            return isLeftChild() ? left : right;
        }
    }

    @Override
    public int size() {
        return size;
    }


    public void leftHanded(Node<E> grand){
        Node<E> parent=grand.right;
        Node<E> child = parent.left;
        grand.right=child;
        parent.left=grand;
        afterRote(grand,parent,child);
    }


    private void afterRote(Node<E> grand, Node<E> parent, Node<E> child) {
        parent.parent=grand.parent;
        if (grand.isLeftNode()){
            grand.parent.left=parent;
        }else if (grand.isRightNode()){
            grand.parent.right=parent;
        }else {
            root=parent;
        }
        if (child!=null){
            child.parent=grand;
        }
        grand.parent=parent;
        updateHeight(grand);
        updateHeight(parent);
    }

    private void updateHeight(Node<E> node) {
        int leftHeight=node.left==null?0:node.left.height;
        int rightHeight=node.right==null?0:node.right.height;
        node.height=1+Math.max(leftHeight,rightHeight);

    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            return false;
        }
        if (root == null) {
            BlanceTreeMyselfImpl.Node newNode = new BlanceTreeMyselfImpl.Node(null, e);
            root = newNode;
            size++;
        } else {
            BlanceTreeMyselfImpl.Node parentNode = null;
            BlanceTreeMyselfImpl.Node addLocation = root;
            int compareResult = 0;
            while (addLocation != null) {
                parentNode = addLocation;
                compareResult = ((Comparable<E>) parentNode.e).compareTo(e);
                if (compareResult > 0) {
                    addLocation = parentNode.left;
                } else if (compareResult < 0) {
                    addLocation = parentNode.right;
                } else {
                    parentNode.e = e;
                    return true;
                }
            }
            Node newNode = new Node(parentNode, e);

            if (compareResult > 0) {
                parentNode.left = newNode;
            } else if (compareResult < 0) {
                parentNode.right = newNode;
            }
            size++;
            afterAdd(newNode);
        }
        return true;
    }

    private void afterAdd(Node newNode) {
        while ((newNode=newNode.parent)!=null){
            if (newNode.isBlance()){
                updateHeight(newNode);
            }else {
                reBalance(newNode);
            }
        }
    }

    private void reBalance(Node grand) {

        Node<E> parent = ((Node<E>) grand).tallerChild();
        Node<E> node = ((Node<E>) parent).tallerChild();
        if (parent.isLeftChild()) {//L
            if (node.isLeftChild()) {//LL
//                right(grand);//LL则右旋
            } else {//LR
                //LR时先左旋平衡父节点，
//                rotateLeft(parent);
                //再右旋祖先节点平衡整棵树
//                rotateRight(grand);
            }
        } else {//R
            if (node.isLeftChild()) {//RL
//                rotateRight(parent);
//                rotateLeft(grand);
            } else {//RR
                leftHanded(grand);//RR则左旋
            }
        }
    }

    @Override
    /**
     * 思路：元素默认是可比较的，所以根据二叉搜索树左子树<根节点<右子树可知
     * 先比较根节点比根小往左搜索，否则往右
     */
    public boolean contains(E e) {
        if (root == null || e == null) {
            return false;
        }
        Node currentNode = root;
        while (currentNode != null) {
            //root比当前值大往左边找
            if (((Comparable<E>) currentNode.e).compareTo(e) > 0) {
                currentNode = currentNode.left;
            } else if (((Comparable<E>) currentNode.e).compareTo(e) < 0) {
                currentNode = currentNode.right;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(E e) {
        remove(node(e));
        return true;
    }

    private void remove(Node<E> node) {
    }

    private Node<E> successor(Node<E> node) {
        Node<E> successor = node;
        while (node != null) {
            successor = node;
            node = node.left;
        }
        return successor;
    }

    private Node<E> node(E e) {
        Node currentNode = root;
        while (currentNode != null) {
            //root比当前值大往左边找
            if (((Comparable<E>) currentNode.e).compareTo(e) > 0) {
                currentNode = currentNode.left;
            } else if (((Comparable<E>) currentNode.e).compareTo(e) < 0) {
                currentNode = currentNode.right;
            } else {
                return currentNode;
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
        {
            Node nodeRoot = root;
            //临时存放节点，结果从上往下要是根进栈根出栈之后是右左
            Stack<Node> src = new Stack<>();
            //存结果 里面的结果需要是从上往下左右根
            Stack<Node> res = new Stack<>();
            //先把根进src，第一次循环把根压入结果栈res中，也即是在栈底
            src.push(nodeRoot);
            while (!src.isEmpty()) {
                Node pop = src.pop();
                //将src栈顶元素取出压入结果栈说明src最后进栈的得是右节点，所以下面的判断先判断pop的左子节点是否为空
                //再判断右子节点才能满足进src之后右节点在上面，
                res.push(pop);
                if (pop.left != null) {
                    src.push(pop.left);
                }
                if (pop.right != null) {
                    src.push(pop.right);
                }
            }
            while (!res.isEmpty()) {
                System.out.println(res.pop().e);
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
            Node nodeRoot = root;
            Stack<Node> nodeStack = new Stack<>();
            while (nodeRoot != null || !nodeStack.isEmpty()) {
                while (nodeRoot != null) {
                    nodeStack.push(nodeRoot);
                    nodeRoot = nodeRoot.left;
                }
                Node pop = nodeStack.pop();
                System.out.println(pop.e);
                nodeRoot = pop.right;
            }
        }
        //非递归
    }

    @Override
    public void levelOrder() {
        if (root == null) {
            return;
        }
        Node nodeRoot = root;
        Queue<Node> queue = new LinkedList();
        queue.offer(nodeRoot);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.e+"高度："+poll.height);
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
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

        BlanceTreeMyselfImpl<Integer> treeMyself = new BlanceTreeMyselfImpl<>();
        treeMyself.add(11);
        treeMyself.add(9);
        treeMyself.add(13);
        treeMyself.add(12);
        treeMyself.add(15);
        treeMyself.add(19);

        System.out.println(treeMyself);
        System.out.println(treeMyself.contains(3));
        System.out.println(treeMyself.contains(4));
        //2538
//        treeMyself.preOrder();
//        System.out.println("上面前序-------");
//        //3852
//        treeMyself.postOrder();
//        System.out.println("上面后序-------");
//        //2358
//        treeMyself.inorderTraversal();
//        System.out.println("上面中序-------");
//        //21538
//        treeMyself.levelOrder();
//        System.out.println("上面层序-------");
        BinaryTrees.print(treeMyself);
        System.out.println();
//        treeMyself.leftHanded();
        treeMyself.levelOrder();
        System.out.println("上面层序-------");
        BinaryTrees.print(treeMyself);
//        TreeMap
    }
}
