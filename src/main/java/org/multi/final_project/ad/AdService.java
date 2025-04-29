package org.multi.final_project.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdService {

    @Autowired
    private AdMapper mapper;

    public int insertOK(AdVO vo){
        return mapper.insertOK(vo);
    }
    public int updateOK(AdVO vo){
        return mapper.updateOK(vo);
    }
    public int deleteOK(AdVO vo){
        return mapper.deleteOK(vo);
    }
    public List<AdVO> searchList(String searchKey, String searchWord, int startRow, int limit){
        return mapper.searchList(searchKey, searchWord, startRow, limit);
    }
    public List<AdVO> selectAll(int startRow, int limit) {
        return mapper.selectAll(startRow, limit);
    }


}
