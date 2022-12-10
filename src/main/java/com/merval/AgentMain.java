package com.merval;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class AgentMain {
    public static void premain (String args, Instrumentation instrumentation) {
        //instrumentation.addTransformer(new HelloTransFormer(),true);
        System.out.println("initPremain2");
        Byte(instrumentation);

    }
    public static void premain (String args) {

    }
    public static void Byte(Instrumentation instrumentation){
        try {
            AgentBuilder.Identified.Extendable builder1 = new AgentBuilder.Default()
                    .type(ElementMatchers.nameContains("com.example.dome.HelloTest"))

                    .transform((builder, typeDescription, classLoader, javaModule) ->{
                        return builder.method(ElementMatchers.any())
                                .intercept(MethodDelegation.to(SpringControllerInterceptor.class));
                    });
            builder1.installOn(instrumentation);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
