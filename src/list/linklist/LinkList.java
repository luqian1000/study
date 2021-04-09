package list.linklist;

import list.ListMyself;

public class LinkList<E> implements ListMyself<E> {
    class Node<E>{
        Node next;
        E element;
    }
    private int size;
    private Node first;


    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "LinkList{" +
                "size=" + size +
                ", first=" + first +
                '}';
    }

    @Override
    public boolean add(Object element) {
        if (first==null){
            first=new Node();
            Node node=new Node();
            node.element=element;
            first.next=node;
            size++;
        }else {
            Node newNode = new Node();
            newNode.element=element;
            Node node=first;
            for (int i = 0; i < size; i++) {
                node=node.next;
            }
            node.next=newNode;
            size++;
        }
        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Node node=first;
        for (int i = 0; i <index; i++) {
            node=node.next;
        }
        Node needRemove = node.next;
        node.next=node.next.next;
        size--;
        return(E) needRemove.element;
    }

    @Override
    public boolean remove(Object o) {
        if (first!=null){
            Node node=first;
            for (int i = 0; i < size; i++) {
                Node prve=node;
                node=node.next;
                if (o!=null&&o.equals(node.element)){
                    Node next = node.next;
                    prve.next=next;
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        Node node=first;
        for (int i = 0; i <= index; i++) {
            node=node.next;
        }
        return (E)node.element;
    }

    private void checkIndex(int index) {
        if (index>=size||index<0){
            throw new RuntimeException("");
        }
    }

    @Override
    public int indexOf(Object element) {
        if(size==0){
            return -1;
        }
        Node node = first.next;
        for (int i = 0; i < size; i++) {
            if (element==null&&node.element==null){
                return i;
            }else if (element.equals(node.element)){
                return i;
            }
            node=node.next;
        }
        return -1;
    }

    @Override
    public E set(int index, Object element) {
        checkIndex(index);
        Node node = first.next;
        for (int i = 0; i < index; i++) {
             node = node.next;
        }
        Object oldValue = node.element;
        node.element=element;
        return (E)oldValue;
    }

    @Override
    public void add(int index, Object element) {
        addCheckIndex(index);
        if (first==null){
            first=new Node();
            Node node=new Node();
            node.element=element;
            first.next=node;
            size++;
        }else {
            //要插入的前一个位置
            Node node=first;
            for (int i = 0; i <= index-1; i++) {
                node=node.next;
            }
            Node newNode = new Node<>();
            newNode.element=element;
            Node prve=node;
            Node next=node.next;
            prve.next=newNode;
            newNode.next=next;
            size++;
        }
    }

    private void addCheckIndex(int index) {
        if (index>size||index<0){
            throw new RuntimeException("");
        }
    }

    public static void main(String[] args) {
        LinkList list=new LinkList();
        list.add("s");
        list.add("2");
        list.add(3);
        list.add(0,"i");
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println(list.remove("2"));
        System.out.println(list.remove("----------"));
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
