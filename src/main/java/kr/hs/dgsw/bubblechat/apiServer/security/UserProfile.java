package kr.hs.dgsw.bubblechat.apiServer.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class UserProfile {

    private final String oauthId;

    private final String name;

    private final String email;

    private final String imageUrl;

}
