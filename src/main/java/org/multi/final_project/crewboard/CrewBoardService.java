package org.multi.final_project.crewboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrewBoardService {
    @Autowired
    private CrewBoardMapper mapper;

    public int insertOK(CrewBoardVO vo){
        return  mapper.insertOK(vo);
    }
    public int updateOK(CrewBoardVO vo){
        return  mapper.updateOK(vo);
    }
    public int deleteOK(CrewBoardVO vo){
        return  mapper.deleteOK(vo);
    }
    public List<CrewBoardVO> selectAll(int startRow, int limit,int cnum){
        return mapper.selectAll(startRow, limit , cnum);
    }
    public CrewBoardVO selectOne(CrewBoardVO vo){
        return mapper.selectOne(vo);
    }
    public List<CrewBoardVO> searchList(String searchKey, String searchWord, int startRow, int limit){
        return mapper.searchList(searchKey, searchWord, startRow, limit);
    }


}
