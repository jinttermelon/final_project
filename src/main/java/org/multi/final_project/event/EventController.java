package org.multi.final_project.event;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.friend.FriendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequestMapping("/event")
@Controller
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping("/insert")
    public String insert() {
        return "event/insert";
    }

    @PostMapping("/insertOK")
    public String insertOK(FriendVO vo) {
        return "redirect:selectAll";
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "10") int limit,
                            Model model) {
        return "event/selectAll";
    }

    @GetMapping("/selectOne")
    public String selectOne(EventVO vo,Model model) {
        return "event/selectOne";
    }

    @GetMapping("/deleteOK")
    public String deleteOK(FriendVO vo) {
        return "redirect:selectAll";
    }

}
