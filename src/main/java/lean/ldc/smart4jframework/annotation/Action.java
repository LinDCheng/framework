package lean.ldc.smart4jframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Action 方法注解
 * @author linDunCheng
 * @since 1.0.0
 * Created by Administrator on 2017/10/26.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

    /**
     * 请求类型路径
     * @return
     */
    String value();
}
