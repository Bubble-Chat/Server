package kr.hs.dgsw.bubblechat.apiServer.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {

    private List<User> userList;

}
