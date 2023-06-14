package kr.hs.dgsw.bubblechat.apiServer.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String id;

    private String email;

    private String name;

    private String photoPath;

    private String backgroundPath;

}
