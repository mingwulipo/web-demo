package org.study.web.util;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @desc: TODO
 * @author: lipo
 * @date: 2019-09-26 17:30
 * @version: v1.0
 */
public class HttpUtil {

    public static final String UTF_8 = "UTF-8";
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try (
                InputStream inputStream = request.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(UTF_8)))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
