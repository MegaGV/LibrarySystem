package xzz.library.service;

import xzz.library.dto.list.CommentListDto;
import xzz.library.dto.list.DiscussListDto;
import xzz.library.dto.search.CommentSearchDto;
import xzz.library.dto.search.DiscussSearchDto;
import xzz.library.pojo.Comment;
import xzz.library.pojo.Discuss;

public interface DiscussService {
    DiscussListDto getDiscusses(DiscussSearchDto discussSearchDto);

    Discuss getDiscuss(String discussId);

    String publishDiscuss(Discuss discuss);

    String deleteDiscuss(String userId, String discussId);

    CommentListDto getComments(CommentSearchDto commentSearchDto);

    String publishComment(Comment comment);

    String deleteComment(String userId, String commentId);
}
