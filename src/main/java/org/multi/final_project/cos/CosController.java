package org.multi.final_project.cos;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.ad.AdVO;
import org.multi.final_project.comment.CommentVO;
import org.multi.final_project.coslike.CosLikeService;
import org.multi.final_project.cosreview.CosReviewVO;
import org.multi.final_project.crewboard.CrewBoardVO;
import org.multi.final_project.event.EventVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/cos")
public class CosController {

    @Autowired
    private CosService service;

    @Autowired
    private CosLikeService cosLikeService;

    @Autowired
    private HttpSession session;

    @Value("${file.dir}")
    private String realPath;

    @GetMapping("/cosManagement")
    public String cosManagement(@RequestParam(defaultValue = "1") int cpage,
                                  @RequestParam(defaultValue = "10") int limit, Model model, CosVO vo) {

        int startRow = (cpage - 1) * limit;
        List<CosVO> vos = service.selectAll(startRow, limit);
        model.addAttribute("vos", vos);

        // 페이지네이션
        int totalRowCount = service.getTotalRowCount(vo);
        log.info("total row count: {}", totalRowCount);

        int pageCount = 1;
        if (totalRowCount / limit == 0) {
            pageCount = 1;
        } else if (totalRowCount % limit == 0) {
            pageCount = totalRowCount / limit;
        } else {
            pageCount = totalRowCount / limit + 1;
        }
        log.info("page count: {}", pageCount);

        model.addAttribute("pageCount", pageCount);

        return "cos/cosManagement";
    }

    @GetMapping("/insert")
    public String insert(){
        log.info("insert()...");

        return "cos/insert";
    }

    @PostMapping("/insertOK")
    public String insertOK(CosVO vo) throws IOException {
        log.info("vo:{}",vo);
        log.info("insertOK()...");

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

        service.insertOK(vo);
        log.info(vo.toString());
        return "redirect:selectAll";
    }

    @GetMapping("/update")
    public String update(){
        log.info("update()...");
        return "cos/update";
    }

    @PostMapping("/updateOK")
    public String updateOK(CosVO vo) throws IOException {
        log.info("updateOK()...");
        log.info("vo:{}",vo);



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
        return "redirect:cosManagement";
    }

    @GetMapping("/delete")
    public String delete(){
        log.info("delete()...");
        return "cos/delete";
    }
    @GetMapping("/deleteOK")
    public String deleteOK(CosVO vo){
        log.info("deleteOK()...");
        service.deleteOK(vo);
        return "redirect:cosManagement";
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1")int cpage,
                            @RequestParam(defaultValue = "5")int limit,
                            Model model,
                            CosVO vo){
        int startRow = (cpage - 1) * limit;
        List<CosVO> vos = service.selectAll(startRow, limit);
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
        return "cos/selectAll";
    }

    @GetMapping("/selectOne")
    public String selectOne(Model model,@RequestParam("cos_num") int cos_num){
        log.info("selectOne()...");
        log.info("cos_num:{}", cos_num);

        log.info("nickname:{}", session.getAttribute("nickname"));


        CosVO vo2 = service.selectOne(cos_num);
        model.addAttribute("vo2", vo2);

        List<CosReviewVO> reviews = service.selectReviewsByCosNum(cos_num);
        model.addAttribute("reviews", reviews);

        int checkLike = cosLikeService.checkLike(session.getAttribute("nickname").toString(),cos_num);
        model.addAttribute("checkLike", checkLike);
        log.info("checkLike:{}", checkLike);

        return "cos/selectOne";
    }

    @GetMapping("/searchList")
    public String searchList(
                            String searchKey,
                            String searchWord,
                            @RequestParam(defaultValue = "1")int cpage,
                            @RequestParam(defaultValue = "10")int limit,
                            Model model){
        int startRow = (cpage - 1) * limit;

        log.info("cpage: {}", cpage);
        log.info("limit: {}", limit);
        log.info("searchKey: {}", searchKey);
        log.info("searchWord: {}", searchWord);

        // startRow를 매퍼로 전달
        List<CosVO> vos = service.searchList(searchKey,searchWord,startRow, limit);
        model.addAttribute("vos", vos);
        return "cos/selectAll";
    }

}
