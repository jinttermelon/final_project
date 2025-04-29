package org.multi.final_project.crewrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "10") int limit, Model model, CrewRecordVO vo){
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