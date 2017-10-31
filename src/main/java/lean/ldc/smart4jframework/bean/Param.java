package lean.ldc.smart4jframework.bean;

import java.util.Map;

/**
 * 请求参数对象
 * 从HttpServletRequest对象中获取的所有请求参数,初始化到Param对象中
 * Created by LinDunCheng on 2017/10/30.
 */
public class Param {

    private Map<String,Object> paramsMap;

    public Param(Map<String, Object> paramsMap) {
        this.paramsMap = paramsMap;
    }

    /**
     * 根据参数名获取 long 型参数
     * @param name
     * @return
     */
    public long getLong( String name ) {
        return Long.parseLong(paramsMap.get(name).toString());
    }

    /**
     * 获取所有字段信息
     * @return
     */
    public Map<String, Object> getMap() {
        return paramsMap;
    }
}
