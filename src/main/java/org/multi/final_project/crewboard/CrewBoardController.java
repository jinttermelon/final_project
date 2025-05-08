package org.multi.final_project.crewboard;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.multi.final_project.cos.CosVO;
import org.multi.final_project.crew.CrewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
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

        log.info("insert ok:{}",vo);

        if (vo.getNotice() == null){
            vo.setNotice("일반");
        }else {
            vo.setNotice("공지");
        }

        service.insertOK(vo);


        return "redirect:selectAll?cnum="+vo.getCnum();
    }
    @GetMapping("update")
    public String update(CrewBoardVO vo, Model model){
        CrewBoardVO vo2 = service.selectOne(vo);
        model.addAttribute("vo2", vo2);
        return "crewboard/update";
    }
    @PostMapping ("updateOK")
    public String updateOK(CrewBoardVO vo){

        int result = service.updateOK(vo);
        return "redirect:selectOne?bnum="+vo.getBnum();
    }
    @GetMapping("delete")
    public String delete(){
        return "crewboard/delete";
    }

    @GetMapping ("deleteOK")
    public String deleteOK(CrewBoardVO vo){

        int result = service.deleteOK(vo);
        return "redirect:selectAll?cnum="+vo.getCnum();
    }
    @GetMapping("selectAll")
    public String selectAll(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "1") int cnum,
                            @RequestParam(defaultValue = "10") int limit,
                            Model model,
                            CrewBoardVO vo){

        int startRow = (cpage - 1) * limit;
        List<CrewBoardVO> vos = service.selectAll(startRow, limit, cnum);
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

        return "crewboard/selectAll";
    }
    @GetMapping("selectOne")
    public String selectOne(CrewBoardVO vo, Model model){

        CrewBoardVO vo2 = service.selectOne(vo);
        model.addAttribute("vo2", vo2);
        return "crewboard/selectOne";
    }

    @GetMapping("searchList")
    public String searchList(@RequestParam(defaultValue = "1") int cpage,
                             @RequestParam(defaultValue = "10") int limit,
                             @RequestParam(defaultValue = "title") String searchKey,
                             @RequestParam(defaultValue = "") String searchWord,
                             Model model,
                             int cnum){

        int startRow = (cpage - 1) * limit;

        log.info("/crewboard/searchList");
        log.info("cpage: {}", cpage);
        log.info("limit: {}", limit);
        log.info("searchKey: {}", searchKey);
        log.info("searchWord: {}", searchWord);
        log.info("cnum: {}",cnum);

        // startRow를 매퍼로 전달
        List<CrewBoardVO> vos = service.searchList(cnum,searchKey,searchWord,startRow,limit);
        model.addAttribute("vos", vos);

        //페이지네이션
        int totalRowCount = service.getSearchListTotalRowCount(cnum,searchKey,searchWord);
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

        return "crewboard/selectAll";
    }

}
