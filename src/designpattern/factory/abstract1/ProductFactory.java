package designpattern.factory.abstract1;

/**
 * 产品工厂接口，生产手机和路由器
 */
public interface ProductFactory {
    IPhoneProduct getIphone();
    IRoutProduct getIrout();
}
