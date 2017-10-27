package lean.ldc.smart4jframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控制器类的 Controller 注解
 * @author linDunCheng
 * @since 1.0.0
 * Created by Administrator on 2017/10/26.
 */
@Target(ElementType.TYPE) //@Target用于描述注解的使用范围 取值ElementType.TYPE表示 用于描述类、接口(包括注解类型) 或enum声明
//@Retention 定义了该Annotation被保留的时间长短,可以对 Annotation的“生命周期”限制,.RUNTIME:在运行时有效（即运行时保留）
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}
