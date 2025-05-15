package org.multi.final_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //1.SecurityFilterChain 등록
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //1.로그인의 대상이 아닌 서블릿경로 또는 리소스,에러 등등의 경로들을 지정해준다.
        String[] whiteList = {
                "/", "/home", "/user/insert", "/user/insertOK",
                "/user/selectAll",
                "/user/login_form", "/user/loginSuccess", "/user/loginFail",
                "/user/required", "/user/denied", "/user/expired","/user/findId","/user/findPw",
                "/crewboard/selectAll", "/crewboard/selectOne",
                "/uploadimgPath/**", "/error", "/api/**", "/css/**", "/js/**", "/images/**"
        };
        
        http.csrf(csrf ->
                        csrf.disable()
                )
                .authorizeHttpRequests(config->
                        config
                                .requestMatchers(whiteList).permitAll()//whiteList 등록
                                .requestMatchers("/admin/**").hasRole("ADMIN")//접근제한
                                .requestMatchers("/staff/**").hasAnyRole("ADMIN","STAFF")//접근제한
                                .anyRequest().authenticated()  //위에 명시한 모든 요청은 로그인 필수설정
                )
                .formLogin(config->
                        config
                                //인증을 거치지 않은 사용자를 리다이렉트 시킬 경로 설정
                                .loginPage("/user/required")
                                //로그인 처리를 할때 요청될 url설정(spring security가 내부적으로 login처리를 대신해준다)
                                //즉, 로그인폼에서 액션값을 의미한다.(컨트롤러에 등록할 필요없다)
                                .loginProcessingUrl("/user/login")
                                //로그인 처리를 대신하려면 어떤파라미터명으로 username,password가 넘어오는지 설정
                                .usernameParameter("id")
                                .passwordParameter("pw")
                                .successHandler(new AuthSuccessHandler())//로그인 성공시 처리할 객체
                                .failureForwardUrl("/user/loginFail")//로그인 실패시 forward 될 url
                                .permitAll()// 위에 명시한 모든 요청경로를 로그인없이 요청할 수 있도록 설정
                )
                .logout(config->
                        config
                                .logoutUrl("/user/logout")  //Spring Security가 자동으로 로그아웃처리해줄 경로
                                .logoutSuccessUrl("/")  // 로그아우 이후에 리다이렉트될 경로
                                .permitAll()
                )
                .exceptionHandling(config->
                        //403 오류인 경우 이동시킬 경로
                        config.accessDeniedPage("/user/denied")
                ).sessionManagement(config->
                        config.maximumSessions(1)  // 최대 세션 허용 갯수
                                .expiredUrl("/user/expired")  //허용세션 개수 초과시 리다렉트경로설정
                );

        return http.build();//위에 설정한 모든 정보를 토대로 SecurityFilterChain 객체 반환.
    }//end securityFilterChain()....
    
    //2.비밀번호를 암호화 해주는 객체를 빈으로 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //3.별도 클래스로 컨포넌트(@Service)로 UserDetailsService 인터페이스를 상속받는 클래스 정의
    //예: CustomUserDetailsService.java

}
