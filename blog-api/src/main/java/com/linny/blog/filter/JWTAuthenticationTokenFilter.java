package com.linny.blog.filter;

import com.linny.blog.exceptions.TokenException;
import com.linny.blog.utils.Token;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    RedisTemplate redisTemplate;



    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            logger.info("无token");
            filterChain.doFilter(request, response);
            return;
        }

        //解析token
        String userId = Token.getIdByToken(token);
        if (StringUtils.isEmpty(userId)) {
            throw new TokenException(" token异常");
        }

        String userIdCache = redisTemplate.opsForValue().get("UserId").toString();
        if (userId.equals(userIdCache)){
            throw new TokenException("登录过期");
        }

        //存入SecurityPasswordContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userId, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);

    }
}
