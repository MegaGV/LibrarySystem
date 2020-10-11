package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzz.library.dao.BookMapper;
import xzz.library.dao.BorrowRecordMapper;
import xzz.library.dao.FineRecordMapper;
import xzz.library.dao.ReturnRecordMapper;
import xzz.library.dto.BorrowRecordDto;
import xzz.library.dto.RecordsDto;
import xzz.library.dto.ReturnRecordDto;
import xzz.library.pojo.BorrowRecord;
import xzz.library.pojo.ReturnRecord;
import xzz.library.service.RecordService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BorrowRecordMapper borrowRecordMapper;
    @Autowired
    private ReturnRecordMapper returnRecordMapper;
    @Autowired
    private FineRecordMapper fineRecordMapper;

    @Override
    public RecordsDto getRecords(String id, String field) {
        RecordsDto recordsDto = new RecordsDto();
        List records = new ArrayList();
        switch (field){
            case "borrow" :
                {
                    List<BorrowRecord> originRecords = borrowRecordMapper.getRecordsByUserid(id);
                    for (BorrowRecord borrowRecord : originRecords) {
                        BorrowRecordDto borrowRecordDto = new BorrowRecordDto(borrowRecord);
                        borrowRecordDto.setBook(bookMapper.selectByPrimaryKey(borrowRecord.getBookId()));
                        borrowRecordDto.setStatus(borrowRecordMapper.getBorrowRecordStatus(borrowRecord.getStatus()));
                        records.add(borrowRecordDto);
                    }
                }
                break;
            case "return":
                {
                    List<ReturnRecord> originRecords = returnRecordMapper.getRecordsByUserid(id);
                    for (ReturnRecord returnRecord : originRecords){
                        ReturnRecordDto returnRecordDto = new ReturnRecordDto(returnRecord);
                        returnRecordDto.setStatus(returnRecordMapper.getReturnRecordStatus(returnRecord.getStatus()));
                        records.add(recordsDto);
                    }
                }
                break;
            case "fine":
                records = fineRecordMapper.getRecordsByUserid(id);
                break;
            default:
        }
        recordsDto.setData(records);
        recordsDto.setTotal(records.size());
        return recordsDto;
    }
}
