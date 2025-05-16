package org.multi.final_project.cos;

import org.apache.ibatis.annotations.Param;
import org.multi.final_project.cosreview.CosReviewVO;
import org.multi.final_project.crewboard.CrewBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosService {

    @Autowired
    private CosMapper mapper;

    public int insertOK(CosVO vo){
        return mapper.insertOK(vo);
    }
    public int updateOK(CosVO vo){
        return mapper.updateOK(vo);
    }
    public int deleteOK(CosVO vo){
        return mapper.deleteOK(vo);
    }
    public List<CosVO> selectAll(int startRow, int limit){
        return mapper.selectAll(startRow,limit);
    }
    public CosVO selectOne(@Param("cos_num") int cos_num){
        return mapper.selectOne(cos_num);
    }
    public List<CosVO> searchList(String searchKey, String searchWord, int startRow, int limit){
        return mapper.searchList(searchKey,searchWord,startRow,limit);
    }
    public int getTotalRowCount(CosVO vo) {
        return mapper.totalRowCount(vo);
    }
    public List<CosVO> selectTop6ByReviewCount(CosVO vo){
        return mapper.selectTop6ByReviewCount(vo);
    }

    public List<CosReviewVO> selectReviewsByCosNum(@Param("cos_num") int cos_num){
        return mapper.selectReviewsByCosNum(cos_num);
    }

}
