package kr.hs.dgsw.bubblechat.apiServer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invite {

    private User friend;

    private Room room;

}
