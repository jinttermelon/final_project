package user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/insert")
    public String insert() {
        return "insert";
    }

    @PostMapping("/insertOK")
    public String insertOK(UserVO vo) {
        return "redirect:/user/selectAll";
    }

    @GetMapping("/banUser")
    public String banUser() {
        return "user/banForm";
    }

    @PostMapping("/banUserOK")
    public String banUserOK(UserVO vo) {
        return "redirect:/user/selectAll";
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam int cpage, @RequestParam int pageSize, Model model) {
        return "user/selectAll";
    }

    @GetMapping("/selectOne")
    public String selectOne(UserVO vo, Model model) {
        return "user/selectOne";
    }

    @GetMapping("/update")
    public String update(UserVO vo, Model model) {
        return "user/update";
    }

    @PostMapping("/updateOK")
    public String updateOK(UserVO vo) {
        return "redirect:/user/selectAll";
    }

    @GetMapping("/delete")
    public String delete() {
        return "user/delete";
    }

    @PostMapping("/deleteOK")
    public String deleteOK(UserVO vo) {
        return "redirect:/user/selectAll";
    }

    @GetMapping("/login")
    public String login_form() {
        return "user/login";
    }

    @GetMapping("/required")
    public String required_login() {
        return "user/required";
    }

    @GetMapping("/fail")
    public String login_fail() {
        return "user/loginFail";
    }

    @GetMapping("/success")
    public String login_success() {
        return "user/loginSuccess";
    }

    @GetMapping("/denied")
    public String denied() {
        return "user/accessDenied";
    }



}
