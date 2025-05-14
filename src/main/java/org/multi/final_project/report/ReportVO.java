package org.multi.final_project.report;

import lombok.Data;

@Data
public class ReportVO {
    private int num;
    private String nickname;
    private String target_nickname;
    private String detail;
    private String reason;
    private String state;
    private String rdate;
}
