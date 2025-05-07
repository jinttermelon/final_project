package org.multi.final_project.user;

import org.apache.ibatis.annotations.Mapper;
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

    UserVO getNickname(String id);

}
