package kr.hs.dgsw.bubblechat.apiServer.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {

    private String email;

    private String token;

}
