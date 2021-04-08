package xzz.library.dao;

import java.util.List;
import xzz.library.dto.search.RecordSearchDto;
import xzz.library.pojo.ReturnRecord;

public interface ReturnRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReturnRecord record);

    ReturnRecord selectByPrimaryKey(String id);

    List<ReturnRecord> selectAll();

    int updateByPrimaryKey(ReturnRecord record);

    List<ReturnRecord> getRecordsByUserid(String userId);

    String getReturnRecordStatus(Integer statusId);

    List<ReturnRecord> getRecords(RecordSearchDto recordSearchDto);

    int countRecords(RecordSearchDto recordSearchDto);

    Integer getStatusId(String statusStr);
}