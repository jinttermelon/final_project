package crewjoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CrewJoinController {
    @Autowired
    private CrewJoinService service;

    @GetMapping("/join")
    public String join(){
        return "crewjoin/join";
    }
    @PostMapping("/joinOK")
    public String joinOK(CrewJoinVO vo){
        return "redirect:/selectAll";
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
    public String selectAll(int cpage, int limit, Model model ){
        return "crewjoin/selectAll";
    }
    @GetMapping("/selectOne")
    public String selectOne(CrewJoinVO vo, Model model ){
        return "crewjoin/selectOne";
    }
}

