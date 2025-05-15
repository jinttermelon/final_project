package org.multi.final_project.user;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @Value("${file.dir}")
    private String realPath;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    @GetMapping("/userAdmin")
    public String userAdmin(UserVO vo, Model model) {

        UserVO vo2 = service.selectOne(vo);
        model.addAttribute("vo2", vo2);

        return "user/userAdmin";
    }

    @GetMapping("/adminMenu")
    public String adminMenu(UserVO vo, Model model) {

        UserVO vo2 = service.selectOne(vo);
        model.addAttribute("vo2", vo2);

        return "user/adminMenu";
    }

    @GetMapping("/insert")
    public String insert() {
        return "user/insert";
    }

    @GetMapping("/ridingHistory")
    public String ridingHistory() {
        return "user/ridingHistory";
    }

    @GetMapping("/mypage")
    public String mypage(UserVO vo, Model model) {

        UserVO vo2 = service.selectOne(vo);
        model.addAttribute("vo2", vo2);

        return "user/mypage";
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
        vo.setPw(passwordEncoder.encode(vo.getPw()));

        vo.setTel(vo.getTel_company()+"-"+vo.getTel01()+"-"+vo.getTel02()+"-"+vo.getTel03());
        vo.setSido(vo.getSido01()+"/"+vo.getSido02());
        log.info("vo : {}",vo);
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

        UserVO vo2 = service.selectOne(vo);
        String[] temp = vo2.getTel().split("-");
        vo2.setTel_company(temp[0]);
        vo2.setTel01(temp[1]);
        vo2.setTel02(temp[2]);
        vo2.setTel03(temp[3]);
        log.info("vo2:" + vo2);
        model.addAttribute("vo2", vo2);
        return "user/update";
    }

    @PostMapping("/updateOK")
    public String updateOK(UserVO vo) throws IOException {

        log.info("realPath : {}",realPath);

        String originName = vo.getFile().getOriginalFilename();
        log.info("originName : {}",originName);

        if(originName.length() == 0){//파일첨부안되었을때는 기본이미지이름으로 설정.
            //vo.setImg_name("default.png");
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
        vo.setPw(passwordEncoder.encode(vo.getPw()));
        vo.setTel(vo.getTel_company()+"-"+vo.getTel01()+"-"+vo.getTel02()+"-"+vo.getTel03());
        vo.setSido(vo.getSido01()+"/"+vo.getSido02());
        vo.setRole((String) session.getAttribute("role"));
        log.info("vo : {}",vo);

        service.updateOK(vo);


        if(session.getAttribute("role").equals("ADMIN")){
            return "redirect:selectAll";
        }else{
            return "redirect:/user/mypage?nickname="+vo.getNickname();
        }

    }

    @GetMapping("/delete")
    public String delete() {
        return "user/delete";
    }

    @PostMapping("/deleteOK")
    public String deleteOK(UserVO vo) {

        log.info(vo.toString());


        service.deleteOK(vo);
        if(session.getAttribute("role").equals("ADMIN")){
            return "redirect:selectAll";
        }else{
            return "redirect:/home";
        }

    }

    @GetMapping("/login_form")
    public String login_form() {

        return "user/login_form";
    }

    @GetMapping("/required")
    public String required_login() {

        return "user/required";
    }

    @PostMapping("/loginFail")
    public String login_fail() {
        return "user/loginFail";
    }

    @PostMapping("/loginSuccess")
    public String login_success() {

        session.getAttribute("id");
        log.info("{}",session.getAttribute("id"));

        UserVO vo2 = service.getNickname(session.getAttribute("id").toString());
        log.info("vo2:" + vo2);

        session.setAttribute("nickname", vo2.getNickname());
        session.setAttribute("id", vo2.getId());
        session.setAttribute("role", vo2.getRole());

        return "user/loginSuccess";
    }

    @GetMapping("/denied")
    public String denied() {
        return "user/accessDenied";
    }


    @PostMapping("/findIdOK")
    public String findIdOK(UserVO vo) {
        log.info("/user/findIdOK");
        log.info(vo.toString());

        vo.setTel(vo.getTel_company()+"-"+vo.getTel01()+"-"+vo.getTel02()+"-"+vo.getTel03());
        String id = service.findIdOK(vo);//전화번호와 출생년도로 아이디 얻어오기
        if(id!=null){
            return "redirect:/user/findIdSuccess?id="+id;
        }else{
            return "redirect:/user/findIdFail";
        }
    }

    @GetMapping("/findIdSuccess")
    public String findIdSuccess() {
        return "user/findIdSuccess";
    }

    @GetMapping("/findIdFail")
    public String findIdFail() {
        return "user/findIdFail";
    }

    @PostMapping("/findPwOK")
    public String findPwOK(UserVO vo) throws UnsupportedEncodingException {
        log.info("/user/findPwOK");

        vo.setTel(vo.getTel_company()+"-"+vo.getTel01()+"-"+vo.getTel02()+"-"+vo.getTel03());
        log.info(vo.toString());

        UserVO vo2 = service.findPwOK(vo);//아이디,전화번호, 출생년도로 vo2 얻어오기

        if(vo2 != null){
            return "redirect:/user/findPwSuccess?id="+vo2.getId();
        }else{
            return "redirect:/user/findPwFail";
        }
    }

    @GetMapping("/findPwSuccess")
    public String findPwSuccess() {
        return "user/findPwSuccess";
    }

    @GetMapping("/findPwFail")
    public String findPwFail() {
        return "user/findPwFail";
    }

    @PostMapping("/updatePwOK")
    public String updatePwOK(UserVO vo) throws UnsupportedEncodingException {
        log.info("/user/updatePwOK");

        vo.setTel(vo.getTel_company()+"-"+vo.getTel01()+"-"+vo.getTel02()+"-"+vo.getTel03());
        vo.setPw(passwordEncoder.encode(vo.getPw()));
        log.info(vo.toString());

        int result = service.updatePwOK(vo);//아이디로 비번변경하기

        if(result == 1){
            return "redirect:/user/login_form";
        }else{
            return "redirect:/user/findPwSuccess?id="+vo.getId();
        }
    }

}
