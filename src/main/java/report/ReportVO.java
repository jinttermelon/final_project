package report;

import lombok.Data;

@Data
public class ReportVO {
    private int num;
    private String nickname;
    private String targetNickname;
    private String detail;
    private String reason;
    private String state;
    private String rdate;
}
