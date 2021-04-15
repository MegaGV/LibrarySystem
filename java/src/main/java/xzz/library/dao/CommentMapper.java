package xzz.library.dao;

import xzz.library.dto.list.CommentListDto;
import xzz.library.pojo.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    Comment selectByPrimaryKey(String id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    CommentListDto getCommentsByDiscussId(String discussId);

}
