package xzz.library.dao;

import xzz.library.dto.search.DiscussSearchDto;
import xzz.library.pojo.Discuss;

import java.util.List;

public interface DiscussMapper {
    int deleteByPrimaryKey(String id);

    int insert(Discuss record);

    Discuss selectByPrimaryKey(String id);

    List<Discuss> selectAll();

    int updateByPrimaryKey(Discuss record);

    List<Discuss> getDiscusses(DiscussSearchDto discussSearchDto);

    int countDiscusses(DiscussSearchDto discussSearchDto);
}
