package report;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequestMapping("/report")
@Controller
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping("/insert")
    public String insert() {
        return "insert";
    }

    @PostMapping("/insertOK")
    public String insertOK(ReportVO vo) {
        return "redirect:/report/selectAll";
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam int cpage, @RequestParam int pageSize, Model model) {
        return "report/selectAll";
    }

    @GetMapping("/selectOne")
    public String selectOne(ReportVO vo, Model model) {
        return "report/selectOne";
    }
}
