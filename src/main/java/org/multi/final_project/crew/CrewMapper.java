package org.multi.final_project.crew;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.multi.final_project.cos.CosVO;

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

        public List<CrewVO> selectOneDayCrew(CrewVO vo);

        CrewVO selectCrewByCnum(int cnum);

        public List<CrewVO> searchListAdmin(@Param("startRow") int startRow,
                                     @Param("limit") int limit,
                                     @Param("searchKey") String searchKey,
                                     @Param("searchWord") String searchWord);

        public int searchCountAdmin(@Param("searchKey") String searchKey, @Param("searchWord") String searchWord);

        public int searchCount(@Param("searchKey") String searchKey, @Param("searchWord") String searchWord);

        public int getLastCnum();

        int updateGrade(CrewVO vo);
}
