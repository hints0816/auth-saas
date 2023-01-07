package org.hints.tenant.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/6 17:47
 */
public class SecurityUtil {

    public static Claims getJwtInfo() throws UnsupportedEncodingException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "Bearer ");
        Claims claims = Jwts.parser()
                .setSigningKey("internet_plus".getBytes("utf-8"))
                .parseClaimsJws(token).getBody();
        return claims;
    }

}
