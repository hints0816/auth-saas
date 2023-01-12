package org.hints.auth.service;

import org.hints.auth.dao.UserDao;
import org.hints.common.pojo.CusUser;
import org.hints.common.pojo.Role;
import org.hints.common.pojo.SaasTenant;
import org.hints.common.service.SysUserSaasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceDetail implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserServiceDetail.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private SysUserSaasService sysUserSaasService;

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String client_id = "";
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        if(httpServletRequest != null){
            client_id = httpServletRequest.getParameter("client_id").toString();
        }
        if(client_id.equals("scm_client")){
            CusUser user = userDao.finduserByUserName(username);
            if(user == null){
                throw new UsernameNotFoundException("用户不存在!");
            }
            CusUser userDetails = CusUser.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(getAuthorities(username))
                    .build();
            return userDetails;
        }else{
            SaasTenant user = sysUserSaasService.findByMobile(username);
            if(user == null){
                throw new UsernameNotFoundException("用户不存在!");
            }
            CusUser userDetails = CusUser.withUsername(user.getSysUid())
                    .password(user.getPassword())
                    .userId(Long.valueOf(user.getSysUid()))
                    .phonenumber(user.getMobile())
                    .authorities(getAuthorities(username))
                    .build();
            return userDetails;
        }
    }

//    public UserDetails loadUserByUsernameAndVerifycode(String username,String verifycode) throws UsernameNotFoundException {
//        User user = userDao.finduser(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("用户不存在!");
//        }
//        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
//                .password(user.getPassword())
//                .authorities(getAuthorities(username))
//                .build();
//        logger.info("verifycode:"+verifycode);
//        return userDetails;
//    }

    //查询该用户的权限集
    public Collection<? extends GrantedAuthority> getAuthorities(String username) {
        Collection<GrantedAuthority> collection = new HashSet<>();
        List<Role> list1 = new ArrayList<>();
        Role role = new Role();
        role.setRole_id(123L);
        list1.add(role);
        return list1;
    }
}