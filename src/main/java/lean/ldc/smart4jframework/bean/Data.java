package lean.ldc.smart4jframework.bean;

/**
 * 返回数据对象
 * 封装了一个Object 类型的模型数据,框架会将该对象写入HttpServletResponse对象中,直接输出至浏览器
 * Created by Administrator on 2017/10/30.
 */
public class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Data (Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
