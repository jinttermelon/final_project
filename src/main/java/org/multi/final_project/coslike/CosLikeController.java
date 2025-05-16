package org.multi.final_project.coslike;


import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.comment.CommentVO;
import org.multi.final_project.cos.CosVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/coslike")
public class CosLikeController {

    @Autowired
    private CosLikeService service;

    @GetMapping("/insert")
    public String insert(@RequestParam int cos_num, HttpSession session) {


        log.info(cos_num + "");
        String nickname = (String) session.getAttribute("nickname");

        if (nickname == null) {
            return "redirect:/login";
        }

        CosLikeVO vo = new CosLikeVO();
        vo.setCos_num(cos_num);
        vo.setNickname(nickname);
        service.insertOK(vo);
        return "redirect:/cos/selectOne?cos_num="+ cos_num;
    }

    @PostMapping("/insertOK")
    public String insertOK(CosLikeVO vo){
        log.info("insertOK()...");
        return "redirect:selectALl";
    }

    @GetMapping("/delete")
    public String delete(){
        log.info("delete()...");
        return "coslike/delete";
    }

    @GetMapping("/deleteOK")
    public String deleteOK(CosLikeVO vo){
        log.info("deleteOK()...");

        service.deleteOK(vo);

        return "redirect:/cos/selectOne?cos_num="+ vo.getCos_num();
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1")int cpage,
                            @RequestParam(defaultValue = "10")int limit,
                            Model model,
                            CosLikeVO vo){
        log.info("selectAll()...");
        log.info("vo:{}",vo);
        int startRow = (cpage - 1) * limit;

        List<CosLikeVO> vos = service.selectAll(vo, startRow, limit);
        for (CosLikeVO cosLike : vos) {
            System.out.println(cosLike.getCosVO().getCos_name());  // cos_name 확인
        }


        model.addAttribute("vos", vos);
        return "coslike/selectAll";
    }


}
