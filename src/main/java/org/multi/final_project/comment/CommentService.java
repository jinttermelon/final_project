package org.multi.final_project.comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper mapper;

    public int insertOK(CommentVO vo){
        return mapper.insertOK(vo);
    }
    public int updateOK(CommentVO vo){
        return mapper.updateOK(vo);
    }
    public int deleteOK(CommentVO vo){
        return mapper.deleteOK(vo);
    }
    public List<CommentVO> selectAll(int startRow, int limit){
        return mapper.selectAll(startRow,limit);
    }

    public List<CommentVO> selectByUser(String nickname, int startRow, int limit) {
        return mapper.selectByUser(nickname , startRow, limit);
    }

    public int getTotalByUser(String nickname) {
        return mapper.getTotalByUser(nickname);
    }
}
