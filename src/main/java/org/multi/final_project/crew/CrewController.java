package org.multi.final_project.crew;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.crewboard.CrewBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequestMapping("crew")
@Controller
public class CrewController {


    @Autowired
    private CrewService service;

    @GetMapping("/create")
    public String create(){
        return "crew/create";
    }
    @PostMapping("/createOK")
    public String createOK(CrewVO vo){
        return "redirect:/selectAll";
    }
    @GetMapping("/update")
    public String update(){
        return "crew/update";
    }
    @PostMapping("/updateOK")
    public String updateOK(CrewVO vo){
        return "redirect:/selectAll";
    }
    @GetMapping("/delete")
    public String delete(){
        return "crew/delete";
    }
    @GetMapping("/deleteFinish")
    public String deleteFinish(){
        return "crew/deleteFinish";
    }
    @PostMapping("/deleteOK")
    public String deleteOK(CrewVO vo){
        return "redirect:/deleteFinish";
    }
    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "10") int limit, Model model ){

        int startRow = (cpage - 1) * limit;
        List<CrewVO> vos = service.selectAll(startRow, limit);
        model.addAttribute("vos", vos);
        return "crew/selectAll";
    }
    @GetMapping("/selectOne")
    public String selectOne(CrewVO vo, Model model ){

        CrewVO vo2 = service.selectOne(vo);
        model.addAttribute("vo2", vo2);
        return "crew/selectOne";
    }
    @GetMapping("/searchList")
    public String searchList(
            @RequestParam(defaultValue = "1") int cpage,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "name") String searchKey,
            @RequestParam(defaultValue = "") String searchWord,
            Model model) {

        // 시작 행 계산
        int startRow = (cpage - 1) * limit;

        log.info("/crew/searchList");
        log.info("cpage: {}", cpage);
        log.info("limit: {}", limit);
        log.info("searchKey: {}", searchKey);
        log.info("searchWord: {}", searchWord);

        // startRow를 매퍼로 전달
        List<CrewVO> vos = service.searchList(startRow, limit, searchKey, searchWord);
        model.addAttribute("vos", vos);

        return "crew/selectAll";
    }

}
