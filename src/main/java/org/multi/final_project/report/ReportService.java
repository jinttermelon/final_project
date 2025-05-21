package org.multi.final_project.report;

import org.multi.final_project.crewboard.CrewBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReportService {
    @Autowired
    private ReportMapper mapper;

    public int insertOK(ReportVO vo){
        return mapper.insertOK(vo);
    }
    public ReportVO selectOne(ReportVO vo){
        return mapper.selectOne(vo);
    }
    public List<ReportVO> selectAll(int startRow, int limit ,ReportVO vo){
        return mapper.selectAll(startRow,limit, vo.getNickname());
    }
    public int getTotalRowCount(ReportVO vo) {
        return mapper.totalRowCount(vo);
    }

    public List<ReportVO> selectByReporter(String nickname, int startRow, int limit) {
        return mapper.selectByReporter(nickname,startRow,limit);
    }

    public int getTotalByReporter(String nickname) {
        return mapper.getTotalByReporter(nickname);
    }

    public List<ReportVO> reportAdminSelectAll(int startRow, int limit) {
        return mapper.reportAdminSelectAll(startRow,limit);
    }

    public int updateStatus(ReportVO vo) {
        return mapper.updateStatus(vo);
    }
}
