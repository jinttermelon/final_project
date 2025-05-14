package org.multi.final_project.crewjoin;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.crew.CrewService;
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
@RequestMapping("crewjoin")
@Controller
public class CrewJoinController {

    @Autowired
    private CrewJoinService service;

    @Autowired
    private CrewService crewService;

    @GetMapping("/join")
    public String join(CrewVO vo, Model model) {

        CrewVO vo2 = crewService.selectOne(vo);
        log.info("CrewVO: {}", vo2);
        model.addAttribute("vo2", vo2);
        return "crewjoin/join";
    }

    @PostMapping("/joinOK")
    public String joinOK(CrewJoinVO vo){



        int result = service.joinOK(vo);

        return "redirect:/crew/selectAll";
    }

    @GetMapping("/update")
    public String update(){
        return "crewjoin/update";
    }

    @PostMapping("/updateOK")
    public String updateOK(CrewJoinVO vo){
        return "redirect:/selectAll";
    }

    @GetMapping("/delete")
    public String delete(){
        return "crewjoin/delete";
    }

    @PostMapping("/deleteOK")
    public String deleteOK(CrewJoinVO vo){
        return "redirect:/selectAll";
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "10") int limit, Model model , CrewVO vo){

        return "crewjoin/selectAll";
    }

    @GetMapping("/selectOne")
    public String selectOne(CrewVO vo, Model model ){

        CrewVO vo2 = crewService.selectOne(vo);
        log.info("CrewVO: {}", vo2);
        model.addAttribute("vo2", vo2);
        return "crewjoin/selectOne";
    }
}

