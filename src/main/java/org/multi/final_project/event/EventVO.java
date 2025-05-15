package org.multi.final_project.event;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EventVO {
    private String title;
    private int num;
    private String city;
    private String prize;
    private String dur;
    private int partici_num;
    private String event_type;
    private String img_name;
    private MultipartFile file;
}
