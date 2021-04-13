package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.service.DiscussService;

@Controller
@RequestMapping("/discuss")
@CrossOrigin(allowCredentials = "true")
public class DiscussController {
    //========================================================================================
    // Discusses
    // Operations: 获取帖子列表、获取帖子、发布帖子、删除帖子、发布评论(回帖)、删除评论
    //========================================================================================
    @Autowired
    private DiscussService discussService;


}
