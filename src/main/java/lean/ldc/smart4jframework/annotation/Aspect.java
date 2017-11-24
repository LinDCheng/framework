package lean.ldc.smart4jframework.annotation;

import java.lang.annotation.*;

/**
 * 自定义AOP
 * 切面注解
 * Created by LinDunCheng on 2017/11/8.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 注解
     * @return
     */
    Class<? extends Annotation> value();
}
