package designpattern.factory.abstract1;
/**
 * 苹果路由器实现路由器产品接口
 */
public class AppleRount implements IRoutProduct{
    @Override
    public void wifi() {
        System.out.println("苹果路由器打开wifi");
    }
}
