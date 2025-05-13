package org.multi.final_project.coslike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.multi.final_project.cos.CosVO;

import java.util.List;

@Mapper
public interface CosLikeMapper {

    public int insertOK(CosLikeVO vo);
    public int deleteOK(CosLikeVO vo);
    public List<CosLikeVO> selectAll(@Param("vo") CosLikeVO vo, int startRow, int limit);
    public int checkLike(String nickname, int cos_num);
}
