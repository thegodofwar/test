


import static com.sun.btrace.BTraceUtils.print;
import static com.sun.btrace.BTraceUtils.printArray;
import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;

@BTrace
public class BTraceScript {

    /**
     * 此方法打印出Test类中的mergeArray(Long[] arrayOne, Long[] arrayTwo)方法传入的参数
     * 参数名字一定要和监控对象的方法参数名字一致
     * 
     * @param arrayOne 监控参数一
     * @param arrayTwo 监控参数二
     * @author jerry
     */
    @OnMethod(clazz = "com.pw.spider.Util.BTraceTest", method = "mergeArray")
    // 此处写明要监控的包、类、方法等  可以使用正则匹配
    public static void anyRead(Long[] arrayOne, Long[] arrayTwo) {
        // 打印监控的类名
        print("  [");
        // 打印监控的方法名
        println("]");

        if (arrayOne != null) {
            printArray(arrayOne);
        } else {
            println("the arguments is null!");
        }

        if (arrayTwo != null) {
            printArray(arrayTwo);
        } else {
            println("the arguments is null!");
        }
    }

}
