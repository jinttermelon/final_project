package org.multi.final_project.event;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.crew.CrewVO;
import org.multi.final_project.crewboard.CrewBoardVO;
import org.multi.final_project.friend.FriendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequestMapping("/event")
@Controller
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping("/applyDone")
    public String applyDone() {
        return "event/applyDone";
    }

    @GetMapping("/myevent")
    public String myevent() {
        return "event/myevent";
    }

    @GetMapping("/insert")
    public String insert() {
        return "event/insert";
    }

    @GetMapping("/eventManagement")
    public String eventManagement() {
        return "event/eventManagement";
    }


    @PostMapping("/insertOK")
    public String insertOK(EventVO vo) {


        int result = service.insertOK(vo);
        return "redirect:selectAll";
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "10") int limit, Model model , EventVO vo){

        int startRow = (cpage - 1) * limit;
        List<EventVO> vos = service.selectAll(startRow, limit);
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


        return "event/selectAll";
    }

    @GetMapping("/participantList")
    public String participantList(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "10") int limit,
                            Model model){

        int startRow = (cpage - 1) * limit;
        List<EventVO> vos = service.selectAll(startRow, limit);
        log.info(vos.toString());
        model.addAttribute("vos", vos);
        return "event/participantList";
    }

    @GetMapping("/selectOne")
    public String selectOne(EventVO vo,Model model) {
        return "event/selectOne";
    }

    @GetMapping("/deleteOK")
    public String deleteOK(EventVO vo) {
        return "redirect:selectAll";
    }

}
