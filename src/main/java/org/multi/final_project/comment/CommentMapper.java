package org.multi.final_project.comment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    public int insertOK(CommentVO vo);
    public int updateOK(CommentVO vo);
    public int deleteOK(CommentVO vo);
    public List<CommentVO> selectAll(int startRow,int limit);

    public List<CommentVO> selectByUser(String nickname, int startRow, int limit);

    public int getTotalByUser(String nickname);

    public List<CommentVO> selectByBnum(int bnum);
}
