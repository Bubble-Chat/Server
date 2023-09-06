package kr.hs.dgsw.bubblechat.apiServer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Friend {

    private String email;

    private String friendEmail;

}
