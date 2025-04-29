package crewrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrewRecordService {
    @Autowired
    private CrewRecordMapper mapper;

    public int insertOK(CrewRecordVO vo){
        return mapper.insertOK(vo);
    }
    public int updateOK(CrewRecordVO vo){
        return mapper.updateOK(vo);
    }
    public int deleteOK(CrewRecordVO vo){
        return mapper.deleteOK(vo);
    }
    public List<CrewRecordVO> selectAll(int cpage, int limit, CrewRecordVO vo){
        return mapper.selectAll(cpage, limit, vo);
    }
    public CrewRecordVO selectOne(CrewRecordVO vo){
        return mapper.selectOne(vo);
    }
    public List<CrewRecordVO> searchList(String searchKey, String searchWord, int cpage, int limit,CrewRecordVO vo){
        return mapper.searchList(searchKey, searchWord, cpage, limit, vo);
    }

}
