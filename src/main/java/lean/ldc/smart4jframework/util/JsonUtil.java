package lean.ldc.smart4jframework.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * json 工具类(基于jackson)
 * Created by LinDunCheng on 2017/10/31.
 */
public final class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将 POJO 转成 json
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj) {
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("convert POJO to JSON failure",e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将 JSON 转成 POJO
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json,type);
        }  catch (Exception e) {
            logger.error("convert JSON to POJO failure",e);
            throw new RuntimeException(e);
        }
        return pojo;
    }

}
