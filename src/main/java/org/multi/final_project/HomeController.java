package org.multi.final_project;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.report.ReportService;
import org.multi.final_project.report.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private ReportService service;

    @GetMapping({"/","/home"})
    public String home() {
        return "home";
    }


}
