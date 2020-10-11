package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xzz.library.dto.RecordsDto;
import xzz.library.service.RecordService;

@Controller
@RequestMapping("/record")
@CrossOrigin(allowCredentials = "true")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/getRecords")
    @ResponseBody
    public RecordsDto getRecords(String id, String field){
        return recordService.getRecords(id, field);
    }
}
