package lean.ldc.smart4jframework.helper;

import lean.ldc.smart4jframework.annotation.Controller;
import lean.ldc.smart4jframework.annotation.Service;
import lean.ldc.smart4jframework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * ClassHelper助手类
 * 在smart.properties配置文件中指定了smart.framework.app.base_package，它是整个应用的基础包名，通过ClassUtil加载的类都需要基于该包名
 * 分别获取应用包名下的所有类，包括所有Service 类，所有Controller类
 * 可以将Controller 注解与Service注解的类所产生的对象理解为Smart框架所管理的Bean
 * 所以有必要再ClassHelper类中增加一个获取应用包名下所有Bean类的方法
 * @author linDunCheng
 * @since 1.0.0
 * Created by Administrator on 2017/10/26.
 */
public final class ClassHelper {

    /**
     * 定义类集合(用户存放所加载的类)
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取应用包名下的所有类
     * @return
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 获取应用包名下所有的Service类
     * @return
     */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if(cls.isAnnotationPresent(Service.class)) { //如果一个类存在注解@Service，返回true
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下所有的Controller类
     * @return
     */
    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Controller.class)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下所有的Bean类
     * @return
     */
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }
}


























