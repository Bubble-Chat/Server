package kr.hs.dgsw.bubblechat.apiServer.domain;

import kr.hs.dgsw.bubblechat.apiServer.entity.RelationshipEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Relationship {

    private String id;

    private String friend_id;

    RelationshipEntity toEntity() {
        return RelationshipEntity.builder()
                .id(id).friendId(friend_id).build();
    }

}
