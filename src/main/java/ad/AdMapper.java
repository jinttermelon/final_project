package ad;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdMapper {

    public int insertOK(AdVO vo);
    public int updateOK(AdVO vo);
    public int deleteOK(AdVO vo);
    public List<AdVO> searchList(String searchKey,String searchWord,int cpage,int limit);
    public List<AdVO> selectAll(int cpage, int limit, AdVO vo);

}
