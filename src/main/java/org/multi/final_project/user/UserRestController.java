package org.multi.final_project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class UserRestController {
    @Autowired
    private UserService service;

    @GetMapping("/idCheck")
    public Map<String,String> idCheck() {
        return null;
    }

    @GetMapping("/nicknameCheck")
    public Map<String,String> nicknameCheck() {
        return null;
    }
}
