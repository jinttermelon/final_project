package org.multi.final_project.cos;

import lombok.Data;
import org.multi.final_project.cosreview.CosReviewVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CosVO {

    private int cos_num;
    private String nickname; // 작성자
    private String cos_name;
    private String info;
    private String km;
    private int kal;
    private int cos_time;
    private String route;
    private String cdate;
    private int like_count;
    private int review_count;
    private String img_name;
    private String level;
    private String open;
    private String tag;
    private String city;
    private List<CosReviewVO> reviewList;
    private MultipartFile file;
}
