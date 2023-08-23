package kr.hs.dgsw.bubblechat.apiServer.security;

import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "oAuth2UserService")
@RequiredArgsConstructor
@Slf4j
public class BubbleChatOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration()
                .getRegistrationId();
        log.info("OAUTH registrationId {}", registrationId);

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        UserProfile userProfile = OAuthAttributes.extract(registrationId, attributes);

        log.info("[userProfile] {}", userProfile);
        String token = userRequest.getAccessToken().getTokenValue();
        log.info("[token] {}", token);

        try {
            User user = userService.getByEmail(userProfile.getEmail());
            List<GrantedAuthority> authorities = null;
            return new BubbleChatUserDetails(user, attributes);
        } catch (Exception e) {

        }



        return null;


    }

}
