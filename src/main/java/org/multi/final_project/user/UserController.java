package org.multi.final_project.user;

import lombok.extern.slf4j.Slf4j;
import org.multi.final_project.crew.CrewVO;
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
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @Value("${file.dir}")
    private String realPath;

    @GetMapping("/insert")
    public String insert() {
        return "user/insert";
    }

    @GetMapping("/findId")
    public String findId() {
        return "user/findId";
    }

    @GetMapping("/findPw")
    public String findPw() {
        return "user/findPw";
    }

    @GetMapping("/updatePw")
    public String updatePw() {
        return "user/updatePw";
    }

    @PostMapping("/insertOK")
    public String insertOK(UserVO vo) throws IOException {

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
        return "user/insertFinish";
    }

    @GetMapping("/banUser")
    public String banUser() {
        return "user/banForm";
    }

    @PostMapping("/banUserOK")
    public String banUserOK(UserVO vo) {
        return "redirect:/user/selectAll";
    }

    @GetMapping("/selectAll")
    public String selectAll(@RequestParam(defaultValue = "1") int cpage,
                            @RequestParam(defaultValue = "10") int limit,
                            Model model,
                            UserVO vo) {


        int startRow = (cpage - 1) * limit;
        log.info("startRow:" + startRow);
        List<UserVO> vos = service.selectAll(startRow, limit);
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

        return "user/selectAll";
    }

    @GetMapping("/selectOne")
    public String selectOne(UserVO vo, Model model) {

        UserVO vo2 = service.selectOne(vo);
        log.info("vo2:" + vo2);
        model.addAttribute("vo2", vo2);
        return "user/selectOne";
    }

    @GetMapping("/update")
    public String update(UserVO vo, Model model) {

        return "user/update";
    }

    @PostMapping("/updateOK")
    public String updateOK(UserVO vo) {
        return "redirect:/user/selectAll";
    }

    @GetMapping("/delete")
    public String delete() {
        return "user/delete";
    }

    @PostMapping("/deleteOK")
    public String deleteOK(UserVO vo) {
        return "redirect:/user/selectAll";
    }

    @GetMapping("/login")
    public String login_form() {
        return "user/login";
    }

    @GetMapping("/required")
    public String required_login() {
        return "user/required";
    }

    @GetMapping("/fail")
    public String login_fail() {
        return "user/loginFail";
    }

    @GetMapping("/success")
    public String login_success() {
        return "user/loginSuccess";
    }

    @GetMapping("/denied")
    public String denied() {
        return "user/accessDenied";
    }



}
