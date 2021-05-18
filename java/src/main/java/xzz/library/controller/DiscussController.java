package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.dto.list.CommentListDto;
import xzz.library.dto.list.DiscussListDto;
import xzz.library.dto.search.DiscussSearchDto;
import xzz.library.pojo.Comment;
import xzz.library.pojo.Discuss;
import xzz.library.service.DiscussService;

@Controller
@RequestMapping("/discuss")
@CrossOrigin(allowCredentials = "true")
public class DiscussController {
    //========================================================================================
    // Discusses
    // Operations: 获取帖子列表、获取帖子、发布帖子、删除帖子、获取评论、发布评论(回帖)、删除评论
    //========================================================================================
    @Autowired
    private DiscussService discussService;

    @PostMapping("/getDiscusses")
    @ResponseBody
    public DiscussListDto getDiscusses(@RequestBody DiscussSearchDto discussSearchDto){
        return discussService.getDiscusses(discussSearchDto);
    }

    @GetMapping("/getDiscuss")
    @ResponseBody
    public Discuss getDiscuss(String discussId){
        return discussService.getDiscuss(discussId);
    }

    @PostMapping("/publishDiscuss")
    @ResponseBody
    public String publishDiscuss(@RequestBody Discuss discuss){
        return discussService.publishDiscuss(discuss);
    }

    @PostMapping("/deleteDiscuss")
    @ResponseBody
    public String deleteDiscuss(String userId, String discussId){
        return discussService.deleteDiscuss(userId, discussId);
    }

    @GetMapping("/getComments")
    @ResponseBody
    public CommentListDto getComments(String discussId){
        return discussService.getComments(discussId);
    }

    @PostMapping("/publishComment")
    @ResponseBody
    public String publishComment(@RequestBody Comment comment){
        return discussService.publishComment(comment);
    }

    @PostMapping("/deleteComment")
    @ResponseBody
    public String deleteComment(String userId, String commentId){
        return discussService.deleteComment(userId, commentId);
    }
}
