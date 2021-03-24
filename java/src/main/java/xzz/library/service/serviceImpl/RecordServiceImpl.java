package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzz.library.dao.BookMapper;
import xzz.library.dao.BorrowRecordMapper;
import xzz.library.dao.FineRecordMapper;
import xzz.library.dao.ReturnRecordMapper;
import xzz.library.dto.BorrowRecordListDto;
import xzz.library.dto.RecordsListDto;
import xzz.library.dto.ReturnRecordListDto;
import xzz.library.pojo.BorrowRecord;
import xzz.library.pojo.FineRecord;
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
    public RecordsListDto getRecords(String id, String recordType) {
        List records = new ArrayList();
        switch (recordType){
            case "borrow" :
                {
                    List<BorrowRecord> originRecords = borrowRecordMapper.getRecordsByUserid(id);
                    for (BorrowRecord borrowRecord : originRecords) {
                        BorrowRecordListDto borrowRecordListDto = new BorrowRecordListDto(borrowRecord);
                        borrowRecordListDto.setBook(bookMapper.selectByPrimaryKey(borrowRecord.getBookId()));
                        borrowRecordListDto.setStatus(borrowRecordMapper.getBorrowRecordStatus(borrowRecord.getStatus()));
                        records.add(borrowRecordListDto);
                    }
                }
                break;
            case "return":
                {
                    List<ReturnRecord> originRecords = returnRecordMapper.getRecordsByUserid(id);
                    for (ReturnRecord returnRecord : originRecords){
                        ReturnRecordListDto returnRecordListDto = new ReturnRecordListDto(returnRecord);
                        returnRecordListDto.setStatus(returnRecordMapper.getReturnRecordStatus(returnRecord.getStatus()));
                        records.add(returnRecordListDto);
                    }
                }
                break;
            case "fine":
                records = fineRecordMapper.getRecordsByUserid(id);
                break;
            default:
        }
        return new RecordsListDto(records, records.size());
    }

    @Override
    public BorrowRecordListDto getBR(String userId, String recordId) {
        BorrowRecord borrowRecord = borrowRecordMapper.selectByPrimaryKey(recordId);
        if (borrowRecord == null || !borrowRecord.getUserId().equals(userId))
            return null;
        BorrowRecordListDto borrowRecordListDto = new BorrowRecordListDto(borrowRecord);
        borrowRecordListDto.setBook(bookMapper.selectByPrimaryKey(borrowRecord.getBookId()));
        borrowRecordListDto.setStatus(borrowRecordMapper.getBorrowRecordStatus(borrowRecord.getStatus()));
        return borrowRecordListDto;
    }

    @Override
    public ReturnRecordListDto getRR(String userId, String recordId) {
        ReturnRecord returnRecord = returnRecordMapper.selectByPrimaryKey(recordId);
        if (returnRecord == null || !returnRecord.getUserId().equals(userId))
            return null;
        ReturnRecordListDto returnRecordListDto = new ReturnRecordListDto(returnRecord);
        returnRecordListDto.setStatus(returnRecordMapper.getReturnRecordStatus(returnRecord.getStatus()));
        return returnRecordListDto;
    }

    @Override
    public FineRecord getFR(String userId, String recordId) {
        FineRecord fineRecord = fineRecordMapper.selectByPrimaryKey(recordId);
        if (fineRecord == null || !fineRecord.getUserId().equals(userId))
            return null;
        return fineRecord;
    }
}
