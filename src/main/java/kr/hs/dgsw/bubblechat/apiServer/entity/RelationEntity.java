package kr.hs.dgsw.bubblechat.apiServer.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.bubblechat.apiServer.domain.Relation;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "friend")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RelationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String id;

    private String friendId;

    private String friendName;

    public Relation toDTO() {
        return Relation.builder()
                .id(id).friend_id(friendId).friend_name(friendName).build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationEntity that = (RelationEntity) o;
        return idx.equals(that.idx) && id.equals(that.id) && friendId.equals(that.friendId) && friendName.equals(that.friendName);
    }
}
