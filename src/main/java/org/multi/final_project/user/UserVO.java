package org.multi.final_project.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserVO {
    private String id;
    private int num;
    private String pw;
    private String nickname;
    private String birth_year;
    private String gender;
    private String sido;
    private String sido01;
    private String sido02;
    private String riding_year;
    private String tel;
    private String tel_company;
    private String tel01;
    private String tel02;
    private String tel03;
    private String tel_open;
    private String img_name;
    private String role;
    private String report;
    private MultipartFile file;

}
