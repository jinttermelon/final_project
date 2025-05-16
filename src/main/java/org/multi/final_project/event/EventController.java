package org.multi.final_project.event;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.crew.CrewVO;
import org.multi.final_project.crewboard.CrewBoardVO;
import org.multi.final_project.friend.FriendVO;
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
@RequestMapping("/event")
@Controller
public class EventController {
    @Value("${file.dir}")
    private String realPath;

    @Autowired
    private EventService service;

    @GetMapping("/update")
    public String update() {
        return "event/update";
    }

    @GetMapping("/applyDone")
    public String applyDone() {
        return "event/applyDone";
    }

    @GetMapping("/myevent")
    public String myevent() {
        return "event/myevent";
    }


    @GetMapping("/eventManagement")
    public String eventManagement(@RequestParam(defaultValue = "1") int cpage,
                                  @RequestParam(defaultValue = "10") int limit, Model model, EventVO vo) {

        int startRow = (cpage - 1) * limit;
        List<EventVO> vos = service.selectAll(startRow, limit);
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

        return "event/eventManagement";
    }

    @GetMapping("/insert")
    public String insert() {
        return "event/insert";
    }

    @PostMapping("/insertOK")
    public String insertOK(EventVO vo) throws IOException {


        log.info("realPath : {}", realPath);

        String originName = vo.getFile().getOriginalFilename();
        log.info("originName : {}", originName);

        if (originName.length() == 0) {//파일첨부안되었을때는 기본이미지이름으로 설정.
            vo.setImg_name("default.png");
        } else {
            //중복파일명 배제하는 처리. ex: img_387483924732743.png
            String save_name = "img_" + System.currentTimeMillis() + originName.substring(originName.lastIndexOf("."));
            log.info("save_name : {}", save_name);
            vo.setImg_name(save_name);//디비에 들어갈 이미지명 세팅

            File f = new File(realPath, save_name);
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

        int result = service.insertOK(vo);
        return "redirect:eventManagement";
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "10") int limit, Model model, EventVO vo) {

        int startRow = (cpage - 1) * limit;
        List<EventVO> vos = service.selectAll(startRow, limit);
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


        return "event/selectAll";
    }

    @GetMapping("/participantList")
    public String participantList(@RequestParam(defaultValue = "1") int cpage,
                                  @RequestParam(defaultValue = "10") int limit,
                                  Model model) {

        int startRow = (cpage - 1) * limit;
        List<EventVO> vos = service.selectAll(startRow, limit);
        log.info(vos.toString());
        model.addAttribute("vos", vos);
        return "event/participantList";
    }

    @GetMapping("/selectOne")
    public String selectOne(EventVO vo, Model model) {
        return "event/selectOne";
    }

    @GetMapping("/delete")
    public String delete() {
        return "event/delete";
    }

    @GetMapping("/deleteOk")
    public String deleteOk() {
        return "event/deleteOk";
    }


}