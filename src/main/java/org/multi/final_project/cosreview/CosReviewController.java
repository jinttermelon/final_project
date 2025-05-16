package org.multi.final_project.cosreview;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/cosreview")
public class CosReviewController {

    @Autowired
    private CosReviewService service;

    @GetMapping("/saveReview")
    public String saveReview(){
        log.info("saveReview()...");
        return "cosreview/saveReview";

    }

    @GetMapping("/insert")
    public String insert(){
        log.info("insert()...");
        return "cosreview/insert";

    }

    @PostMapping("/insertOK")
    public String insertOK(CosReviewVO vo){
        log.info("insertOK()...");
        log.info("vo: {}", vo);

        int result = service.insertOK(vo);

        if(result == 1){
            return "redirect:saveReview?cos_num="+vo.getCos_num();
        }else{
            return "redirect:selectOne?cos_num="+vo.getCos_num();
        }


    }

    @GetMapping("/update")
    public String update(){
        log.info("update()...");
        return "cosreview/update";
    }

    @PostMapping("/updateOK")
    public String updateOK(CosReviewVO vo){
        log.info("updateOK()...");
        return "redirect:selectAll";
    }

    @GetMapping("/delete")
    public String delete(){
        log.info("delete()...");
        return "cosreview/delete";
    }
    @PostMapping("/deleteOK")
    public String deleteOK(CosReviewVO vo){
        log.info("deleteOK()...");
        return "redirect:selectAll";
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1")int cpage,
                            @RequestParam(defaultValue = "10")int limit,
                            Model model){
        log.info("selectAll()...");
        return "cosreview/selectAll";
    }

    @GetMapping("/selectOne")
    public String selectOne(CosReviewVO vo){
        log.info("selectOne()...");
        return "cosreview/selectOne";
    }
}
