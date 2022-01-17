package cn.bugstack.guide.idea.plugin;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.Callable;

public class MonitorMethod {

    @RuntimeType
    public static Object intercept(@This Object obj, @Origin Method method, @SuperCall Callable<?> callable, @AllArguments Object... args) throws Exception {
        try {
            return callable.call();
        } finally {

            String originalSql = (String) BeanUtil.getFieldValue(obj, "originalSql");
            String replaceSql = ReflectUtil.invoke(obj, "asSql");

            System.out.println("数据库名称：Mysql");
            System.out.println("线程ID：" + Thread.currentThread().getId());
            System.out.println("时间：" + new Date());
            System.out.println("原始SQL：\r\n" + originalSql);
            System.out.println("替换SQL：\r\n" + replaceSql);
        }
    }

}
