package org.hints.common.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

@Data
@Table("sys_user${comp}")
@NoArgsConstructor
public class CusUser implements UserDetails {

    /**
     * 用户ID
     */
    @Id
    private Long user_id;
    /**
     * 用户账号
     */
    @Name
    private String user_name;
    /**
     * 用户昵称
     */
    private String nick_name;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phonenumber;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐加密
     */
    private String salt;
    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String del_flag;
    /**
     * 最后登录IP
     */
    private String login_ip;
    /**
     * 最后登录时间
     */
    private Date login_date;
    /**
     * 角色对象
     */
    private List<Role> roles;
    /**
     * 角色组
     */
    private Long[] role_ids;
    /**
     * 岗位组
     */
    private Long[] post_ids;

    private Collection<? extends GrantedAuthority> authorities;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @ManyMany(target = Role.class,
            relation = "sys_user_role",
            from = "user_id",
            to = "role_id")
    private List<Role> utype;

    public CusUser(Long user_id, String user_name, String nick_name, String email,
                   String phonenumber, String sex, String avatar,
                   String password, String salt, String status,
                   String del_flag, String login_ip, Date login_date, Collection<? extends GrantedAuthority> authorities) {
        this(user_id, user_name, nick_name, email, phonenumber, sex, avatar, password,
                salt, status, del_flag, login_ip, login_date, true, true, true, true, authorities);
    }

    public CusUser(Long user_id, String user_name, String nick_name, String email,
                   String phonenumber, String sex, String avatar,
                   String password, String salt, String status,
                   String del_flag, String login_ip, Date login_date, boolean enabled,
                   boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        if (user_name != null && !"".equals(user_name) && password != null) {
            this.user_id = user_id;
            this.user_name = user_name;
            this.nick_name = nick_name;
            this.email = email;
            this.phonenumber = phonenumber;
            this.sex = sex;
            this.avatar = avatar;
            this.password = password;
            this.salt = salt;
            this.status = status;
            this.del_flag = del_flag;
            this.login_ip = login_ip;
            this.login_date = login_date;

            this.enabled = enabled;
            this.accountNonExpired = accountNonExpired;
            this.credentialsNonExpired = credentialsNonExpired;
            this.accountNonLocked = accountNonLocked;
            this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        } else {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }

    public static class UserBuilder {

        private Long user_id;
        private String user_name;
        private String nick_name;
        private String email;
        private String phonenumber;
        private String sex;
        private String avatar;
        private String password;
        private String salt;
        private String status;
        private String del_flag;
        private String login_ip;
        private Date login_date;
        private List<Role> roles;
        private Long[] role_ids;
        private Long[] post_ids;

        private List<GrantedAuthority> authorities;

        private boolean accountExpired;
        private boolean accountLocked;
        private boolean credentialsExpired;
        private boolean disabled;

        private Function<String, String> passwordEncoder;

        private UserBuilder() {
            this.passwordEncoder = (password) -> {
                return password;
            };
        }

        public UserBuilder userId(Long userId) {
            Assert.notNull(userId, "user_id cannot be null");
            this.user_id = userId;
            return this;
        }

        public UserBuilder userName(String userName) {
            Assert.notNull(userName, "userName cannot be null");
            this.user_name = userName;
            return this;
        }

        public UserBuilder nickName(String nickName) {
            Assert.notNull(nickName, "nickName cannot be null");
            this.nick_name = nickName;
            return this;
        }

        public UserBuilder email(String email) {
            Assert.notNull(email, "email cannot be null");
            this.email = email;
            return this;
        }

        public UserBuilder phonenumber(String phonenumber) {
            Assert.notNull(phonenumber, "phonenumber cannot be null");
            this.phonenumber = phonenumber;
            return this;
        }

        public UserBuilder sex(String sex) {
            Assert.notNull(sex, "sex cannot be null");
            this.sex = sex;
            return this;
        }

        public UserBuilder avatar(String avatar) {
            Assert.notNull(avatar, "avatar cannot be null");
            this.avatar = avatar;
            return this;
        }

        public UserBuilder salt(String salt) {
            Assert.notNull(salt, "salt cannot be null");
            this.salt = salt;
            return this;
        }

        public UserBuilder delFlag(String delFlag) {
            Assert.notNull(delFlag, "delFlag cannot be null");
            this.del_flag = delFlag;
            return this;
        }

        public UserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        public UserBuilder loginIp(String loginIp) {
            Assert.notNull(loginIp, "loginIp cannot be null");
            this.login_ip = loginIp;
            return this;
        }

        public UserBuilder loginDate(Date loginDate) {
            Assert.notNull(loginDate, "loginDate cannot be null");
            this.login_date = loginDate;
            return this;
        }

        public UserBuilder passwordEncoder(Function<String, String> encoder) {
            Assert.notNull(encoder, "encoder cannot be null");
            this.passwordEncoder = encoder;
            return this;
        }

        public UserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList(roles.length);
            String[] var3 = roles;
            int var4 = roles.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String role = var3[var5];
                Assert.isTrue(!role.startsWith("ROLE_"), role + " cannot start with ROLE_ (it is automatically added)");
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }

            return this.authorities((Collection) authorities);
        }

        public UserBuilder authorities(GrantedAuthority... authorities) {
            return this.authorities((Collection) Arrays.asList(authorities));
        }

        public UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList(authorities);
            return this;
        }

        public UserBuilder authorities(String... authorities) {
            return this.authorities((Collection) AuthorityUtils.createAuthorityList(authorities));
        }

        public UserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public UserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public UserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public UserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public CusUser build() {
            String encodedPassword = (String) this.passwordEncoder.apply(this.password);
            return new CusUser(this.user_id, this.user_name, this.nick_name,
                    this.email, this.phonenumber, this.sex, this.avatar,
                    encodedPassword, this.salt, this.status, this.del_flag,
                    this.login_ip, this.login_date,
                    !this.disabled, !this.accountExpired, !this.credentialsExpired, !this.accountLocked, this.authorities);
        }
    }

    public static UserBuilder withUsername(String username) {
        return builder().userName(username);
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return user_name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet(new AuthorityComparator());
        Iterator var2 = authorities.iterator();

        while (var2.hasNext()) {
            GrantedAuthority grantedAuthority = (GrantedAuthority) var2.next();
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = 500L;

        private AuthorityComparator() {
        }

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            } else {
                return g1.getAuthority() == null ? 1 : g1.getAuthority().compareTo(g2.getAuthority());
            }
        }
    }
}
