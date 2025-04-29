package friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    @Autowired
    private FriendMapper mapper;

    public int insertOK(FriendVO vo){
        return mapper.insertOK(vo);
    }
    public int deleteOK(FriendVO vo){
        return mapper.deleteOK(vo);
    }
    public List<FriendVO> selectAll(int cpage, int limit){
        return mapper.selectAll(cpage, limit);
    }
    public List<FriendVO> selectSend(FriendVO vo){
        return mapper.selectSend(vo);
    }
    public List<FriendVO> selectReceive(FriendVO vo){
        return mapper.selectReceive(vo);
    }
    public String acceptedFriend(FriendVO vo){
        return mapper.acceptedFriend(vo);
    }
    public String rejectedFriend(FriendVO vo){
        return mapper.rejectedFriend(vo);
    }
}
