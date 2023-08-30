package kr.hs.dgsw.bubblechat.apiServer.domain;

import kr.hs.dgsw.bubblechat.apiServer.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String email;

    private String name;

    private String photoPath;


    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(email)
                .name(name)
                .photoPath(photoPath)
                .build();
    }

}
