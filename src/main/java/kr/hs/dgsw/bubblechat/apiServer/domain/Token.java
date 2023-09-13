package kr.hs.dgsw.bubblechat.apiServer.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Token {

    private String provider;

    private String token;

}
