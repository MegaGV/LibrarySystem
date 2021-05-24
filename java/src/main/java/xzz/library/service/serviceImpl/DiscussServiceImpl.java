package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzz.library.dao.CommentMapper;
import xzz.library.dao.DiscussMapper;
import xzz.library.dao.MessageMapper;
import xzz.library.dto.list.BookListDto;
import xzz.library.dto.list.CommentListDto;
import xzz.library.dto.list.DiscussListDto;
import xzz.library.dto.search.CommentSearchDto;
import xzz.library.dto.search.DiscussSearchDto;
import xzz.library.pojo.Comment;
import xzz.library.pojo.Discuss;
import xzz.library.pojo.Message;
import xzz.library.service.DiscussService;
import xzz.library.util.MessageUtils;

import java.util.List;
import java.util.UUID;

@Service
public class DiscussServiceImpl implements DiscussService {
    @Autowired
    private DiscussMapper discussMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private MessageMapper messageMapper;


    @Override
    public DiscussListDto getDiscusses(DiscussSearchDto discussSearchDto) {
        discussSearchDto.initial();
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
    public CommentListDto getComments(CommentSearchDto commentSearchDto) {
        commentSearchDto.initial();
        return new CommentListDto(commentMapper.getComments(commentSearchDto), commentMapper.countComments(commentSearchDto));
    }

    @Override
    @Transactional
    public String publishComment(Comment comment) {
        if (comment == null)
            return "信息有误";
        comment.initial();
        while (commentMapper.selectByPrimaryKey(comment.getId()) != null)
            comment.setId(UUID.randomUUID().toString());

        Discuss discuss = discussMapper.selectByPrimaryKey(comment.getDiscussId());
        Message message = MessageUtils.NewComment(discuss.getUserId(), discuss.getTitle());

        try{
            commentMapper.insert(comment);
            messageMapper.insert(message);
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
