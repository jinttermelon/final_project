package org.multi.final_project.cosreview;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CosReviewMapper {

    public int insertOK(CosReviewVO vo);
    public int updateOK(CosReviewVO vo);
    public int deleteOK(CosReviewVO vo);
    public List<CosReviewVO> selectAll(int cpage,int limit, CosReviewVO vo);
    public CosReviewVO selectOne(CosReviewVO vo);
}
