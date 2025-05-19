package org.multi.final_project.event;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.multi.final_project.crew.CrewVO;

import java.util.List;

@Mapper
public interface EventMapper {
    public int insertOK(EventVO vo);

    public int deleteOK(EventVO vo);

    public int updateOK(EventVO vo);

    public EventVO selectOne(EventVO vo);

    public List<EventVO> selectAll(int startRow, int limit);

    public List<EventVO> myEvent(int startRow, int limit , @Param("nickname") String nickname);


    public int totalRowCount(EventVO vo);

    public int applyDone(Event_EntryVO vo);

    public int countApplied(Event_EntryVO entryVO);
}
