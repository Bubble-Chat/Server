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

    private String id;

    private String email;

    private String name;

    private String photoPath;

    private String backgroundPath;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .id(id)
                .email(email)
                .name(name)
                .photoPath(photoPath)
                .backgroundPath(backgroundPath)
                .build();
    }

}
