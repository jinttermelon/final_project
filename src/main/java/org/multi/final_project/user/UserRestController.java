package org.multi.final_project.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UserRestController {
    @Autowired
    private UserService service;

    @GetMapping("/idCheck")
    public Map<String, String> idCheck(@RequestParam("id") String id) {
        Map<String, String> result = new HashMap<>();
        log.info("idCheck");

        boolean exists = service.idCheck(id);  // UserService에 구현 필요
        if (exists) {
            result.put("status", "fail");
            result.put("message", "이미 사용 중인 아이디입니다.");
        } else {
            result.put("status", "success");
            result.put("message", "사용 가능한 아이디입니다.");
        }

        return result;
    }

    @GetMapping("/nicknameCheck")
    public Map<String, String> nicknameCheck(@RequestParam("nickname") String nickname) {
        Map<String, String> result = new HashMap<>();

        boolean exists = service.nicknameCheck(nickname);  // UserService에 구현 필요
        if (exists) {
            result.put("status", "fail");
            result.put("message", "이미 사용 중인 닉네임입니다.");
        } else {
            result.put("status", "success");
            result.put("message", "사용 가능한 닉네임입니다.");
        }

        return result;
    }
}
