package DynamicProxy;

/**
 * Created by chenbin@megaeyes.com on 2018/3/22 0022.
 */
@AspectX
public class AdviceCaculator {
    //关注的方法名
    @Point("calculate")
    private String methodname(){
        return null;
    }
    //before
    @Around
    public void pre(int c,int d){
        System.out.println((System.nanoTime()));
    }
    //after
//    @After
    public void aft(){
        System.out.println("***********后*************");
    }
}
