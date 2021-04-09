package tree;

import java.util.Stack;
import java.util.TreeSet;

public class TreeMyselfImpl<E> implements TreeMyself<E>{
    private int size;
    private Node root;

    class Node<E>{
        E e;
        Node<E> left;
        Node<E> right;
        Node<E> parent;
        public Node(Node parent,E e){
            this.parent=parent;
            this.e=e;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {
        if (e==null){
            return false;
        }
        if (root==null){
            Node newNode=new Node(null,e);
            root=newNode;
            size++;
        }else {
            Node parentNode=null;
            Node addLocation=root;
            int compareResult=0;
            while (addLocation!=null){
                parentNode=addLocation;
                 compareResult= ((Comparable<E>) parentNode.e).compareTo(e);
                if (compareResult>0){
                    addLocation = parentNode.left;
                }else if (compareResult<0){
                    addLocation = parentNode.right;
                }else {
                    parentNode.e=e;
                    return true;
                }
            }
            if (compareResult>0){
                parentNode.left=new Node(parentNode,e);
            }else if (compareResult<0){
                parentNode.right=new Node(parentNode,e);
            }
            size++;
        }
        return true;
    }

    @Override
    public boolean contains(E e) {

        if(root==null||e==null){
            return false;
        }
        Node currentNode=root;
        while (currentNode!=null){
            //root比当前值大往左边找
            if (((Comparable<E>)currentNode.e).compareTo(e)>0){
                currentNode=currentNode.left;
            }else if (((Comparable<E>)currentNode.e).compareTo(e)<0){
                currentNode=currentNode.right;
            }else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean remove(E e) {
        return false;
    }
    private void preOrderTraversal(Node node){
        if (node!=null){
            System.out.println(node.e);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }
    @Override
    public void preOrder() {
        //递归实现 begin
        {
//            preOrderTraversal(root);
        }
        //递归实现 stop
        //非递归实现 begin
        {
            Node nodeRoot=root;
            Stack<Node> nodeStack=new Stack();
            while (nodeRoot!=null||!nodeStack.isEmpty()){
                while (nodeRoot!=null){
                    nodeStack.push(nodeRoot);
                    System.out.println(nodeRoot.e);
                    nodeRoot=nodeRoot.left;
                }
                Node pop = nodeStack.pop();
                nodeRoot=pop.right;
            }
        }
        //非递归实现 stop
    }

    @Override
    //后序遍历
    public void postOrder() {

        //递归实现 begin
        {
//            postOrderTraversal(root);
        }
        //递归实现 stop
        //非递归实现 begin
        {
            Node nodeRoot=root;
            Stack<Node> src=new Stack<>();
            Stack<Node> res=new Stack<>();
            src.push(nodeRoot);
            while (!src.isEmpty()){
                Node pop = src.pop();
                res.push(pop);
                if (pop.left!=null){
                    src.push(pop.left);
                }
                if (pop.right!=null){
                    src.push(pop.right);
                }
            }
            while (!res.isEmpty()){
                System.out.println(res.pop().e);
            }

        }
        //非递归实现 stop

    }

    private void postOrderTraversal(Node root) {
        if (root!=null){
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.println(root.e);
        }
    }

    @Override
    public void inorderTraversal() {
        //递归
        {
//            inorderTraversal(root);
        }
        //递归

        //非递归
        {
            Node nodeRoot=root;
            Stack<Node> nodeStack=new Stack<>();
            while (nodeRoot!=null||!nodeStack.isEmpty()){
                while (nodeRoot!=null){
                    nodeStack.push(nodeRoot);
                    nodeRoot=nodeRoot.left;
                }
                Node pop = nodeStack.pop();
                System.out.println(pop.e);
                nodeRoot=pop.right;
            }
        }
        //非递归
    }

    private void inorderTraversal(Node root) {
        if (root!=null){
            inorderTraversal(root.left);
            System.out.println(root.e);
            inorderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        TreeMyselfImpl<String> treeMyself=new TreeMyselfImpl<>();
        treeMyself.add("2");
        treeMyself.add("5");
        treeMyself.add("3");
        treeMyself.add("8");
        System.out.println(treeMyself);
        System.out.println(treeMyself.contains("3"));
        System.out.println(treeMyself.contains("4"));
        //2538
        treeMyself.preOrder();
        System.out.println("上面前序-------");
        //3852
        treeMyself.postOrder();
        System.out.println("上面后序-------");
        //2358
        treeMyself.inorderTraversal();
        System.out.println("上面中序-------");

    }
}
