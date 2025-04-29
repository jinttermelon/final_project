package org.multi.final_project.report;

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
    public List<ReportVO> selectAll(int startRow, int limit){
        return mapper.selectAll(startRow,limit);
    }
}
