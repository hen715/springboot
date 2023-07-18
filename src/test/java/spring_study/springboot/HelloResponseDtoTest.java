package spring_study.springboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import spring_study.springboot.web.dto.HelloResponseDto;




public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount  = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        Assertions.assertThat(dto.getName()).isEqualTo(name);
        Assertions.assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
