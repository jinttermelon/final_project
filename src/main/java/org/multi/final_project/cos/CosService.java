package org.multi.final_project.cos;

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
    public CosVO selectOne(CosVO vo){
        return mapper.selectOne(vo);
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

}
