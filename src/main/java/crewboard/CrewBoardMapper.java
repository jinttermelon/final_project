package crewboard;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CrewBoardMapper {
    public int insertOK(CrewBoardVO vo);
    public int updateOK(CrewBoardVO vo);
    public int deleteOK(CrewBoardVO vo);
    public List<CrewBoardVO> selectAll(int cpage, int limit, CrewBoardVO vo);
    public CrewBoardVO selectOne(CrewBoardVO vo);
    public List<CrewBoardVO>searchList(String searchKey, String searchWord, int cpage, int limit);

}
