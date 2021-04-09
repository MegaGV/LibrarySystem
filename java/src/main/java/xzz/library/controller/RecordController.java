package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.dto.info.BorrowRecordInfoDto;
import xzz.library.dto.list.RecordListDto;
import xzz.library.dto.info.ReturnRecordInfoDto;
import xzz.library.pojo.FineRecord;
import xzz.library.service.RecordService;

@Controller
@RequestMapping("/record")
@CrossOrigin(allowCredentials = "true")
public class RecordController {
    //========================================================================================
    // Record
    // Operations: 获取记录列表、获取单个记录(按种类单独一个函数)
    //========================================================================================
    @Autowired
    private RecordService recordService;

    @GetMapping("/getRecords")
    @ResponseBody
    public RecordListDto getRecords(String userId, String recordType){
        return recordService.getRecords(userId, recordType);
    }

    @GetMapping("/getBR")
    @ResponseBody
    public BorrowRecordInfoDto getBR(String userId, String recordId){
        return recordService.getBR(userId, recordId);
    }

    @GetMapping("/getRR")
    @ResponseBody
    public ReturnRecordInfoDto getRR(String userId, String recordId){
        return recordService.getRR(userId, recordId);
    }

    @GetMapping("/getFR")
    @ResponseBody
    public FineRecord getFR(String userId, String recordId){
        return recordService.getFR(userId, recordId);
    }
}
