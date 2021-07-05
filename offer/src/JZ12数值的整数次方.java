/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 *
 * 保证base和exponent不同时为0。不得使用库函数，同时不需要考虑大数问题，也不用考虑小数点后面0的位数。
 *
 *
 * 输入
 * 2.00000,3
 * 返回值
 * 8.00000
 */
public class JZ12数值的整数次方 {


    public static void main(String[] args) {
        JZ12数值的整数次方 jz12数值的整数次方=new JZ12数值的整数次方();
        double power = jz12数值的整数次方.Power(2.00000, 1);
        System.out.println(power);
    }
    public double Power(double base, int exponent) {
        if (base==0&&exponent==0){
            throw new RuntimeException("不能同时为0");
        }
        if (exponent==0){
            return 1;
        }
        double result=1;
        for (int i = 0; i < exponent; i++) {
            result=base*result;
        }
        return result;

    }
}
