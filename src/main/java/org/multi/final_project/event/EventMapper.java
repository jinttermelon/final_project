package org.multi.final_project.event;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {
    public int insertOK(EventVO vo);

    public int deleteOK(EventVO vo);

    public int updateOK(EventVO vo);

    public EventVO selectOne(EventVO vo);

    public List<EventVO> selectAll(int startRow, int limit);
}
