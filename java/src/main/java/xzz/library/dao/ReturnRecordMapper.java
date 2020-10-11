package xzz.library.dao;

import java.util.List;
import xzz.library.pojo.ReturnRecord;

public interface ReturnRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReturnRecord record);

    ReturnRecord selectByPrimaryKey(String id);

    List<ReturnRecord> selectAll();

    int updateByPrimaryKey(ReturnRecord record);

    List<ReturnRecord> getRecordsByUserid(String id);

    String getReturnRecordStatus(Integer id);
}