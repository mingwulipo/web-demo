package org.study.web.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @desc: TODO
 * @author: lipo
 * @date: 2019-09-25 15:02
 * @version: v1.0
 */
public class RequestWrapper extends HttpServletRequestWrapper {
    /**
     * getInputStream使用，构造器写入
     */
    private byte[] byteArray;

    /**
     * request.getParameter()使用
     */
    private Map<String , String[]> paramMap = new HashMap<>();
    private Map<String , String> headerMap = new HashMap<>();

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        paramMap.putAll(request.getParameterMap());
    }

    public RequestWrapper(HttpServletRequest request, JSONObject newParams) {
        super(request);
        byteArray = newParams.toString().getBytes(Charset.forName("UTF-8"));
        //v.toString()会产生npe
        newParams.forEach((k, v) -> paramMap.put(k, new String[]{v + ""}));
        putHeader("Content-length", byteArray.length + "");
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                //return 0;//死循环，必须重写
                return bais.read();
            }
        };

    }

    @Override
    public String getParameter(String name) {
        String[] array = paramMap.get(name);
        return array[0];
    }


    @Override
    public String[] getParameterValues(String name) {//同上
        return paramMap.get(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return paramMap;
    }

    private void addAllParameters(Map<String, Object> extendParams) {
        for(Map.Entry<String , Object> entry : extendParams.entrySet()) {
            paramMap.put(entry.getKey() , new String[] {entry.getValue() + ""});
        }
    }

    public void putHeader(String name, String value) {
        this.headerMap.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        // check the custom headers first
        String headerValue = headerMap.get(name);

        if (headerValue != null) {
            return headerValue;
        }
        // else return from into the original wrapped object
        return ((HttpServletRequest) getRequest()).getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        // create a set of the custom header names
        Set<String> set = new HashSet<>(headerMap.keySet());

        // now add the headers from the wrapped request object
        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
        while (e.hasMoreElements()) {
            // add the names of the request headers into the list
            String n = e.nextElement();
            set.add(n);
        }

        // create an enumeration from the set and return
        return Collections.enumeration(set);
    }

    @Override
    public int getContentLength() {
        return byteArray.length;
    }

    @Override
    public long getContentLengthLong() {
        return byteArray.length;
    }
}
