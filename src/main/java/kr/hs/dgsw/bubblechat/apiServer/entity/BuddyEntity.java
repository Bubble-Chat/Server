package kr.hs.dgsw.bubblechat.apiServer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Entity
@Table(name = "friend")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BuddyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String email;

    @OneToOne
    @JoinColumn(name = "friendEmail")
    private UserEntity friend;

}
