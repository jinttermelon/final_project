package org.multi.final_project.crew;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.multi.final_project.crewjoin.CrewJoinService;
import org.multi.final_project.crewjoin.CrewJoinVO;
import org.multi.final_project.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequestMapping("crew")
@Controller
public class CrewController {


    @Autowired
    private CrewService service;

    @Autowired
    private CrewJoinService joinService;

    @Value("${file.dir}")
    private String realPath;

    @GetMapping("/crewAdmin")
    public String crewAdmin(){
        return "crew/crewAdmin";
    }

    @GetMapping("/create")
    public String create(){
        return "crew/create";
    }
    @PostMapping("/createOK")
    public String createOK(CrewVO vo) throws IOException {

        log.info("realPath : {}",realPath);

        String originName = vo.getFile().getOriginalFilename();
        log.info("originName : {}",originName);

        if(originName.length() == 0){//파일첨부안되었을때는 기본이미지이름으로 설정.
            vo.setImg_name("default.png");
        }else{
            //중복파일명 배제하는 처리. ex: img_387483924732743.png
            String save_name = "img_"+ System.currentTimeMillis()+originName.substring(originName.lastIndexOf("."));
            log.info("save_name : {}",save_name);
            vo.setImg_name(save_name);//디비에 들어갈 이미지명 세팅

            File f = new File(realPath,save_name);
            vo.getFile().transferTo(f);//파일 저장...

            //작은이미지로 만들어서 저장하기
            //// create thumbnail image/////////
            BufferedImage original_buffer_img = ImageIO.read(f);
            BufferedImage thumb_buffer_img = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphic = thumb_buffer_img.createGraphics();
            graphic.drawImage(original_buffer_img, 0, 0, 50, 50, null);

            File thumb_file = new File(realPath, "thumb_" + save_name);

            ImageIO.write(thumb_buffer_img, save_name.substring(save_name.lastIndexOf(".") + 1), thumb_file);
        }//end if

        service.createOK(vo);

        int cnum = service.getLastCnum();
        log.info("cnum : {}",cnum);

        CrewJoinVO joinVO = new CrewJoinVO();
        joinVO.setCnum(cnum);
        joinVO.setNickname(vo.getLeader_nickname());
        joinVO.setAnswer("");
        joinVO.setApprove("승인");
        joinService.joinOK(joinVO);
        return "redirect:selectAll";
    }
    @GetMapping("/update")
    public String update(CrewVO vo, Model model){

        CrewVO vo2 = service.selectOne(vo);
        model.addAttribute("vo2", vo2);
        return "crew/update";
    }
    @PostMapping("/updateOK")
    public String updateOK(CrewVO vo) throws IOException {

        log.info("realPath : {}",realPath);

        String originName = vo.getFile().getOriginalFilename();
        log.info("originName : {}",originName);

        if(originName.length() == 0){//파일첨부안되었을때는 기본이미지이름으로 설정.
            vo.setImg_name("default.png");
        }else{
            //중복파일명 배제하는 처리. ex: img_387483924732743.png
            String save_name = "img_"+ System.currentTimeMillis()+originName.substring(originName.lastIndexOf("."));
            log.info("save_name : {}",save_name);
            vo.setImg_name(save_name);//디비에 들어갈 이미지명 세팅

            File f = new File(realPath,save_name);
            vo.getFile().transferTo(f);//파일 저장...

            //작은이미지로 만들어서 저장하기
            //// create thumbnail image/////////
            BufferedImage original_buffer_img = ImageIO.read(f);
            BufferedImage thumb_buffer_img = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphic = thumb_buffer_img.createGraphics();
            graphic.drawImage(original_buffer_img, 0, 0, 50, 50, null);

            File thumb_file = new File(realPath, "thumb_" + save_name);

            ImageIO.write(thumb_buffer_img, save_name.substring(save_name.lastIndexOf(".") + 1), thumb_file);
        }//end if

        service.updateOK(vo);

        return "redirect:/crew/selectAll";
    }
    @GetMapping("/delete")
    public String delete(){
        return "crew/delete";
    }
    @GetMapping("/deleteFinish")
    public String deleteFinish(){
        return "crew/deleteFinish";
    }


    @GetMapping("/deleteOK")
    public String deleteOK(@RequestParam("cnum") int cnum, HttpSession session) {

        String role = session.getAttribute("role").toString();
        String nickname = session.getAttribute("nickname").toString();

        log.info("nickname : " + nickname);
        log.info("role : " + role);

        // crew 정보를 DB에서 직접 조회
        CrewVO vo = service.getCrewByCnum(cnum);
        log.info("crew.getLeader_nickname : " + vo.getLeader_nickname());

        // 관리자이거나, 리더일 경우만 삭제
        if ("ADMIN".equals(role) || nickname.equals(vo.getLeader_nickname())) {
            service.deleteOK(vo);
            return "redirect:/crew/deleteFinish";
        } else
        {
            return "redirect:/crew/selectAll";
        }

    }

    @GetMapping("/crewlistAdmin")
    public String crewlistAdmin(@RequestParam(defaultValue = "1") int cpage,
                                @RequestParam(defaultValue = "10") int limit, Model model , CrewVO vo){

        int startRow = (cpage - 1) * limit;
        List<CrewVO> vos = service.selectAll(startRow, limit);
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


        return "crew/crewlistAdmin";
    }
    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "10") int limit, Model model , CrewVO vo){

        int startRow = (cpage - 1) * limit;
        List<CrewVO> vos = service.selectAll(startRow, limit);
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


        return "crew/selectAll";
    }
    @GetMapping("/selectOne")
    public String selectOne(CrewVO vo, Model model,HttpSession session ){

        CrewVO vo2 = service.selectOne(vo);
        model.addAttribute("vo2", vo2);

        String sessionNickname = (String) session.getAttribute("nickname");
        model.addAttribute("sessionNickname", sessionNickname);

        return "crew/selectOne";
    }
    @GetMapping("/searchList")
    public String searchList(
            @RequestParam(defaultValue = "1") int cpage,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "name") String searchKey,
            @RequestParam(defaultValue = "") String searchWord,
            Model model,
            CrewVO vo) {

        // 시작 행 계산
        int startRow = (cpage - 1) * limit;

        log.info("/crew/searchList");
        log.info("cpage: {}", cpage);
        log.info("limit: {}", limit);
        log.info("searchKey: {}", searchKey);
        log.info("searchWord: {}", searchWord);

        // startRow를 매퍼로 전달
        List<CrewVO> vos = service.searchList(startRow, limit, searchKey, searchWord);
        model.addAttribute("vos", vos);


        //페이지네이션
        int totalRowCount = service.getSearchListTotalRowCount(vo,searchKey,searchWord);
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

        return "crew/selectAll";
    }

    @GetMapping("/searchListAdmin")
    public String searchCrewListAdmin(
            @RequestParam String searchKey,
            @RequestParam String searchWord,
            @RequestParam(defaultValue = "1") int cpage,
            @RequestParam(defaultValue = "10") int limit,
            Model model) {

        List<CrewVO> vos = service.searchList(searchKey, searchWord, cpage, limit);
        int pageCount = service.getSearchPageCount(searchKey, searchWord, limit);

        model.addAttribute("vos", vos);
        model.addAttribute("pageCount", pageCount);

        return "crew/crewlistAdmin";  // ✅ 여기로 같은 템플릿 사용
    }

}
