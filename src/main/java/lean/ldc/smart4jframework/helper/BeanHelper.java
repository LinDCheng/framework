package lean.ldc.smart4jframework.helper;

import lean.ldc.smart4jframework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean助手类
 * 获取所有被Smart框架管理的Bean类，此时需要调用ClassHelper类的getBeanClassSet方法
 * 然后需要循环调用ReflectionUtil类的newInstance方法，来实例化对象。
 * 最后将每次创建的对象存放在一个静态的Map<Class<?>,Object> 中。
 * 我们需要随时通过该Map的key(类名)去获取所对应的value(Bean对象)
 * Created by Administrator on 2017/10/26.
 */
public final class BeanHelper {

    /**
     * 定义 Bean 映射（用于存放 Bean 类与 Bean 实例的映射关系）
     */
    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> cls : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(cls);//实例化对象
            BEAN_MAP.put(cls,obj);
        }
    }

    /**
     * 获取 Bean 映射
     * @return
     */
    public static Map<Class<?>,Object> getBeanMap() {
        return BEAN_MAP;
    }

    public static <T> T getBean() {

    }
}
