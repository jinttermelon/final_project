package org.multi.final_project.crewjoin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.multi.final_project.crew.CrewVO;
import org.multi.final_project.crewboard.CrewBoardVO;

import java.util.List;
@Mapper
public interface CrewJoinMapper {

        public int joinOK(CrewJoinVO vo);

        public int updateOK(CrewJoinVO vo);

        public int deleteOK(CrewJoinVO vo);

        public List<CrewJoinVO> selectAll(@Param("nickname") String nickname,int cpage, int limit, CrewJoinVO vo);

        public CrewJoinVO selectOne(CrewJoinVO vo);

        public int totalRowCount(CrewJoinVO vo);
}

