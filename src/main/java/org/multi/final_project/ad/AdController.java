package org.multi.final_project.ad;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.crew.CrewVO;
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
@RequestMapping("/ad")
public class AdController {


    @Autowired
    private AdService service;


    @GetMapping("/insert")
    public String insert(){
        log.info("insert()...");
        return "ad/insert";
    }

    @PostMapping("/insertOK")
    public String insertOK(AdVO vo){
        log.info("insertOK()...");
        return "redirect:selectALl";
    }

    @GetMapping("/update")
    public String update(){
        log.info("update()...");
        return "ad/update";
    }

    @PostMapping("/updateOK")
    public String updateOK(AdVO vo){
        log.info("updateOK()...");
        return "redirect:selectALl";
    }

    @GetMapping("/delete")
    public String delete(){
        log.info("delete()...");
        return "ad/delete";
    }
    @PostMapping("/deleteOK")
    public String deleteOK(AdVO vo){
        log.info("deleteOK()...");
        return "redirect:selectALl";
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1")int cpage,
                            @RequestParam(defaultValue = "10")int limit,
                            Model model){
        log.info("selectAll()...");
        int startRow = (cpage - 1) * limit;
        List<AdVO> vos = service.selectAll(startRow, limit);
        model.addAttribute("vos", vos);
        return "ad/selectAll";
    }

    @GetMapping("/searchList")
    public String searchList(
            String searchKey,
            String searchWord,
            @RequestParam(defaultValue = "1")int cpage,
            @RequestParam(defaultValue = "10")int limit,
            Model model){
        log.info("selectAll()...");

        int startRow = (cpage - 1) * limit;

        log.info("cpage: {}", cpage);
        log.info("limit: {}", limit);
        log.info("searchKey: {}", searchKey);
        log.info("searchWord: {}", searchWord);

        // startRow를 매퍼로 전달
        List<AdVO> vos = service.searchList(searchKey,searchWord,startRow, limit);
        model.addAttribute("vos", vos);
        return "ad/selectAll";
    }
}
