package org.multi.final_project.coslike;

import org.multi.final_project.cos.CosVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosLikeService {

    @Autowired
    private CosLikeMapper mapper;

    public int insertOK(CosLikeVO vo){
        return mapper.insertOK(vo);
    }
    public int deleteOK(CosLikeVO vo){
        return mapper.deleteOK(vo);
    }
    public List<CosLikeVO> selectAll(int startRow, int limit){
        return mapper.selectAll(startRow,limit);
    }
}
