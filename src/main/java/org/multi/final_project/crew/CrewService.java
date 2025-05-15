package org.multi.final_project.crew;

import org.multi.final_project.cos.CosVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrewService {

    @Autowired
    private CrewMapper mapper;

    public int createOK(CrewVO vo){
        return mapper.createOK(vo);
    }
    public int updateOK(CrewVO vo){
        return mapper.updateOK(vo);
    }
    public int deleteOK(CrewVO vo){
        return mapper.deleteOK(vo);
    }
    public List<CrewVO> selectAll(int startRow, int limit){
        return mapper.selectAll(startRow, limit);
    }
    public CrewVO selectOne(CrewVO vo){
        return mapper.selectOne(vo);
    }
    public List<CrewVO> searchList(int startRow, int limit, String searchKey, String searchWord){
        return mapper.searchList(startRow, limit, searchKey, searchWord);
    }
    public CrewVO nameCheck(CrewVO vo){
        return mapper.nameCheck(vo);
    }

    public int getTotalRowCount(CrewVO vo) {
        return mapper.totalRowCount(vo);
    }

    public int getSearchListTotalRowCount(CrewVO vo,String searchKey, String searchWord) {
        return mapper.searchListTotalRowCount(vo,searchKey,searchWord);
    }

    public List<CrewVO> selectOneDayCrew(CrewVO vo){
        return mapper.selectOneDayCrew(vo);
    }
}
