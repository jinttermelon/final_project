package org.multi.final_project.comment;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.ad.AdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/comment")
public class CommentController {



    @Autowired
    private CommentService service;

    @Autowired
    private HttpSession session;

    @GetMapping("/insert")
    public String insert(){
        log.info("insert()...");
        return "comment/insert";


    }

    @PostMapping("/insertOK")
    public String insertOK(CommentVO vo, HttpSession session,
                           @RequestParam("cnum") int cnum){
        log.info("insertOK()...");
        String userNickname = (String) session.getAttribute("nickname");
        vo.setNickname(userNickname);
        service.insertOK(vo);

        return "redirect:/crewboard/selectOne?bnum="+vo.getBnum()+"&cnum="+cnum;
    }

    @GetMapping("/update")
    public String update(){
        log.info("update()...");
        return "comment/update";
    }

    @PostMapping("/updateOK")
    public String updateOK(CommentVO vo){
        log.info("updateOK()...");
        return "redirect:selectAll";
    }

    @GetMapping("/delete")
    public String delete(){
        log.info("delete()...");
        return "comment/delete";
    }
    @PostMapping("/deleteOK")
    public String deleteOK(CommentVO vo){
        log.info("deleteOK()...");
        return "redirect:selectAll";
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1")int cpage,
                            @RequestParam(defaultValue = "10")int limit,
                            Model model){
        log.info("selectAll()...");
        int startRow = (cpage - 1) * limit;
        List<CommentVO> vos = service.selectAll(startRow, limit);
        log.info(vos.toString());
        model.addAttribute("vos", vos);
        return "comment/selectAll";
    }

}
