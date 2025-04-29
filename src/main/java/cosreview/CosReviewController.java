package cosreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cosreview")
public class CosReviewController {

    @Autowired
    private CosReviewService service;

    @GetMapping("/insert")
    public String insert(){
        System.out.println("insert()...");
        return "insert";
    }

    @PostMapping("/insertOK")
    public String insertOK(CosReviewVO vo){
        System.out.println("insertOK()...");
        return "redirect:selectAll";
    }

    @GetMapping("/update")
    public String update(){
        System.out.println("update()...");
        return "update";
    }

    @PostMapping("/updateOK")
    public String updateOK(CosReviewVO vo){
        System.out.println("updateOK()...");
        return "redirect:selectAll";
    }

    @GetMapping("/delete")
    public String delete(){
        System.out.println("delete()...");
        return "delete";
    }
    @PostMapping("/deleteOK")
    public String deleteOK(CosReviewVO vo){
        System.out.println("deleteOK()...");
        return "redirect:selectAll";
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1")int cpage,
                            @RequestParam(defaultValue = "10")int limit,
                            Model model){
        System.out.println("selectAll()...");
        return "selectAll";
    }

    @GetMapping("/selectOne")
    public String selectOne(CosReviewVO vo){
        System.out.println("selectOne()...");
        return "selectOne";
    }
}
