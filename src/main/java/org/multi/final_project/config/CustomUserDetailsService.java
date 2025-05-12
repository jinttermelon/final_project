package org.multi.final_project.config;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.user.UserMapper;
import org.multi.final_project.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    //2.UserMapper DI
    @Autowired
    private UserMapper mapper;

    //1.Spring Security 로그인 처리시 호출하는 메소드 재정의
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername username:{}", username);
        //1-1. 로그인 폼에서 입력한 username을 이용해서 상세정보객체 생성
        UserVO vo = mapper.findById(username);
        log.info(vo.toString());

        //1-2. 해당사용자가 없을때 예외처리
        if(vo == null) {
            //예외발생
            throw new UsernameNotFoundException(username+"는 존재하지 않는 사용자입니다.");
        }

        //1-3.권한목록 컬랙션 생성 및 추가
        List<GrantedAuthority> authList = new ArrayList<>();
        //GrantedAuthority 인터페이스를 상속받은 클래스로 다형성 객체생성
        //객체생성시 생성자의 파라메터값 : ROLE_를 붙여줘야한다.
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+vo.getRole());
        authList.add(grantedAuthority);

        //1-4.확인
        for(GrantedAuthority x : authList) {
            log.info(x.getAuthority());
        }

        //1-5. 다형성을 이용한 객체생성 및 리턴
        UserDetails ud = new User(vo.getId(),vo.getPw(),authList);
        return ud;
    }
}
