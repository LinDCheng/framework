package lean.ldc.smart4jframework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回视图对象(返回View类型的视图对象，则返回JSP页面;返回Data类型的数据对象，则返回JSON数据)
 * Created by Administrator on 2017/10/30.
 */
public class View {

    /**
     * 视图路径
     */
    private String path;

    /**
     * 模型数据
     */
    private Map<String,Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<String, Object>();
    }

    public View addModel(String key, Object value) {
        model.put(key,value);
        return this;
    }

    public String getPath () {
        return path;
    }

    public Map<String,Object> getModel() {
        return model;
    }
}
