package lean.ldc.smart4jframework;

import lean.ldc.smart4jframework.bean.Data;
import lean.ldc.smart4jframework.bean.Handler;
import lean.ldc.smart4jframework.bean.Param;
import lean.ldc.smart4jframework.bean.View;
import lean.ldc.smart4jframework.helper.BeanHelper;
import lean.ldc.smart4jframework.helper.ConfigHelper;
import lean.ldc.smart4jframework.helper.ControllerHelper;
import lean.ldc.smart4jframework.util.*;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * MVC框架中最核心的类
 * Created by Administrator on 2017/10/30.
 */
@WebServlet(urlPatterns = "/*",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet{

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化相关的 Helper 类
        HelperLoader.init();
        //获取 ServletContext 对象(用于注册Servlet)
        ServletContext servletContext = servletConfig.getServletContext();
        //注册处理 JSP 的 Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        //处理静态资源的默认 Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    public void service (HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        //获取请求方法和请求路径
        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();
        //获取 Action 处理器
        Handler handler = ControllerHelper.getHandler(requestMethod,requestPath);
        if( handler != null ) {
            //获取 Controller 类及其 Bean 实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            //创建请求参数对象
            Map<String,Object> paramsMap = new HashMap<String,Object>();
            Enumeration<String> paramsNames = request.getParameterNames();
            while (paramsNames.hasMoreElements()) {
                String paramName = paramsNames.nextElement();
                String paramValue = request.getParameter(paramName);
                paramsMap.put(paramName, paramValue);
            }
            String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
            if (StringUtils.isNotEmpty(body)) {
                String[] params = StringUtils.split(body,"&");
                if(ArrayUtil.isNotEmpty(params)) {
                    for (String param : params) {
                        String[] array = StringUtils.split(param,"=");
                        if(ArrayUtil.isNotEmpty(array) && array.length==2) {
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramsMap.put(paramName,paramValue);
                        }
                    }
                }
            }
            Param param = new Param(paramsMap);
            //调用 Action 方法
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean,actionMethod,param);
            //处理 Action 方法返回值
            if (result instanceof View) {
                //返回 JSP 页面
                View view = (View) result;
                String path = view.getPath();
                if (StringUtils.isNotEmpty(path)) {
                    if (path.startsWith("/")) {
                        response.sendRedirect(request.getContextPath() + path);
                    } else {
                        Map<String,Object> model = view.getModel();
                        for (Map.Entry<String,Object> entry : model.entrySet()) {
                            request.setAttribute(entry.getKey(),entry.getValue());
                        }
                        request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request,response);
                    }
                }
            }else if (request instanceof Data) {
                //返回 JSON 数据
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null ) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter writer = response.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }
    }
}





























