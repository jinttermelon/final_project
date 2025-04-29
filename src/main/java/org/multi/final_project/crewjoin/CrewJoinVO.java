package org.multi.final_project.crewjoin;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class CrewJoinVO {
    private String nickname;
    private String answer;
    private int num;
    private int cnum;
    private boolean approve;
    private String join_date;

}
