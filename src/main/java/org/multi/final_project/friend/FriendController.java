package org.multi.final_project.friend;

import java.security.Principal;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.user.UserService;
import org.multi.final_project.user.UserVO;
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

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;




    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "10") int limit,
                            Model model,
                            Principal principal) {

        String loginId = principal.getName(); // 이메일 등 로그인 아이디
        UserVO user = userService.getNickname(loginId); // 이 메서드 호출
        String nickname = user.getNickname(); // 진짜 닉네임

        List<FriendVO> list = service.selectAll(nickname, cpage, limit);

        model.addAttribute("friendList", list);
        model.addAttribute("cpage", cpage);
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

    @GetMapping("/delete")
    public String delete(FriendVO vo) {
        return "friend/delete";
    }


    @GetMapping("/deleteOK")
    public String deleteOK(FriendVO vo) {
        log.info("deleteOK");
        log.info(vo.toString());
        service.deleteOK(vo);
        return "redirect:selectAll";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String searchWord, Model model) {
        String nickname = session.getAttribute("nickname").toString();
        log.info("nickname: " + nickname);
        List<UserVO> searchList = userService.searchByNickname(searchWord,nickname);
        model.addAttribute("searchList", searchList);
        return "friend/search";
    }

    @PostMapping("/insertOK")
    public String insertOK(FriendVO vo,@RequestParam(defaultValue = "") String searchWord) {
        String nickname = session.getAttribute("nickname").toString();
        log.info("nickname: " + nickname);
        vo.setUnickname(nickname);
        log.info("insertOK");
        log.info(vo.toString());
        log.info(searchWord);

        service.insertOK(vo);

        return "redirect:search?searchWord="+searchWord;
    }

}
