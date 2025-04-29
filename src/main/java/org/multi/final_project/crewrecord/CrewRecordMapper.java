package org.multi.final_project.crewrecord;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface CrewRecordMapper {
    public int insertOK(CrewRecordVO vo);
    public int updateOK(CrewRecordVO vo);
    public int deleteOK(CrewRecordVO vo);
    public List<CrewRecordVO> selectAll(int cpage, int limit, CrewRecordVO vo);
    public CrewRecordVO selectOne(CrewRecordVO vo);
    public List<CrewRecordVO> searchList(String searchKey, String searchWord, int cpage, int limit, CrewRecordVO vo);

}

