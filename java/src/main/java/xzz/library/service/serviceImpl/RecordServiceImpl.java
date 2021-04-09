package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzz.library.dao.BookMapper;
import xzz.library.dao.BorrowRecordMapper;
import xzz.library.dao.FineRecordMapper;
import xzz.library.dao.ReturnRecordMapper;
import xzz.library.dto.info.BorrowRecordInfoDto;
import xzz.library.dto.list.RecordListDto;
import xzz.library.dto.info.ReturnRecordInfoDto;
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
    public RecordListDto getRecords(String userId, String recordType) {
        List records = new ArrayList();
        switch (recordType){
            case "borrow" :
                {
                    List<BorrowRecord> originRecords = borrowRecordMapper.getRecordsByUserid(userId);
                    for (BorrowRecord borrowRecord : originRecords) {
                        BorrowRecordInfoDto borrowRecordInfoDto = new BorrowRecordInfoDto(borrowRecord);
                        borrowRecordInfoDto.setBook(bookMapper.selectByPrimaryKey(borrowRecord.getBookId()));
                        borrowRecordInfoDto.setStatus(borrowRecordMapper.getBorrowRecordStatus(borrowRecord.getStatus()));
                        records.add(borrowRecordInfoDto);
                    }
                }
                break;
            case "return":
                {
                    List<ReturnRecord> originRecords = returnRecordMapper.getRecordsByUserid(userId);
                    for (ReturnRecord returnRecord : originRecords){
                        ReturnRecordInfoDto returnRecordInfoDto = new ReturnRecordInfoDto(returnRecord);
                        returnRecordInfoDto.setStatus(returnRecordMapper.getReturnRecordStatus(returnRecord.getStatus()));
                        records.add(returnRecordInfoDto);
                    }
                }
                break;
            case "fine":
                records = fineRecordMapper.getRecordsByUserid(userId);
                break;
            default:
        }
        return new RecordListDto(records);
    }

    @Override
    public BorrowRecordInfoDto getBR(String userId, String recordId) {
        BorrowRecord borrowRecord = borrowRecordMapper.selectByPrimaryKey(recordId);
        if (borrowRecord == null || !borrowRecord.getUserId().equals(userId))
            return null;
        BorrowRecordInfoDto borrowRecordInfoDto = new BorrowRecordInfoDto(borrowRecord);
        borrowRecordInfoDto.setBook(bookMapper.selectByPrimaryKey(borrowRecord.getBookId()));
        borrowRecordInfoDto.setStatus(borrowRecordMapper.getBorrowRecordStatus(borrowRecord.getStatus()));
        return borrowRecordInfoDto;
    }

    @Override
    public ReturnRecordInfoDto getRR(String userId, String recordId) {
        ReturnRecord returnRecord = returnRecordMapper.selectByPrimaryKey(recordId);
        if (returnRecord == null || !returnRecord.getUserId().equals(userId))
            return null;
        ReturnRecordInfoDto returnRecordInfoDto = new ReturnRecordInfoDto(returnRecord);
        returnRecordInfoDto.setStatus(returnRecordMapper.getReturnRecordStatus(returnRecord.getStatus()));
        return returnRecordInfoDto;
    }

    @Override
    public FineRecord getFR(String userId, String recordId) {
        FineRecord fineRecord = fineRecordMapper.selectByPrimaryKey(recordId);
        if (fineRecord == null || !fineRecord.getUserId().equals(userId))
            return null;
        return fineRecord;
    }
}
