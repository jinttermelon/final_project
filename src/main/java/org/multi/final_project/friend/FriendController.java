package org.multi.final_project.friend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequestMapping("/friend")
@Controller
public class FriendController {

    @Autowired
    private FriendService service;

    @GetMapping("/insert")
    public String insert(FriendVO vo) {
        return "friend/insert";
    }//크루 목록이 나와야함


    @PostMapping("/insertOK")
    public String insertOK(FriendVO vo) {
        return "redirect:selectAll";
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "10") int limit,
                            Model model) {
        return "friend/selectAll";
    }

    @GetMapping("/selectSend")
    public String selectSend(FriendVO vo, Model model) {
        return "friend/selectSend";
    }

    @GetMapping("/selectRecive")
    public String selectRecive(FriendVO vo, Model model) {
        return "friend/selectRecive";
    }

    @GetMapping("/acceptedFriend")
    public String acceptedFriend(FriendVO vo, Model model) {
        return "friend/acceptedFriend";
    }

    @GetMapping("/rejectedFriend")
    public String rejectedFriend(FriendVO vo, Model model) {
        return "friend/rejectedFriend";
    }

    @GetMapping("/deleteOK")
    public String deleteOK(FriendVO vo) {
        return "redirect:selectAll";
    }

}
