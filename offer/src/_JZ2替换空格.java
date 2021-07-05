//请实现一个函数，将一个字符串中的每个空格替换成“%20”。
// 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。

/**
 * 输入
 * "We Are Happy"
 * 返回值
 * "We%20Are%20Happy"
 */
public class _JZ2替换空格 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param s string字符串
     * @return string字符串
     */
    public String replaceSpace (String s) {
        // write code here
        return s.replace(" ","%20");
    }

    public static void main(String[] args) {
        _JZ2替换空格 jz2替换空格 = new _JZ2替换空格();

        System.out.println(jz2替换空格.replaceSpace("We Are Happy"));
    }
}
