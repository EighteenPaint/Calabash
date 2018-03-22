package Cglib;

/**
 * Created by chenbin@megaeyes.com on 2018/3/22 0022.
 */
public class JavaServiceBean extends CGlibProxyFactory{
    private String user = null;


    public void save(String name) {
        System.out.println("我是save()方法");
    }

    public void update(String name, Integer personid) {
        System.out.println("我是update()方法");
    }

    public String getPersonName(Integer personid) {
        System.out.println("我是getPersonName()方法");
        return "xxx";
    }

    @Override
    Object before(Object... args) {
        System.out.println("***********调用**之前**********");
        return null;
    }

    @Override
    Object after(Object... args) {
        System.out.println("***********调用**之后**********");
        return null;
    }
}
