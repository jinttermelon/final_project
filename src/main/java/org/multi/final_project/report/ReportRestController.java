package org.multi.final_project.report;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportRestController {

    @Autowired
    private ReportService service;

    @GetMapping("/updateStatus")
    public String updateStatus(ReportVO vo, Model model) {
        log.info("updateStatus");
        log.info(vo.toString());

        service.updateStatus(vo);

        return "완료";
    }
}
