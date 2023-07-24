package spring_study.springboot.config.auth;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import spring_study.springboot.config.auth.dto.SessionUser;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {//@LoginUser 어노테이션이 붙어있고, 파라미터 클래스 타입이 SessionUser.class 인 경우 true 반환
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class)!=null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation&&isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");//세션에 객체를 가져옴
    }
}
