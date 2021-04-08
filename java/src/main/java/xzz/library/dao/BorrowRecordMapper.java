package xzz.library.dao;

import java.util.List;
import xzz.library.dto.search.RecordSearchDto;
import xzz.library.pojo.BorrowRecord;

public interface BorrowRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(BorrowRecord record);

    BorrowRecord selectByPrimaryKey(String id);

    List<BorrowRecord> selectAll();

    int updateByPrimaryKey(BorrowRecord record);

    List<BorrowRecord> getRecordsByUserid(String userId);

    String getBorrowRecordStatus(Integer statusId);

    List<BorrowRecord> getRecords(RecordSearchDto recordSearchDto);

    int countRecords(RecordSearchDto recordSearchDto);

    Integer getStatusId(String statusStr);
}