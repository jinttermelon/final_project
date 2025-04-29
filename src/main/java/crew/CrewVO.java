package crew;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CrewVO {
    private int cnum;
    private String name;
    private String sido;
    private int recruit;
    private String sche;
    private String question;
    private int score;
    private String img_name;
    private MultipartFile file;
    private String leader_nickname;
    private String grade;
    private boolean sche_check;
    private String usergrade;













}
