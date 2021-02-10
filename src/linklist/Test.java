package linklist;

import java.util.LinkedList;
import java.util.List;

public class Test {
//    List<String> list=new DoubleLinkList<String>();
//    LinkedList


    public static void main(String[] args) {

        DoubleLinkList<String> strings=new DoubleLinkList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        String s = strings.get(2);
        System.out.println(strings);
        System.out.println(s);

    }
}
