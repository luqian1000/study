package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeSet;

/**
 * 二叉搜索树
 * @param <E>
 */
public class TreeMyselfImpl<E> implements TreeMyself<E> {
    private int size;
    private Node root;

    class Node<E> {
        E e;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(Node parent, E e) {
            this.parent = parent;
            this.e = e;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            return false;
        }
        if (root == null) {
            Node newNode = new Node(null, e);
            root = newNode;
            size++;
        } else {
            Node parentNode = null;
            Node addLocation = root;
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
            if (compareResult > 0) {
                parentNode.left = new Node(parentNode, e);
            } else if (compareResult < 0) {
                parentNode.right = new Node(parentNode, e);
            }
            size++;
        }
        return true;
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
        if (node == null) {
            return;
        }
        //移除度为二的节点  注意⚠️：这里有一个地方需要特别注意，当移除度为2的节点a时势必要有一个节点来接替这个位置，
        // 所以这个地方一个重要的思路是
        //不删除该度为2的节点a，而是找到该节点的前驱或后继（中序遍历的结果）节点b的值覆盖掉该度为2的节点a的值
        //之后再将节点b（度只能1或0）的节点删除
        if (node.left != null && node.right != null) {
            Node<E> successor = successor(node);
            //用后继节点的值代替要删除的度为2的节点的值
            node.e = successor.e;
            //将后继节点赋给node继续下面的逻辑删除后继节点，后继节点度必定为1或0
            node = successor;
        }
        if (node.parent == null) {
            root = null;
        } else if (node.right != null || node.left != null) {
            if (node.right != null) {
                if (node.parent.left == node) {
                    node.parent.left = node.right;
                } else {
                    node.parent.right = node.right;
                }
            } else {
                if (node.parent.left == node) {
                    node.parent.left = node.left;
                } else {
                    node.parent.right = node.left;
                }
            }
        } else {
            if (node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
        size--;
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
            System.out.println(poll.e);
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

//        TreeSet
        TreeMyselfImpl<Integer> treeMyself = new TreeMyselfImpl<>();
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
        treeMyself.remove(9);
        treeMyself.levelOrder();
//        TreeMyselfImpl<String> test=new TreeMyselfImpl<>();
//        test
        //2
        //2
//        35
        //2

    }
}
