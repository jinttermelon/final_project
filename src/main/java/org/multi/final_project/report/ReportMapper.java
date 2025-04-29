package org.multi.final_project.report;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {

    public int insertOK(ReportVO vo);

    public ReportVO selectOne(ReportVO vo);

    public List<ReportVO> selectAll(int cpage, int limit);
}
