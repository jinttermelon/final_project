package org.multi.final_project.coslike;
import org.apache.ibatis.annotations.Mapper;
import org.multi.final_project.cos.CosVO;

import java.util.List;

@Mapper
public interface CosLikeMapper {

    public int insertOK(CosLikeVO vo);
    public int deleteOK(CosLikeVO vo);
    public List<CosLikeVO> selectAll(int startRow,int limit);

}
