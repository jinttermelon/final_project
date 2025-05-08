package org.multi.final_project.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/insert")
    public String insert() {
        return "user/insert";
    }

    @GetMapping("/findId")
    public String findId() {
        return "user/findId";
    }

    @GetMapping("/findPassword")
    public String findPassword() {
        return "findPw";
    }

    @PostMapping("/insertOK")
    public String insertOK(UserVO vo) {
        service.insertOK(vo);

        log.info(vo.toString());
        return "user/insertFinish";
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
    public String selectAll(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "10") int limit,
                            Model model) {

        int startRow = (cpage - 1) * limit;
        log.info("startRow:" + startRow);
        List<UserVO> vos = service.selectAll(startRow, limit);
        model.addAttribute("vos", vos);
        return "user/selectAll";
    }

    @GetMapping("/selectOne")
    public String selectOne(UserVO vo, Model model) {

        UserVO vo2 = service.selectOne(vo);
        log.info("vo2:" + vo2);
        model.addAttribute("vo2", vo2);
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
