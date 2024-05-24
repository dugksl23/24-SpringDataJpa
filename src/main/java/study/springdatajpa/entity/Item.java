package study.springdatajpa.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item implements Persistable<Long> {

    @Id
    private Long id;
    @Column(name = "item_name", nullable = false)
    private String name;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;


    public Item(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    /**
     * @isNew()
     * 등록시간( `@CreatedDate` )을 조합해서 사용하면
     * 이 필드로 새로운 엔티티 여부를 편리하게 확인할 수 있다.
     * (@CreatedDate에 값이 없으면 새로운 엔티티로 판단)
     */
    @Override
    public boolean isNew() {
        return createdAt == null;
    }

}
