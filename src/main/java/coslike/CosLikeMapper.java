package coslike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CosLikeMapper {

    public int insertOK(CosLikeVO vo);
    public int deleteOK(CosLikeVO vo);
    public List<CosLikeVO> selectAll(int cpage,int limit, CosLikeVO vo);

}
