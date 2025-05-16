package org.multi.final_project.crewboard;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.multi.final_project.crew.CrewVO;

import java.util.List;

@Mapper
public interface CrewBoardMapper {

    public int insertOK(CrewBoardVO vo);
    public int updateOK(CrewBoardVO vo);
    public int deleteOK(CrewBoardVO vo);
    public List<CrewBoardVO> selectAll(int startRow, int limit , int cnum);
    public CrewBoardVO selectOne(CrewBoardVO vo);
    public List<CrewBoardVO>searchList( int cnum,String searchKey, String searchWord, int startRow, int limit);
    public int totalRowCount(CrewBoardVO vo);
    public int searchListTotalRowCount(int cnum,String searchKey, String searchWord);
    public List<CrewBoardVO> myBoardSelectAll(int startRow, int limit , @Param("nickname") String nickname);
    public List<CrewBoardVO> selectByUser(String nickname, int startRow, int limit);

    public int getTotalByUser(String nickname);
}
