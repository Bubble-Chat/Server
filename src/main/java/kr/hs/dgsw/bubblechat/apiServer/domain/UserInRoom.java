package kr.hs.dgsw.bubblechat.apiServer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInRoom {

    private Long idx;

    private String email;

    private String roomName;

    private Long roomIdx;

}
