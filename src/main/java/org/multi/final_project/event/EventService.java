package org.multi.final_project.event;

import org.multi.final_project.crew.CrewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventMapper mapper;

    public int insertOK(EventVO vo){
        return mapper.insertOK(vo);
    }
    public int updateOK(EventVO vo){
        return mapper.updateOK(vo);
    }
    public int deleteOK(EventVO vo){
        return mapper.deleteOK(vo);
    }
    public EventVO selectOne(EventVO vo){
        return mapper.selectOne(vo);
    }
    public List<EventVO> selectAll(int startRow, int limit){
        return mapper.selectAll(startRow, limit);
    }
    public int getTotalRowCount(EventVO vo) {
        return mapper.totalRowCount(vo);
    }

}
