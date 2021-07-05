package designpattern.factory.abstract1;

/**
 * 小米工厂实现产品工厂接口生产手机和路由器
 */
public class XiaomiFactory implements ProductFactory{
    @Override
    public IPhoneProduct getIphone() {
        return new XiaomiPhone();
    }

    @Override
    public IRoutProduct getIrout() {
        return new XiaomRount();
    }
}
