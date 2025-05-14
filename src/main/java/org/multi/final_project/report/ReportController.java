package org.multi.final_project.report;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.crewboard.CrewBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequestMapping("/report")
@Controller
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping("/insert")
    public String insert() {

        return "report/insert";
    }

    @PostMapping("/insertOK")
    public String insertOK(ReportVO vo) {

        service.insertOK(vo);

        return "redirect:/report/selectAll?nickname=" + vo.getNickname();
    }

    @GetMapping("selectAll")
    public String selectAll(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "10") int limit,
                            Model model,
                            ReportVO vo){

        int startRow = (cpage - 1) * limit;
        List<ReportVO> vos = service.selectAll(startRow, limit, vo);
        model.addAttribute("vos", vos);

        // 페이지네이션
        int totalRowCount = service.getTotalRowCount(vo);
        log.info("total row count: {}", totalRowCount);

        int pageCount = 1;
        if (totalRowCount / limit==0){
            pageCount = 1;
        }else if(totalRowCount % limit==0){
            pageCount = totalRowCount / limit;
        }else {
            pageCount = totalRowCount / limit +1;
        }
        log.info("page count: {}", pageCount);

        model.addAttribute("pageCount", pageCount);

        return "report/selectAll";
    }

    @GetMapping("/selectOne")
    public String selectOne(ReportVO vo, Model model) {
        return "report/selectOne";
    }
}
