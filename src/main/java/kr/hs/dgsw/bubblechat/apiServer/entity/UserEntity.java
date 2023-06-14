package kr.hs.dgsw.bubblechat.apiServer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserEntity {
    @Id
    private String id;

    private String email;

    private String name;

    private String photoPath;

    private String backgroundPath;
}
