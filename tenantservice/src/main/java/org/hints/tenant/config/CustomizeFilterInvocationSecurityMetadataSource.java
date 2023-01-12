package org.hints.tenant.config;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();

        return null;

        //没有匹配上的资源，都是登录访问
//        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

    //TODO String... antPatterns
    private boolean customMatcher(String requestUrl,String... antPatterns) {
        boolean result= false;
        String[] var4 = antPatterns;
        int var5 = antPatterns.length;
        for(int var6 = 0; var6 < var5; ++var6) {
            String pattern = var4[var6];
            result = antPathMatcher.match(pattern,requestUrl);
            if(result){
                break;
            }
        }
        return result;

    }

    /**
     * 过滤静态资源
     */
    protected boolean skipAuthenticationCheck(String url) {
        return url.endsWith(".css") ||
                url.endsWith(".js") ||
                url.endsWith(".html") ||
                url.endsWith(".map") ||
                url.endsWith(".woff") ||
                url.endsWith(".png") ||
                url.endsWith(".jpg") ||
                url.endsWith(".jpeg") ||
                url.endsWith(".tif") ||
                url.endsWith(".tiff") ||
                url.endsWith(".json") ||
                url.endsWith(".xml");
    }
}
