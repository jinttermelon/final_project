package org.multi.final_project.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<FriendVO> selectAll(String fnickname, int cpage, int limit) {
        int startRow = (cpage - 1) * limit;

        Map<String, Object> param = new HashMap<>();
        param.put("fnickname", fnickname);
        param.put("startRow", startRow);
        param.put("limit", limit);

        List<FriendVO> list = mapper.selectAll(param);

        System.out.println(">>> 불러온 친구 수: " + list.size());
        for (FriendVO f : list) {
            System.out.println("내 닉네임: " + f.getNickname() + ", 친구 닉네임: " + f.getFnickname());
        }

        return list;
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
    public List<FriendVO> selectAcceptedFriends(String userId){return mapper.selectAcceptedFriends(userId);}
}
