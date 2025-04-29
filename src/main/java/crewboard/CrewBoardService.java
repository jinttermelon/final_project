package crewboard;

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
    public List<CrewBoardVO> selectAll(int cpage, int limit, CrewBoardVO vo){
        return mapper.selectAll(cpage, limit, vo);
    }
    public CrewBoardVO selecOne(CrewBoardVO vo){
        return mapper.selectOne(vo);
    }
    public List<CrewBoardVO> selectAll(String searchKey, String searchWord, int cpage, int limit){
        return mapper.searchList(searchKey, searchWord, cpage, limit);
    }


}
