package cn.bugstack.guide.idea.plugin;


import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class PreAgent {

    //JVM 首先尝试在代理类上调用以下方法
    public static void premain(String agentArgs, Instrumentation inst) {
        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> {
            return builder
                    .method(ElementMatchers.named("executeInternal")) // 拦截任意方法
                    .intercept(MethodDelegation.to(MonitorMethod.class)); // 委托
        };

        new AgentBuilder
                .Default()
                .type(ElementMatchers.nameStartsWith("com.mysql.jdbc.PreparedStatement"))
                .transform(transformer)
                .installOn(inst);
    }

    //如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
    public static void premain(String agentArgs) {
    }

}
