package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xzz.library.dto.BorrowRecordDto;
import xzz.library.dto.RecordsDto;
import xzz.library.dto.ReturnRecordDto;
import xzz.library.pojo.FineRecord;
import xzz.library.service.RecordService;

@Controller
@RequestMapping("/record")
@CrossOrigin(allowCredentials = "true")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/getRecords")
    @ResponseBody
    public RecordsDto getRecords(String id, String recordType){
        return recordService.getRecords(id, recordType);
    }

    @GetMapping("/getBR")
    @ResponseBody
    public BorrowRecordDto getBR(String userId, String recordId){
        return recordService.getBR(userId, recordId);
    }

    @GetMapping("/getRR")
    @ResponseBody
    public ReturnRecordDto getRR(String userId, String recordId){
        return recordService.getRR(userId, recordId);
    }

    @GetMapping("/getFR")
    @ResponseBody
    public FineRecord getFR(String userId, String recordId){
        return recordService.getFR(userId, recordId);
    }
}
