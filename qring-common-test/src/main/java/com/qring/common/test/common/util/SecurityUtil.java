package com.qring.common.test.common.util;

import com.qring.common.base.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Principal;
import java.util.Optional;

/**
 * @Author Qring
 * @Description TODO
 * @Date 2023/3/19 21:53
 * @Version 1.0
 */
@Slf4j
public class SecurityUtil {

    private static final String ANONYMOUS_USER = "anonymous";

    /**
     * 描述根据账号密码进行调用security进行认证授权 主动调
     * 用AuthenticationManager的authenticate方法实现
     * 授权成功后将用户信息存入SecurityContext当中
     *
     * @param username              用户名
     * @param password              密码
     * @param authenticationManager 认证授权管理器,
     * @return UserInfo  用户信息
     * @see AuthenticationManager
     */
    public static Object login(String username, String password, AuthenticationManager authenticationManager) throws AuthenticationException {
        //使用security框架自带的验证token生成器  也可以自定义。
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return authenticate.getPrincipal();
    }

    public static String getCurrentLogin() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(principal -> {
                    // 大多数 AuthenticationManager 会返回 UserDetails ，提供更多信息
                    if (principal instanceof UserDetails) {
                        UserDetails userDetails = (UserDetails) principal;
                        return userDetails.getUsername();
                    }
                    // 如果没有更多信息，可以看一下是否是一个 Principal
                    if (principal instanceof Principal) {
                        return ((Principal) principal).getName();
                    }
                    // 其他情况看作是一个用户名
                    return String.valueOf(principal);
                })
                // 如果未认证，那么 Authentication 为 Null
                // 可以在未受安全保护的 URL 中实验
                // 此次返回匿名用户
                .orElse(ANONYMOUS_USER);
    }

    /**
     * 获取当前登录的所有认证信息
     *
     * @return 获取当前登录的所有认证信息
     */
    public static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication();
    }

    /**
     * 获取当前登录用户信息
     *
     * @return 获取当前登录用户信息
     */
    public static Object getUserInfo() {
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal != null) {
                return authentication.getPrincipal();
            }
        }
        throw new BizException();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
