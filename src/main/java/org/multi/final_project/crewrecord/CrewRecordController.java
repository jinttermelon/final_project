package org.multi.final_project.crewrecord;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.crew.CrewVO;
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
@RequestMapping("crewrecord")
@Controller
public class CrewRecordController {
    @Autowired
    private CrewRecordService service;

    @GetMapping("/insert")
    public String insert(){
        return "crewrecord/insert";
    }
    @PostMapping("/insertOK")
    public String insertOK(CrewRecordVO vo){
        return "redirect:/selectAll";
    }
    @GetMapping("/update")
    public String update(CrewRecordVO vo, Model model){
        return "crewrecord/update";
    }
    @PostMapping("/updateOK")
    public String updateOK(CrewRecordVO vo){
        return "redirect:/selectAll";
    }
    @GetMapping("/delete")
    public String delete(CrewRecordVO vo){
        return "crewrecord/delete";
    }
    @PostMapping("/deleteOK")
    public String deleteOK(CrewRecordVO vo){
        return "redirect:/selectAll";
    }

    @GetMapping("selectAll")
    public String selectAll(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "1") int cnum,
                            @RequestParam(defaultValue = "10") int limit,
                            Model model,
                            CrewRecordVO vo){

        int startRow = (cpage - 1) * limit;
        List<CrewRecordVO> vos = service.selectAll(startRow, limit, cnum);
        model.addAttribute("vos", vos);


        // 페이지네이션
        int totalRowCount = service.getTotalRowCount(vo);
        log.info("total row count: {}", totalRowCount);
        int pageCount = 1;
        if (totalRowCount / limit==0){
            pageCount = 1;
        }else if(totalRowCount % limit==0){
            pageCount = totalRowCount / limit;
        }else {
            pageCount = totalRowCount / limit +1;
        }
        log.info("page count: {}", pageCount);

        model.addAttribute("pageCount", pageCount);

        return "crewrecord/selectAll";
    }

    @GetMapping("/selectOne")
    public String selectOne(CrewRecordVO vo, Model model ){
        return "crewrecord/selectOne";
    }
    @GetMapping("/searchList")
    public String searchList(String searchKey,
                             String searchWord,
                             @RequestParam(defaultValue = "1") int cpage,
                             @RequestParam(defaultValue = "10") int limit,
                             Model model,
                             CrewRecordVO vo ){
        return "crewrecord/searchAll";
    }

}