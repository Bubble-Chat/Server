package kr.hs.dgsw.bubblechat.apiServer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "friend")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FriendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String email;

    private String friendEmail;

    //private UserEntity user;

}
