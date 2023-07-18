package spring_study.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor//생성자 주입
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
