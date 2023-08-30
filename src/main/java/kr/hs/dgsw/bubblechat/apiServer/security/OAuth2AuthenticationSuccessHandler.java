package kr.hs.dgsw.bubblechat.apiServer.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {


        BubbleChatUserDetails userDetails = (BubbleChatUserDetails)authentication.getPrincipal();
        log.info("[INFO - SUCCESS] {}", userDetails);

//        String accessToken = jwtTokenProvider.generateToken(authentication, 30L * 60L * 1000);
//        String refreshToken = jwtTokenProvider.generateToken(authentication, 30L * 24L * 60L* 60L * 1000);
//
//        jwtTokenProvider.addTokenCookie(response, accessToken);

        String targetUrl = UriComponentsBuilder.fromUriString(getNextPage(userDetails))
                //.queryParam("token", accessToken)
                .build()
                .toUriString();


        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String getNextPage(BubbleChatUserDetails userDetails) {
        return "/lab/index";
    }


}
