package org.multi.final_project;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private ReportService service;

    @GetMapping({"/","/home"})
    public String home() {
        return "home";
    }


    @SpringBootApplication
    public static class final_projectApplication {

        public static void main(String[] args) {
            SpringApplication.run(final_projectApplication.class, args);
        }

    }
}
