package kr.hs.dgsw.bubblechat.apiServer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InviteDTO {

    private String friendEmail;

    private Long roomIdx;

}
