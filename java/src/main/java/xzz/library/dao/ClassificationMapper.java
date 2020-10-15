package xzz.library.dao;

import java.util.List;
import xzz.library.pojo.Classification;

public interface ClassificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Classification record);

    Classification selectByPrimaryKey(Integer id);

    List<Classification> selectAll();

    int updateByPrimaryKey(Classification record);

    List<Classification> getFirstClassification();

    List<Classification> getSecondClassification(String classifyChar);
}