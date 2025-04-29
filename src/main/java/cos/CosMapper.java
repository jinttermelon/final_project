package cos;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CosMapper {
    public int insertOK(CosVO vo);
    public int updateOK(CosVO vo);
    public int deleteOK(CosVO vo);
    public List<CosVO> selectAll(int cpage, int limit, CosVO vo);
    public CosVO selectOne(CosVO vo);
    public List<CosVO> searchList(String searchKey, String searchWord, int cpage, int limit);
}
