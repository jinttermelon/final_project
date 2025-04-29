package crew;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CrewMapper {
    public int createOK(CrewVO vo);

    public int updateOK(CrewVO vo);

    public int deleteOK(CrewVO vo);

    public List<CrewVO> selectAll(int cpage, int limit, CrewVO vo);

    public CrewVO selectOne(CrewVO vo);

    public List<CrewVO> searchList(String searchKey, String searchWord, int cpage, int limit);

    public CrewVO nameCheck(CrewVO vo);
}
