package spring_study.springboot.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass//이 클래스를 상속 시 아래 필드들도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class)//Auditin 기능을 포함시킴
public class BaseTimeEntity {

    @CreatedDate//엔티티가 생성되어 저장될 시 시간 자동 저장
    private LocalDateTime createDate;

    @LastModifiedDate//엔티티가 수정될 시 시간 자동 저장
    private LocalDateTime modifiedDate;
}
