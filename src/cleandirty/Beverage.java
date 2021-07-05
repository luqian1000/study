package cleandirty;

import java.math.BigDecimal;

/**
 * @author fan_rc@suixingpay.com
 * @description 饮料抽象类
 * @date 2019/9/17 20:53
 */
public abstract class Beverage {

    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    /**
     * cost方法是用来返回饮料的价钱（需在具体类中自己实现）
     *
     * @return
     */
    public abstract BigDecimal cost();
}