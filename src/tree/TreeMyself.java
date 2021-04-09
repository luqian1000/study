package tree;

import java.util.TreeSet;

public interface TreeMyself<E> {
    int size();
    boolean add(E e);
    boolean contains(E e);
    boolean isEmpty();
    boolean remove(E e);
    //前序遍历
    void preOrder();
    //后序遍历
    void postOrder();
    //中序遍历
    void inorderTraversal();


}
