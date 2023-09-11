package kr.hs.dgsw.bubblechat.apiServer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_in_room")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserInRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String email;

    private Long roomIdx;

}
