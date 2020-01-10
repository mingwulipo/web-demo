package org.study.web.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.study.web.util.HttpUtil;
import org.study.web.util.RequestWrapper;
import org.study.web.util.ResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @desc: TODO
 * @author: lipo
 * @date: 2019-09-25 14:49
 * @version: v1.0
 */
//@Component
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);
        String errorMsg = "";

        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String token = request.getHeader("token");
            if (!StringUtils.hasText(token)) {
                errorMsg = "令牌缺失";
                return;
            }

            String bodyString = HttpUtil.getBodyString(request);

            JSONObject jo = JSONObject.parseObject(bodyString);
            jo.put("token", token);

            RequestWrapper requestWrapper = new RequestWrapper(request, jo);
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            if (!StringUtils.isEmpty(errorMsg)) {
                responseWrapper.responseMessage(errorMsg);
            }
        }
    }

    @Override
    public void destroy() {

    }

}
