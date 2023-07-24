package spring_study.springboot.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring_study.springboot.domain.user.Role;

@Getter
@NoArgsConstructor
@Entity
@Table(name="userTable")//오류 수정,테이블 이름이 user 일 경우 오류 발생
public class User extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)//enum 값은 데이터베이스에 기본적으로 int 로 저장되지만, 저장 형식 설정
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name,String email,String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name =name;
        this.picture = picture;

        return this;
    }

    public  String getRoleKey(){
        return this.role.getKey();
    }
}
