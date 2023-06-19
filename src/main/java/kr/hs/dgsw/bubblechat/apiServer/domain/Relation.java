package kr.hs.dgsw.bubblechat.apiServer.domain;

import kr.hs.dgsw.bubblechat.apiServer.entity.RelationEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Relation {

    private String id;

    private String friend_id;

    private String friend_name;

    public RelationEntity toEntity() {
        return RelationEntity.builder()
                .id(id).friendId(friend_id).friendName(friend_name).build();
    }

}
