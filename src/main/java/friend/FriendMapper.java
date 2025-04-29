package friend;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendMapper {

    public int insertOK(FriendVO vo);

    public int updateOK(FriendVO vo);

    public int deleteOK(FriendVO vo);

    public List<FriendVO> selectAll(int startRow, int limit);

    public List<FriendVO> selectSend(FriendVO vo);

    public List<FriendVO> selectReceive(FriendVO vo);

    public String acceptedFriend(FriendVO vo);

    public String rejectedFriend(FriendVO vo);
}
