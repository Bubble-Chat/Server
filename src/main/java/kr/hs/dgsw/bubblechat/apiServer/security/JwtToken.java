package kr.hs.dgsw.bubblechat.apiServer.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class JwtToken {

    private String accessToken;

    private String refreshToken;

}
