package org.multi.final_project.crewjoin;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CrewJoinMapper {

        public int joinOK(CrewJoinVO vo);

        public int updateOK(CrewJoinVO vo);

        public int deleteOK(CrewJoinVO vo);

        public List<CrewJoinVO> selectAll(int cpage, int limit, CrewJoinVO vo);

        public CrewJoinVO selectOne(CrewJoinVO vo);
}
