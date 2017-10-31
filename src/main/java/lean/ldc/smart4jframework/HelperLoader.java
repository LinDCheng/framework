package lean.ldc.smart4jframework;

import lean.ldc.smart4jframework.helper.BeanHelper;
import lean.ldc.smart4jframework.helper.ClassHelper;
import lean.ldc.smart4jframework.helper.ControllerHelper;
import lean.ldc.smart4jframework.helper.IocHelper;
import lean.ldc.smart4jframework.util.ClassUtil;

/**
 * 初始化框架--加载ClassHelper,BeanHelper,IoCHelper,ControllerHelper(加载相应的Helper类，实际上就是加载它们的静态块)
 * Created by LinDunCheng on 2017/10/30.
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classes = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for (Class<?> cls : classes ) {
            ClassUtil.loadClass(cls.getName(),false);
        }
    }

}
