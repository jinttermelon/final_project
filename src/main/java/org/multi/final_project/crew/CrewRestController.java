package org.multi.final_project.crew;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.report.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class CrewRestController {
    @Autowired
    private CrewService service;

    @GetMapping("/nameCheck")
    public Map<String,String> nameCheck(CrewVO vo){
        return null;
    }

    @GetMapping("/crewlistAdmin/updateGrade")
    public String updateGrade(CrewVO vo, Model model) {
        log.info("updateGrade");
        log.info(vo.toString());

        service.updateGrade(vo);

        return "완료";
    }
}
