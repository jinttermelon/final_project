package org.multi.final_project.user;

import org.multi.final_project.crew.CrewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper mapper;

    public int insertOK(UserVO vo){
        return mapper.insertOK(vo);
    }
    public int updateOK(UserVO vo){
        return mapper.updateOK(vo);
    }
    public int updateLevel(UserVO vo){
        return mapper.updateLevel(vo);
    }
    public int getHistoryCount(UserVO vo){
        return mapper.getHistoryCount(vo);
    }
    public int deleteOK(UserVO vo){
        return mapper.deleteOK(vo);
    }
    public int login(UserVO vo){
        return mapper.login(vo);
    }
    public UserVO idCheck(UserVO vo){
        return mapper.idCheck(vo);
    }
    public UserVO selectOne(UserVO vo){
        return mapper.selectOne(vo);
    }
    public List<UserVO> selectAll(int startRow, int limit){
        return mapper.selectAll(startRow, limit);
    }
    public UserVO banUserOK(UserVO vo){
        return mapper.banUserOK(vo);
    }
    public UserVO nicknameCheck(UserVO vo){
        return mapper.nicknameCheck(vo);
    }
    public int getTotalRowCount(UserVO vo) {
        return mapper.totalRowCount(vo);
    }

    public int getSearchListTotalRowCount(UserVO vo,String searchKey, String searchWord) {
        return mapper.searchListTotalRowCount(vo,searchKey,searchWord);
    }

    public UserVO pwCheck(UserVO vo) {
        return mapper.pwCheck(vo);
    }

    public UserVO findById(String username){
        return mapper.findById(username);
    }

    public UserVO getNickname(String id){
        return mapper.getNickname(id);
    }
}
