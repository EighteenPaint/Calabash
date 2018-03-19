import com.hbh.TestPO;
import com.sun.xml.internal.ws.org.objectweb.asm.Type;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by chenbin@megaeyes.com on 2018/3/7 0007.
 */
public class TTTT {

        public static void dealArray(int... intArray) {
            for (int i : intArray)
                System.out.print(i + " ");

            System.out.println();
        }

        public static void main(String args[]) {
            int[] intArray = { 1, 2, 3 };
            Object i = 9;
            dealArray(intArray);// 通过编译，正常运行
            System.out.println(i instanceof Number);
        }

}
