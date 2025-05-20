package org.multi.final_project.friend;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FriendMapper {

    public int insertOK(FriendVO vo);

    public int updateOK(FriendVO vo);

    public int deleteOK(FriendVO vo);

    public List<FriendVO> selectSend(FriendVO vo);

    public List<FriendVO> selectReceive(FriendVO vo);

    public String acceptedFriend(FriendVO vo);

    public String rejectedFriend(FriendVO vo);

    public List<FriendVO> selectAcceptedFriends(String user_id);

    public List<FriendVO> getAllFriends();

    public List<FriendVO> selectAll(Map<String, Object> param);

}
