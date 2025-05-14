package org.multi.final_project.crewjoin;


import lombok.Data;
import org.multi.final_project.crew.CrewVO;

import java.sql.Timestamp;

@Data
public class CrewJoinVO {
    private String nickname;
    private String answer;
    private int num;
    private int cnum;
    private String approve;
    private String join_date;
    private CrewVO crewVO;

}
