package org.multi.final_project;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.cos.CosService;
import org.multi.final_project.cos.CosVO;
import org.multi.final_project.crew.CrewService;
import org.multi.final_project.crew.CrewVO;
import org.multi.final_project.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class HomeController {



    @Autowired
    private CosService cosService;

    @Autowired
    private CrewService crewService;

    @GetMapping({"/","/home"})
    public String home(CrewVO crewVO,CosVO cosVO, Model model) {

        List<CosVO> top6 = cosService.selectTop6ByReviewCount(cosVO);
        model.addAttribute("top6", top6);

        List<CrewVO> oneday_crew = crewService.selectOneDayCrew(crewVO);
        model.addAttribute("oneday_crew", oneday_crew);



        return "home";
    }


    @SpringBootApplication
    public static class final_projectApplication {

        public static void main(String[] args) {
            SpringApplication.run(final_projectApplication.class, args);
        }

    }
}
