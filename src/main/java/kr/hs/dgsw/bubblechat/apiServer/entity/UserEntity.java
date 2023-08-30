package kr.hs.dgsw.bubblechat.apiServer.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserEntity {

    @Id
    private String email;

    private String name;

    private String photoPath;

    public User toDTO() {
        return User.builder()
                .email(email)
                .name(name)
                .photoPath(photoPath)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(photoPath, that.photoPath);
    }

}
