package com.merval;


import net.bytebuddy.implementation.bind.annotation.*;


import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class SpringInterceptor {
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @AllArguments Object[] args,@SuperCall Callable callable) {
        long start = System.currentTimeMillis() ;

        String beanName="";
        try {
            if (args!=null && args[0]!=null&&args[0] instanceof String){
                beanName= (String) args[0];
            }
            return callable.call();
        } catch(Exception e) {

            System.out.println(e);
        } finally {
            long end = System.currentTimeMillis();
            System.out.println("spring  ben 拦截启动: beanName " +beanName  +""+ "耗时"+(end - start));
        }
        return null;
    }
}
