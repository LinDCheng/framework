package lean.ldc.smart4jframework.helper;

import lean.ldc.smart4jframework.annotation.Inject;
import lean.ldc.smart4jframework.util.ReflectionUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类(IoC框架中所管理的对象都是单利的，由于IoC框架底层是从BeanHelper中获取Bean Map，而Bean Map中的对象都是事先创建好放入这个Bean容器的，所有的对象都是单例的)
 * 开发者不是自己通过new的方式来实例化,而是通过框架自身来实例化,这个过程叫做IoC--控制反转。控制不是由开发者决定，而是反转给框架
 * 一般，将控制反转也成为DI(依赖注入),可以理解为将某个类需要依赖的成员注入到这个类中。
 * 实现：
 * 先通过BeanHelper获取所有的Bean Map(是一个Map<Class<?>,Object>结构，记录了类与对象的映射关系)，然后遍历这个映射关系,分别
 * 取出Bean 类与Bean实例，进而通过反射获取类中所有的成员变量。继续遍历这些成员变量，判断变量是否带有Inject注解，有的话，从Bean Map
 * 中根据Bean类取出Bean实例，最后通过ReflectUtil 的 setField方法修改当前成员变量的值
 * Created by LinDunCheng on 2017/10/30.
 */
public final class IocHelper {

    static {
        //获取所有 Bean 类与 Bean 实例之间的映射关系
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if( beanMap != null ) {
            for (Map.Entry<Class<?>,Object>beanEntry : beanMap.entrySet() ) {
                //从 BeanMap 中获取 Bean 类与 Bean 实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //获取 Bean 类定义的所有成员变量 (Bean Field)
                Field [] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtils.isNotEmpty(beanFields)) {
                    //遍历 Bean Field
                    for (Field beanField : beanFields) {
                        //判断 Bean Field 是否带有 Inject 注解
                        if ( beanField.isAnnotationPresent(Inject.class) ) {
                            //在 Bean Map 中获取 Bean Field 对应的实例
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if ( beanFieldInstance != null ) {
                                //通过反射初始化 BeanField 的值
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
