package stack;

import tree.TreeMyselfImpl;
import tree.TreeMyselfImpl2;

import java.util.ArrayList;

public class StackMyselfImpl<E> implements StackMyself<E>{
    private int size;
    private Object[]elements;
    private  final static int DEFAULT_CAPACITY=10;
    public StackMyselfImpl(){
        elements= new Object[DEFAULT_CAPACITY];
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean push(E element) {

        return false;
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E top() {
        return null;
    }

    public static void main(String[] args) {
//        ArrayList;
    }
}
