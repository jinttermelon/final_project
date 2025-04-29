package org.multi.final_project.crew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CrewRestController {
    @Autowired
    private CrewService service;

    @GetMapping("/nameCheck")
    public Map<String,String> nameCheck(CrewVO vo){
        return null;
    }
}
