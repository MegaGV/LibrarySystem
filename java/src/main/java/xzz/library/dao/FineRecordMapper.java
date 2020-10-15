package xzz.library.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xzz.library.pojo.FineRecord;

public interface FineRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(FineRecord record);

    FineRecord selectByPrimaryKey(String id);

    List<FineRecord> selectAll();

    int updateByPrimaryKey(FineRecord record);

    List<FineRecord> getRecordsByUserid(String id);

    List getRecords(@Param("limit") Integer limit, @Param("start") Integer start);

    int countRecords();
}