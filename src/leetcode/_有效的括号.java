package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 *
 * 输入：s = "{[]}"
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 */
public class _有效的括号 {

    public boolean isValid(String s) {
        if (s.length()%2!=0){
            return false;
        }
        String a="(";
        String b=")";
        String c="[";
        String d="]";
        String e="{";
        String f="}";
        List<String> strings = Arrays.asList(s.split(""));
        List<String> leftBracket=new ArrayList<>();

        for (String string : strings) {
            if (a.equals(string)||c.equals(string)||e.equals(string)){
                leftBracket.add(string);
                continue;
            }
            if (leftBracket.size()<=0){
                return false;
            }
            String leftTop = leftBracket.get(leftBracket.size() - 1);

            if (b.equals(string)){
                if (a.equals(leftTop)){
                    leftBracket.remove(leftBracket.size() - 1);
                    continue;
                }
            } if (d.equals(string)){
                if (c.equals(leftTop)){
                    leftBracket.remove(leftBracket.size() - 1);
                    continue;
                }
            } if (f.equals(string)){
                if (e.equals(leftTop)){
                    leftBracket.remove(leftBracket.size() - 1);
                    continue;
                }
            }
            return false;
        }
        if(leftBracket.size()>0){
            return false;
        }
        return true;
    }
    public boolean isValid2(String s) {
        if(s.isEmpty())
            return true;
        Stack<Character> stack=new Stack<Character>();
        for(char c:s.toCharArray()){
            if(c=='(')
                stack.push(')');
            else if(c=='{')
                stack.push('}');
            else if(c=='[')
                stack.push(']');
            else if(stack.empty()||c!=stack.pop())
                return false;
        }
        if(stack.empty())
            return true;
        return false;
    }

    public static void main(String[] args) {
        _有效的括号 valid=new _有效的括号();
        boolean valid1 = valid.isValid("){");
        System.out.println(valid1);
    }

}
