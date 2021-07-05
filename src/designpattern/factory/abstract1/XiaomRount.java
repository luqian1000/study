package designpattern.factory.abstract1;

/**
 * 小米路由器实现路由器产品接口
 */
public class XiaomRount implements IRoutProduct{
    @Override
    public void wifi() {
        System.out.println("小米路由器打开wifi");
    }
}
