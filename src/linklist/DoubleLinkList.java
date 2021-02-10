package linklist;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

public class DoubleLinkList<E> implements List<E> {
    private Node first;
    private Node last;

    private int size;
    private class Node {
        public E element;
        public Node prve;
        public Node next;

        public Node() {
        }

        public Node(E element, Node prve, Node next) {
            this.element = element;
            this.prve = prve;
            this.next = next;
        }
    }

    public DoubleLinkList() {
        first=new Node();
        last=new Node();
        size=0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {

        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        try {
            add(size,e);
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        first=null;
        last=null;
        size=0;

    }

    @Override
    public E get(int index) {
        if (index>=size||index<0){
            throw new IndexOutOfBoundsException("链表目前最大长度是"+size+"要获取的位置是"+index+1);
        }
        if (index>size>>1){

            Node current=last;
            for (int i = size; i > index+1; i--) {
                current=current.prve;
            }
            return current.element;
        }else {
            Node current=first;
            for (int i = 0; i < index; i++) {
                current=current.next;
            }
            return current.element;
        }

    }

    private Node node(int index) {
        if (index>=size||index<0){
            throw new IndexOutOfBoundsException("链表目前最大长度是"+size+"要获取的位置是"+index+1);
        }
        if (index>size>>1){

            Node current=last;
            for (int i = size; i > index+1; i--) {
                current=current.prve;
            }
            return current;
        }else {
            Node current=first;
            for (int i = 0; i < index; i++) {
                current=current.next;
            }
            return current;
        }

    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {
        if (index>size||index<0){
            throw new IndexOutOfBoundsException("链表目前最大长度是"+size+"要插入的位置是"+index+1);
        }
        if (index>size>>1){
            Node current=last;
            for (int i = size; i > index; i--) {
                current=current.prve;
            }
            Node currentPrve=current.prve;
            Node needAddNode=new Node(element,currentPrve,current);
            currentPrve.next=needAddNode;
            current.prve=needAddNode;
            if (size==index){
                last=needAddNode;
            }
            size++;
        }else {
            if (size==0){
                first=new Node(element,null,null);
                last=first;
                size++;
                return;
            }
            Node current=first;
            for (int i = 0; i < index; i++) {
                current=current.next;
            }
            Node currentPrve=current.prve;
            Node needAddNode=new Node(element,currentPrve,current);
            currentPrve.next=needAddNode;
            current.prve=needAddNode;
            if (index==0){
                first=needAddNode;
            }
            size++;

        }

    }

    @Override
    public E remove(int index) {
        Node currentNode = node(index);




        return null;
    }

    @Override



    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    public static void main(String[] args) {
//        Queue queue=new ArrayBlockingQueue
    }
}
