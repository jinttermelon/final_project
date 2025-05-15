package org.multi.final_project.cos;

import org.apache.ibatis.annotations.Mapper;
import org.multi.final_project.crewboard.CrewBoardVO;

import java.util.List;

@Mapper
public interface CosMapper {
    public int insertOK(CosVO vo);
    public int updateOK(CosVO vo);
    public int deleteOK(CosVO vo);
    public List<CosVO> selectAll(int startRow, int limit);
    public CosVO selectOne(CosVO vo);
    public List<CosVO> searchList(String searchKey, String searchWord, int startRow, int limit);
    public int totalRowCount(CosVO vo);
}
