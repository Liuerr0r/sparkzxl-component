package com.sparksys.cloud.interceptor;

import com.sparksys.core.constant.BaseContextConstants;
import com.sparksys.core.constant.CoreConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * description: feign client 拦截器，
 * 实现将 feign 调用方的 请求头封装到 被调用方的请求头
 *
 * @author: zhouxinlei
 * @date: 2020-07-12 16:31:14
 */
@Slf4j
public class FeignAddHeaderRequestInterceptor implements RequestInterceptor {

    private static final List<String> HEADER_NAME_LIST = Arrays.asList(
            BaseContextConstants.APPLICATION_AUTH_USER_ID, BaseContextConstants.APPLICATION_AUTH_NAME,
            BaseContextConstants.APPLICATION_AUTH_ACCOUNT
    );

    public FeignAddHeaderRequestInterceptor() {
        super();
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(CoreConstant.REQUEST_TYPE, CoreConstant.REQUEST_TYPE);
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        if (request == null) {
            log.warn("path={}, 在FeignClient API接口未配置FeignConfiguration类， 故而无法在远程调用时获取请求头中的参数!", template.path());
            return;
        }
        HEADER_NAME_LIST.forEach((headerName) -> template.header(headerName, String.valueOf(request.getAttribute(headerName))));
    }
}
