package tree;

import tree.printer.BinaryTreeInfo;
import tree.printer.BinaryTrees;

import java.util.*;

/**
 * 二叉搜索树
 * @param <E>
 */
public class TreeMyselfImpl2<E> implements TreeMyself<E> , BinaryTreeInfo {
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

        public Node(Node parent, E e) {
            this.parent = parent;
            this.e = e;
        }
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
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    /**
     * 思路：
     * 1、如果树为空，直接创建元素值为e的新节点并赋给root
     * 2、不为空则从根节点开始比较元素值，比根节点小往左插入，比根小往右插入，
     * 左右不为空时循环跟左或右比较判断插入的位置，直到左或右为空插入到合适位置
     */
    public boolean add(E e) {
        //判空
        if (e==null){
            return false;
        }
        if (size==0){
            root=new Node(null,e);
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
        }
        size++;
        return true;
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
        return size==0;
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

//            if (childAmount<2){
//                Node replceNode=node.left!=null?node.left:node.right;
//                if (childAmount==1){
//                    replceNode.parent=node.parent;
//
//                }
//            }else {
//                Node successor=successor(node);
//                node.e=successor.e;
//                remove(successor);
//            }

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
                //度为1
            }else if(childAmount==1){
                //删除node节点后要维护的其子节点
                Node replceNode=node.left!=null?node.left:node.right;
                //如果要删除的节点是叶子节点且它的度为1则把要删除的节点的父节点赋给要维护的子节点的parent变量
                if (node.isRoot()){
                   replceNode.parent=node.parent;
                }else if (node.isRight()){
                    node.parent.right=replceNode;
                    //node.isLeft()
                }else {
                    node.parent.left=replceNode;
                }
                size--;
                //度为2
            }else {
                Node successor=successor(node);
                node.e=successor.e;
                remove(successor);
            }

        }
        return false;
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

    private int getChildAmount(Node node) {
        if (node.left!=null&&node.right!=null){
            return 2;
        }else if (node.left!=null||node.right!=null){
            return 1;
        }else {
            return 0;
        }

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
        root=null;
        size=0;

    }

    @Override
    /**
     * 前序遍历
     * 1、从根开始遍历，一直往左，打印输出根节点的值，同时保留遍历中的子树的根节点到栈里，用于遍历完左节点遍历右节点
     *
     */
    public void preOrder() {
        if (size==0){
            return;
        }
        Node childRootNode=root;
        Stack stack=new Stack();
        while (childRootNode!=null||!stack.isEmpty()){
            while (childRootNode!=null){
                stack.add(childRootNode);
                System.out.println(childRootNode.e);
                childRootNode=childRootNode.left;
            }
            Node pop = (Node)stack.pop();
            childRootNode=pop.right;
        }
    }

    @Override
    /**
     * 后续遍历
     * 思路，用两个栈，一个栈用来存放遍历过的根节点方便回去，另一个是结果栈，结果栈从上到下的顺序最后要是实际结果的倒序。
     * 后序遍历的方法是左->右->根，那存放到结果栈的数据从上往下应该是根->右->左，而根->右->左又可以近似看成前序遍历
     * ⚠️相当于把前序遍历先访问右节点的结果（正常前序遍历是根->左->右，现在是根->右->左）存到结果栈里，
     * 结果栈再取出来就是后序遍历的结果
     */
    public void postOrder() {
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


    @Override
    public void inorderTraversal() {
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

    @Override
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




    public static void main(String[] args) {
//        TreeSet
        TreeMyselfImpl2<Integer> treeMyself = new TreeMyselfImpl2<>();
        //2,5,3,8,1,9,15,12
        treeMyself.add(2);
        treeMyself.add(5);
        treeMyself.add(3);
        treeMyself.add(8);
        treeMyself.add(1);
        treeMyself.add(9);
        treeMyself.add(15);
        treeMyself.add(12);
        System.out.println(treeMyself);
        System.out.println(treeMyself.contains(3));
        System.out.println(treeMyself.contains(4));
        //2538
        treeMyself.preOrder();
        System.out.println("上面前序-------");
        //3852
        treeMyself.postOrder();
        System.out.println("上面后序-------");
        //2358
        treeMyself.inorderTraversal();
        System.out.println("上面中序-------");
        //21538
        treeMyself.levelOrder();
        System.out.println("上面层序-------");
        System.out.println("长度"+treeMyself.size());
        BinaryTrees.print(treeMyself);
        System.out.println();
        treeMyself.remove(2);
        System.out.println("长度"+treeMyself.size());
        treeMyself.levelOrder();
        BinaryTrees.print(treeMyself);
//        TreeMyselfImpl<String> test=new TreeMyselfImpl<>();
//        test
        //2
        //2
//        35
        //2

    }
}
