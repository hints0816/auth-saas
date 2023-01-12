package org.hints.tenant.utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.hints.common.pojo.CusUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/6 17:47
 */
public class SecurityUtil {

    public static CusUser getJwtInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "Bearer ");
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey("internet_plus".getBytes("utf-8"))
                    .parseClaimsJws(token).getBody();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        LinkedHashMap o = (LinkedHashMap) claims.get("user");
        CusUser cusUser = JSONObject.parseObject(JSONObject.toJSONString(o), CusUser.class);
        return cusUser;
    }

    public static String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principal = authentication.getPrincipal().toString();
        return principal;
    }

}
