package lean.ldc.smart4jframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码与解码工具类
 * Created by LinDunCheng on 2017/10/31.
 */
public final class CodecUtil {
    private static Logger logger = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 将 URL 编码
     * @param source
     * @return
     */
    public static String encodeURL(String source) {
        String target;
        try {
            target = URLEncoder.encode(source,"UTF-8");

        } catch (Exception e) {
            logger.error("encode url failure",e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将 URL 解码
     * @param source
     * @return
     */
    public static String decodeURL (String source) {
        String target;
        try {
            target = URLDecoder.decode(source,"UTF-8");

        } catch (Exception e) {
            logger.error("decode url failure",e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
