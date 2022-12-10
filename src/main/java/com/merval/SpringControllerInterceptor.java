package com.merval;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;


public class SpringControllerInterceptor {



    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @AllArguments Object[] args,
                                   @SuperCall Callable<?> callable) {
        System.out.println("before controller: {}"+method.getName());

        long start = System.currentTimeMillis() ;
        try {
            Object res = callable.call();

            for(Object i:args){
                System.out.println(i);
                if(i instanceof HttpServletRequest){
                    System.out.println("是http请求");
                    HttpServletRequest request=(HttpServletRequest) i;
                    String traceId = request.getHeader("traceId");

                    System.out.println(method.getName()+traceId);
                }
            }
            return res;
        } catch(Exception e) {

            System.out.println(e);
        } finally {
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
        return null;
    }
}
