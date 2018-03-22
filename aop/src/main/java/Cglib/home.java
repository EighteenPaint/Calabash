package Cglib;

/**
 * Created by chenbin@megaeyes.com on 2018/3/22 0022.
 */
public class home {
    public static void main(String[] args) {
        CGlibProxyFactory factory = new JavaServiceBean();
        JavaServiceBean service = (JavaServiceBean) factory.createProxyInstance(factory);
        service.save("888");
    }
}
