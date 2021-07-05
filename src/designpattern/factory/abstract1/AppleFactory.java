package designpattern.factory.abstract1;
/**
 * 苹果工厂实现产品工厂接口生产手机和路由器
 */
public class AppleFactory implements ProductFactory{
    @Override
    public IPhoneProduct getIphone() {
        return new AppleiPhone();
    }

    @Override
    public IRoutProduct getIrout() {
        return new AppleRount();
    }
}
