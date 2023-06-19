package kr.hs.dgsw.bubblechat.apiServer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kr.hs.dgsw.bubblechat.apiServer.domain.Relationship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "friend")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelationshipEntity {

    private String id;

    private String friendId;

    Relationship toEntity() {
        return Relationship.builder()
                .id(id).friend_id(friendId).build();
    }

}
