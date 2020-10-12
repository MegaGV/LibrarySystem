package xzz.library.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import xzz.library.pojo.BorrowRecord;

public interface BorrowRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(BorrowRecord record);

    BorrowRecord selectByPrimaryKey(String id);

    List<BorrowRecord> selectAll();

    int updateByPrimaryKey(BorrowRecord record);

    List<BorrowRecord> getRecordsByUserid(String id);

    String getBorrowRecordStatus(Integer id);

    List getRecords(@Param("limit") Integer limit, @Param("start") Integer start);

    Integer countRecords();
}