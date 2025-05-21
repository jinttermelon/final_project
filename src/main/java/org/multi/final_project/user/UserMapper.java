package org.multi.final_project.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.multi.final_project.crew.CrewVO;

import java.util.List;

@Mapper
public interface UserMapper {

    public int insertOK(UserVO vo);

    public int updateOK(UserVO vo);

    public int updateLevel(UserVO vo);

    public int getHistoryCount(UserVO vo);

    public int deleteOK(UserVO vo);

    public int login(UserVO vo);

    public UserVO idCheck(UserVO vo);

    public UserVO selectOne(UserVO vo);

    public List<UserVO> selectAll(int startRow, int limit);

    public UserVO banUserOK(UserVO vo);

    public UserVO nicknameCheck(UserVO vo);

    public UserVO getNickname(String id);


    public int totalRowCount(UserVO vo);

    public int searchListTotalRowCount(UserVO vo,String searchKey, String searchWord);

    public UserVO pwCheck(UserVO vo);


    public UserVO findById(String username);

    public String findIdOK(UserVO vo);

    public UserVO findPwOK(UserVO vo);

    public int updatePwOK(UserVO vo);

    public List<UserVO> searchByNickname(String searchWord,String nickname);

    List<UserVO> adminSelectAll(int startRow, int limit);

    int adminInsertOK(UserVO vo);
}
