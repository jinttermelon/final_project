package crew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("crew")
@Controller
public class CrewController {
    @Autowired
    private CrewService service;

    @GetMapping("/create")
    public String create(){
        return "crew/create";
    }
    @PostMapping("/createOK")
    public String createOK(CrewVO vo){
        return "redirect:/selectAll";
    }
    @GetMapping("/update")
    public String update(){
        return "crew/update";
    }
    @PostMapping("/updateOK")
    public String updateOK(CrewVO vo){
        return "redirect:/selectAll";
    }
    @GetMapping("/delete")
    public String delete(){
        return "crew/delete";
    }
    @GetMapping("/deleteFinish")
    public String deleteFinish(){
        return "crew/deleteFinish";
    }
    @PostMapping("/deleteOK")
    public String deleteOK(CrewVO vo){
        return "redirect:/deleteFinish";
    }
    @GetMapping("/selectAll")
    public String selectAll(int cpage, int limit, Model model ){
        return "crew/selectAll";
    }
    @GetMapping("/selectOne")
    public String selectOne(CrewVO vo, Model model ){
        return "crew/selectOne";
    }
    @GetMapping("/searchList")
    public String searchList(String searchKey, String searchWord, int cpage, int limit, Model model ){
        return "crew/searchAll";
    }

}
