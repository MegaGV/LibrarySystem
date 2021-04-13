package xzz.library.dao;

import xzz.library.pojo.Discuss;

import java.util.List;

public interface DiscussMapper {
    int deleteByPrimaryKey(String id);

    int insert(Discuss record);

    Discuss selectByPrimaryKey(String id);

    List<Discuss> selectAll();

    int updateByPrimaryKey(DiscussMapper record);
}
