package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzz.library.dao.CommentMapper;
import xzz.library.dao.DiscussMapper;
import xzz.library.service.DiscussService;

@Service
public class DiscussServiceImpl implements DiscussService {
    @Autowired
    private DiscussMapper discussMapper;
    @Autowired
    private CommentMapper commentMapper;


}
