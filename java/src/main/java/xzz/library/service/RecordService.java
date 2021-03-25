package xzz.library.service;

import xzz.library.dto.BorrowRecordListDto;
import xzz.library.dto.RecordsListDto;
import xzz.library.dto.ReturnRecordListDto;
import xzz.library.pojo.FineRecord;

public interface RecordService {
    RecordsListDto getRecords(String userId, String recordType);

    BorrowRecordListDto getBR(String userId, String recordId);

    ReturnRecordListDto getRR(String userId, String recordId);

    FineRecord getFR(String userId, String recordId);
}
