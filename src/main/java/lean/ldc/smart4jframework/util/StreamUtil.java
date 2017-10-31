package lean.ldc.smart4jframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 流操作工具类
 * Created by LinDunCheng on 2017/10/31.
 */
public final class StreamUtil {

    private static final Logger logger = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 从输入流中获取字符串
     * @param is
     * @return
     */
    public static String getString (InputStream is ) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            logger.error("get string failure",e);
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }
}
