package org.multi.final_project.report;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.multi.final_project.crewboard.CrewBoardVO;

import java.util.List;

@Mapper
public interface ReportMapper {

    public int insertOK(ReportVO vo);

    public ReportVO selectOne(ReportVO vo);

    public List<ReportVO> selectAll(int startRow, int limit, @Param("nickname") String nickname);
    public int totalRowCount(ReportVO vo);

    public List<ReportVO> selectByReporter(String nickname, int startRow, int limit);

    int getTotalByReporter(String nickname);
}
