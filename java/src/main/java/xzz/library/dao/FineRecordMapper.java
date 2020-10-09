package xzz.library.dao;

import java.util.List;
import xzz.library.pojo.FineRecord;

public interface FineRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(FineRecord record);

    FineRecord selectByPrimaryKey(String id);

    List<FineRecord> selectAll();

    int updateByPrimaryKey(FineRecord record);
}