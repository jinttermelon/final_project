package org.multi.final_project.crewjoin;
import org.multi.final_project.crew.CrewVO;
import org.multi.final_project.crewboard.CrewBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CrewJoinService {
        @Autowired
        private CrewJoinMapper mapper;

        public int joinOK(CrewJoinVO vo){
            return mapper.joinOK(vo);
        }
        public int updateOK(CrewJoinVO vo){
            return mapper.updateOK(vo);
        }
        public int deleteOK(CrewJoinVO vo){
            return mapper.deleteOK(vo);
        }
        public List<CrewJoinVO> selectAll(int cpage, int limit, CrewJoinVO vo){
            return mapper.selectAll(vo.getNickname(), cpage, limit, vo);
        }
        public CrewJoinVO selectOne(CrewJoinVO vo){
            return mapper.selectOne(vo);
        }
    public int getTotalRowCount(CrewJoinVO vo) {
        return mapper.totalRowCount(vo);
    }
}
