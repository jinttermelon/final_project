package org.multi.final_project.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    //1.요청캐쉬 객체를 직접생성
    private RequestCache requestCache = new HttpSessionRequestCache();

    //2. 위에 1번에서 생성한객체를 인자없는 생성자로 슈퍼클래스의 전달
    public AuthSuccessHandler() {
        super.setRequestCache(requestCache);
    }


    //3.세션등록,캐쉬설정 등등 성공또는 실패시 리다이렉트 처리구문을 위한 오버라이딩.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        //3-1.세션 유지시간 설정
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60*20); //초단위 설정(예:20분)

        //3-2.로그인 성공시 입력된 사용자 정보획득
        String id = authentication.getName();
        log.info("로그인된 사용자:{}", id);

        //3-3.세션에 사용자 등록
        session.setAttribute("id", id);

        //3-4.로그인 성공이후 미리 저장된 요청이 있었는지 읽어오기
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        //3-5.만약에 미리저장된 요청이 없다면 즉 널값이 아니라면 로그인 환영페이지 또는 홈페이지로 이동
        if (savedRequest != null) {
            //3-6.로그인 환영페이지로 포워드(디스페처)
            request.getRequestDispatcher("/user/loginSuccess").forward(request,response);
        }else{
            //3-7.원래 가려던 목적지 경로로 리다이렉트 이동시킨다(Get방식 요청 파라미터도 자동으로 같이 전달)
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
