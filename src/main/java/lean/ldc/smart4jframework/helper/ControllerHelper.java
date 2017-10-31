package lean.ldc.smart4jframework.helper;

import lean.ldc.smart4jframework.annotation.Action;
import lean.ldc.smart4jframework.bean.Handler;
import lean.ldc.smart4jframework.bean.Request;
import lean.ldc.smart4jframework.util.ArrayUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器助手类
 * 通过ClassHelper，获取所有定义了Controller注解的类，通过反射获取该类中所有带有Action注解的方法，得到注解中的请求表达式,
 * 进而获取请求方法与请求路径，封装一个请求对象(Request) 与处理对象(Handler)，最后将Request与Handler建立一个映射关系,
 * 放入一个Action Map中
 * Created by Administrator on 2017/10/30.
 */
public final class ControllerHelper {

    /**
     * 用于存放请求与处理器的映射关系
     */
    private static final Map<Request,Handler> ACTION_MAP = new HashMap<Request,Handler>();

    static {
        //获取所有的 Controller 类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtils.isNotEmpty(controllerClassSet)) {
            //遍历这些 Controller 类
            for (Class<?> controllerClass : controllerClassSet ) {
                //获取 Controller 中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if(ArrayUtil.isNotEmpty(methods)) {
                    //遍历 Controller 中的方法
                    for (Method method : methods) {
                        //判断当前方法是否带有 Action 注解
                        if (method.isAnnotationPresent(Action.class)) {
                            //从 Action 注解中获取URL映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            //验证 URL 映射规则
                            if (mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if(ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    //获取请求方法与请求路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod,requestPath);
                                    Handler handler = new Handler(controllerClass,method);
                                    //初始化ACTION_MAP
                                    ACTION_MAP.put(request,handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取Handler
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getHandler ( String requestMethod, String requestPath) {
        Request request = new Request(requestMethod,requestPath);
        return ACTION_MAP.get(request);
    }
}





















