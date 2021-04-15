package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzz.library.dao.CommentMapper;
import xzz.library.dao.DiscussMapper;
import xzz.library.dto.list.CommentListDto;
import xzz.library.dto.list.DiscussListDto;
import xzz.library.dto.search.DiscussSearchDto;
import xzz.library.pojo.Comment;
import xzz.library.pojo.Discuss;
import xzz.library.service.DiscussService;

import java.util.UUID;

@Service
public class DiscussServiceImpl implements DiscussService {
    @Autowired
    private DiscussMapper discussMapper;
    @Autowired
    private CommentMapper commentMapper;


    @Override
    public DiscussListDto getDiscusses(DiscussSearchDto discussSearchDto) {
        return new DiscussListDto(discussMapper.getDiscusses(discussSearchDto), discussMapper.countDiscusses(discussSearchDto));
    }

    @Override
    public Discuss getDiscuss(String discussId) {
        return discussMapper.selectByPrimaryKey(discussId);
    }

    @Override
    @Transactional
    public String publishDiscuss(Discuss discuss) {
        if (discuss == null)
            return "信息有误";
        discuss.initial();
        while (discussMapper.selectByPrimaryKey(discuss.getId()) != null)
            discuss.setId(UUID.randomUUID().toString());

        try{
            discussMapper.insert(discuss);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "发布帖子失败";
        }
    }

    @Override
    @Transactional
    public String deleteDiscuss(String userId, String discussId) {
        if (userId == null || discussId == null)
            return "信息有误";
        Discuss discuss = discussMapper.selectByPrimaryKey(discussId);
        if (!userId.equals(discuss.getUserId()))
            return "权限不足";

        try{
            discussMapper.deleteByPrimaryKey(discussId);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "删除帖子失败";
        }
    }

    @Override
    public CommentListDto getComments(String discussId) {
        return commentMapper.getCommentsByDiscussId(discussId);
    }

    @Override
    @Transactional
    public String publishComment(Comment comment) {
        if (comment == null)
            return "信息有误";
        comment.initial();
        while (commentMapper.selectByPrimaryKey(comment.getId()) != null)
            comment.setId(UUID.randomUUID().toString());

        try{
            commentMapper.insert(comment);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "发布评论失败";
        }
    }

    @Override
    @Transactional
    public String deleteComment(String userId, String commentId) {
        if (userId == null || commentId == null)
            return "信息有误";
        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        if (!userId.equals(comment.getUserId())){ // 贴主和评论主均可删除
            if (!userId.equals(discussMapper.selectByPrimaryKey(comment.getDiscussId()).getUserId()))
                return "权限不足";
        }

        try{
            commentMapper.deleteByPrimaryKey(commentId);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "删除评论失败";
        }
    }
}
