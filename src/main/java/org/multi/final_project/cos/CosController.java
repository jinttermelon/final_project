package org.multi.final_project.cos;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.ad.AdVO;
import org.multi.final_project.comment.CommentVO;
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
@RequestMapping("/cos")
public class CosController {

    @Autowired
    private CosService service;


    @GetMapping("/insert")
    public String insert(){
        log.info("insert()...");

        return "cos/insert";
    }

    @PostMapping("/insertOK")
    public String insertOK(CosVO vo){
        log.info("insertOK()...");

        service.insertOK(vo);
        log.info(vo.toString());
        return "redirect:selectAll";
    }

    @GetMapping("/update")
    public String update(){
        log.info("update()...");
        return "cos/update";
    }

    @PostMapping("/updateOK")
    public String updateOK(CosVO vo){
        log.info("updateOK()...");
        return "redirect:selectAll";
    }

    @GetMapping("/delete")
    public String delete(){
        log.info("delete()...");
        return "cos/delete";
    }
    @PostMapping("/deleteOK")
    public String deleteOK(CosVO vo){
        log.info("deleteOK()...");
        return "redirect:selectAll";
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1")int cpage,
                            @RequestParam(defaultValue = "5")int limit,
                            Model model){
        int startRow = (cpage - 1) * limit;
        List<CosVO> vos = service.selectAll(startRow, limit);
        model.addAttribute("vos", vos);
        return "cos/selectAll";
    }

    @GetMapping("/selectOne")
    public String selectOne(CosVO vo){
        log.info("selectOne()...");

        return "cos/selectOne";
    }

    @GetMapping("/searchList")
    public String searchList(
                            String searchKey,
                            String searchWord,
                            @RequestParam(defaultValue = "1")int cpage,
                            @RequestParam(defaultValue = "10")int limit,
                            Model model){
        int startRow = (cpage - 1) * limit;

        log.info("cpage: {}", cpage);
        log.info("limit: {}", limit);
        log.info("searchKey: {}", searchKey);
        log.info("searchWord: {}", searchWord);

        // startRow를 매퍼로 전달
        List<CosVO> vos = service.searchList(searchKey,searchWord,startRow, limit);
        model.addAttribute("vos", vos);
        return "cos/selectAll";
    }

}
