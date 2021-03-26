package xzz.library.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xzz.library.pojo.ReturnRecord;

public interface ReturnRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReturnRecord record);

    ReturnRecord selectByPrimaryKey(String id);

    List<ReturnRecord> selectAll();

    int updateByPrimaryKey(ReturnRecord record);

    List<ReturnRecord> getRecordsByUserid(String userId);

    String getReturnRecordStatus(Integer statusId);

    List getRecords(@Param("limit") Integer limit, @Param("start") Integer start);

    int countRecords();
}