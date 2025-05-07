package org.multi.final_project.crew;



import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CrewMapper {
        public int createOK(CrewVO vo);

        public int updateOK(CrewVO vo);

        public int deleteOK(CrewVO vo);

        public List<CrewVO> selectAll(int startRow, int limit);

        public CrewVO selectOne(CrewVO vo);

        public List<CrewVO> searchList(int startRow, int limit, String searchKey, String searchWord);

        public CrewVO nameCheck(CrewVO vo);

        public int totalRowCount(CrewVO vo);

        public int searchListTotalRowCount(CrewVO vo,String searchKey, String searchWord);

}
