package org.multi.final_project.event;

import org.apache.ibatis.annotations.Mapper;
import org.multi.final_project.crew.CrewVO;

import java.util.List;

@Mapper
public interface EventMapper {
    public int insertOK(EventVO vo);

    public int deleteOK(EventVO vo);

    public int updateOK(EventVO vo);

    public EventVO selectOne(EventVO vo);

    public List<EventVO> selectAll(int startRow, int limit);


    public int totalRowCount(EventVO vo);

}
