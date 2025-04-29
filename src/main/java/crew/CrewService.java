package crew;

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
    public List<CrewVO> selectAll(int cpage, int limit, CrewVO vo){
        return mapper.selectAll(cpage, limit, vo);
    }
    public CrewVO selectOne(CrewVO vo){
        return mapper.selectOne(vo);
    }
    public List<CrewVO> searchList(String searchKey, String searchWord, int cpage, int limit){
        return mapper.searchList(searchKey, searchWord, cpage, limit);
    }
    public CrewVO nameCheck(CrewVO vo){
        return mapper.nameCheck(vo);
    }
}
