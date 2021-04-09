package xzz.library.service;

import xzz.library.dto.info.BorrowRecordInfoDto;
import xzz.library.dto.list.RecordListDto;
import xzz.library.dto.info.ReturnRecordInfoDto;
import xzz.library.pojo.FineRecord;

public interface RecordService {
    RecordListDto getRecords(String userId, String recordType);

    BorrowRecordInfoDto getBR(String userId, String recordId);

    ReturnRecordInfoDto getRR(String userId, String recordId);

    FineRecord getFR(String userId, String recordId);
}
