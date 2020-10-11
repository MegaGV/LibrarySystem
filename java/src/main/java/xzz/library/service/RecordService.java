package xzz.library.service;

import xzz.library.dto.RecordsDto;

public interface RecordService {
    RecordsDto getRecords(String id, String field);
}
