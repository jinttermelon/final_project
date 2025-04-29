package crewboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("crewboard")
@Controller
public class CrewBoardController {
    @Autowired
    private CrewBoardService service;

    @GetMapping("insert")
    public String insert(){
        return "crewboard/insert";
    }
    @PostMapping ("insertOK")
    public String insertOK(CrewBoardVO vo){
        return "redirect:/selectAll";
    }
    @GetMapping("update")
    public String update(){
        return "crewboard/update";
    }
    @PostMapping ("updateOK")
    public String updateOK(CrewBoardVO vo){
        return "redirect:/selectAll";
    }
    @GetMapping("delete")
    public String delete(){
        return "crewboard/delete";
    }
    @PostMapping ("deleteOK")
    public String deleteOK(CrewBoardVO vo){
        return "redirect:/selectAll";
    }
    @GetMapping("selectAll")
    public String selectAll(int cpage, int limit, Model model, CrewBoardVO vo){
        return "crewboard/selectAll";
    }
    @GetMapping("selectOne")
    public String selectOne(CrewBoardVO vo, Model model){
        return "crewboard/selectOne";
    }
    @GetMapping("searchList")
    public String searchList(String searchKey, String searchWord, int cpage, int limit, Model model){
        return "crewboard/selectAll";
    }

}
