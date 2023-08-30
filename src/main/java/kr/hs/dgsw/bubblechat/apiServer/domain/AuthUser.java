package kr.hs.dgsw.bubblechat.apiServer.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUser {

    private boolean isExists;

    private String name;

    private String email;

    private String accessToken;

}
