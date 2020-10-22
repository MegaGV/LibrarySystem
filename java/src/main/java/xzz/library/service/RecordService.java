package xzz.library.service;

import xzz.library.dto.BorrowRecordDto;
import xzz.library.dto.RecordsDto;
import xzz.library.dto.ReturnRecordDto;
import xzz.library.pojo.FineRecord;

public interface RecordService {
    RecordsDto getRecords(String id, String recordType);

    BorrowRecordDto getBR(String userId, String recordId);

    ReturnRecordDto getRR(String userId, String recordId);

    FineRecord getFR(String userId, String recordId);
}
