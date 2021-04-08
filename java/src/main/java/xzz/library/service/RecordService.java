package xzz.library.service;

import xzz.library.dto.list.BorrowRecordListDto;
import xzz.library.dto.list.RecordListDto;
import xzz.library.dto.list.ReturnRecordListDto;
import xzz.library.pojo.FineRecord;

public interface RecordService {
    RecordListDto getRecords(String userId, String recordType);

    BorrowRecordListDto getBR(String userId, String recordId);

    ReturnRecordListDto getRR(String userId, String recordId);

    FineRecord getFR(String userId, String recordId);
}
