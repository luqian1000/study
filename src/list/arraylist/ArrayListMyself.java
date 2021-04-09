package list.arraylist;

import list.ListMyself;

import java.util.*;

public class ArrayListMyself<E> implements ListMyself<E> {
    //长度
    private int size;
    //存放数据的数组
    private Object[] elements;
    //初始数组长度
    private final static int CAPACITY=10;
    //空数组，用于空构造
    private final static Object[] EMPTY_ELEMENT= {};
    public ArrayListMyself(){
        this(0);
    }
    public ArrayListMyself(int capacity){
        if (capacity>0){
            elements=new Object[capacity];
        }else if (capacity==0){
            elements=EMPTY_ELEMENT;
        }else {
            throw new RuntimeException("初始化容量有误");
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E element) {
        checkCapacity();
        elements[size++]=element;
        return true;
    }
    //检查容量 扩容
    private void checkCapacity() {
        if (elements.length==0){
            elements=new Object[CAPACITY];
        }else if (elements.length==size){
            //扩容
            elements = Arrays.copyOf(elements, elements.length + elements.length >> 1);
        }
    }

    @Override
    public E remove(int index) {
        checkindex(index);
        E oldElement =(E) elements[index];
        for (int i=index;i<size;i++){
            elements[i-1]=elements[i];
        }
        elements[index]=null;
        size--;
        return oldElement;
    }

    private void checkindex(int index) {
        if (index>=size||index<0){
            throw new IndexOutOfBoundsException("越界");
        }
    }

    @Override
    public boolean remove(E e) {
        if (e==null){
            for (int i = 0; i < size; i++) {
                if (elements[i]==null){
                    fastRemove(i);
                    return true;
                }
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (e.equals(elements[i])){
                    fastRemove(i);
                    return true;
                }
            }
        }

        return false;
    }

    private void fastRemove(int index) {
        for (int i=index;i<size;i++){
            elements[i-1]=elements[i];
        }
        elements[index]=null;
        size--;
    }

    @Override
    public E get(int index) {
        checkindex(index);
        return (E)elements[index];
    }

    @Override
    public int indexOf(E element) {
        if (size>0){
            if (element==null){
                for (int i = 0; i < size; i++) {
                    if (elements[i]==element){
                        return i;
                    }
                }
            }else {
                for (int i = 0; i < size; i++) {
                    if (element.equals(elements[i])){
                        return i;
                    }
                }
            }

        }
        return -1;
    }

    @Override
    public E set(int index, E element) {
       checkindex(index);
        Object oldValue = elements[index];
        elements[index]=element;

        return (E)oldValue;
    }

    @Override
    //这个看下，从最后一个往后挪，跟remove把某个挖空往前挪一样
    public void add(int index, E element) {
        if (index>size||index<0){
            throw new IndexOutOfBoundsException("越界");
        }
        checkCapacity();
        size++;
        for (int i = size-2; i >=index; i--) {
            elements[i+1]=elements[i];
        }

        elements[index]=element;
    }

    @Override
    public String toString() {
        return "ArrayListMyself{" +
                "size=" + size +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }

    public static void main(String[] args) {
//        Vector
//        ArrayList<String> s=new ArrayList<>();
//        s.add(1,"");
//        ArrayListMyself<String> arrayListMyself=new ArrayListMyself<>();
////        arrayListMyself.add("2");
//        arrayListMyself.add("7");
//        arrayListMyself.add(1,"5");
//        System.out.println(arrayListMyself);
//        Stack stack=new Stack();
        Deque deque=new ArrayDeque();
        deque.push("e");
        deque.push("s");
        deque.push("m");
//        LinkedList


        deque.add("j");
        System.out.println(deque.pop());
        System.out.println(deque.pop());
        System.out.println(deque.pop());
        System.out.println(deque.pop());
    }
}
