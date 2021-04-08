package xzz.library.dao;

import java.util.List;
import xzz.library.dto.search.RecordSearchDto;
import xzz.library.pojo.FineRecord;

public interface FineRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(FineRecord record);

    FineRecord selectByPrimaryKey(String id);

    List<FineRecord> selectAll();

    int updateByPrimaryKey(FineRecord record);

    List<FineRecord> getRecordsByUserid(String userId);

    List<FineRecord> getRecords(RecordSearchDto recordSearchDto);

    int countRecords(RecordSearchDto recordSearchDto);
}