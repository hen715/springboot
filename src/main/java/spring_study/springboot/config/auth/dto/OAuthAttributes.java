package spring_study.springboot.config.auth.dto;

import lombok.Builder;
import lombok.Getter;
import spring_study.springboot.domain.User;
import spring_study.springboot.domain.user.Role;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String,Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String,Object> attributes, String nameAttributeKey,String name, String email, String picture){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId,String userNameAttributeName, Map<String,Object> attributes){//사용자 정보는 Map 이기 때문에 하나하나 변환 of 를 통해서
        if("naver".equals(registrationId)){
            return ofNaver("id",attributes);
        }

        return ofGoogle(userNameAttributeName,attributes);
    }
    private static  OAuthAttributes ofGoogle(String useNameAttributeName,Map<String,Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get(("email")))
                .picture((String) attributes.get(("picture")))
                .attributes(attributes)
                .nameAttributeKey(useNameAttributeName)
                .build();
    }

    private static  OAuthAttributes ofNaver(String useNameAttributeName,Map<String,Object> attributes){

        Map<String,Object> response = (Map<String, Object>)attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get(("email")))
                .picture((String) response.get(("picture")))
                .attributes(response)
                .nameAttributeKey(useNameAttributeName)
                .build();
    }

    public User toEntity(){//User 엔티티 생성, 처음 가입시 Guest 권한 부여
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
