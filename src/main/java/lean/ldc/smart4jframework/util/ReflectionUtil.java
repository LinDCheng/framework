package lean.ldc.smart4jframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * Created by Administrator on 2017/10/26.
 */
public final class ReflectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建类实例
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls) {
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            logger.error("new instance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     * @param object
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object object, Method method,Object... args) {
        Object result;
        try {
            method.setAccessible(true);//指示反射的对象在使用时应该取消 Java 语言访问检查,实际上setAccessible是启用和禁用访问安全检查的开关,并不是为true就能访问为false就不能访问
            result =  method.invoke(object,args);
        } catch (Exception e) {
            logger.error("invoke method failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     * @param object
     * @param field
     * @param value
     */
    public static void setField(Object object, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(object,value);
        }catch (Exception e) {
            logger.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }
}

























