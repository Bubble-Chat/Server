package kr.hs.dgsw.bubblechat.apiServer.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends GenericFilterBean {
    public static final String COOKIE_NAME = "auth_token";

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = ((HttpServletRequest)request).getHeader("Authorization");
        LoggerFactory.getLogger(getClass()).info("[TOKEN] {} {}", ((HttpServletRequest) request).getRequestURL(), token);

        if (token == null) {
            token = getTokenByCookie((HttpServletRequest)request);
        }

        //log.info("[TOKEN1] {}", jwtTokenProvider.validateToken(token));

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                String email = jwtTokenProvider.getUsername(token);
                //log.info("[email] {}", email);

                User user = userService.getByEmail(email);
                //log.info("[user] {}", user);
                if (user != null) {
                    BubbleChatUserDetails userDetails = new BubbleChatUserDetails(user);
                    //log.info("[userDetails] {}", userDetails);

                    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }


        chain.doFilter(request, response);
    }

    private String getTokenByCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
