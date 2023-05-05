package com.redtv.redtvwebserve.cotroller;

import com.redtv.redtvwebserve.dto.DateDto;
import com.redtv.redtvwebserve.service.DataService;
import com.redtv.redtvwebserve.utils.DateUtils;
import com.redtv.redtvwebserve.vo.ResponseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DataController {

    @Autowired
    private DataService dataService;


    // 统计网站UV
    @RequestMapping(path = "/data/uv", method = RequestMethod.POST)
    public ResponseDetails getUV(@RequestBody DateDto dateDto) {
        System.out.println(dateDto);
        Date start = DateUtils.stringToDate(dateDto.getStart());
        Date end = DateUtils.stringToDate(dateDto.getEnd());
        System.out.println(start);
        System.out.println(end);
        long uv = dataService.calculateUV(start, end);

        return ResponseDetails.ok().put("uv",0);
    }

    // 统计活跃用户
    @RequestMapping(path = "/data/dau", method = RequestMethod.POST)
    public ResponseDetails getDAU(@RequestBody DateDto dateDto) {

        System.out.println(dateDto);
        Date start = DateUtils.stringToDate(dateDto.getStart());
        Date end = DateUtils.stringToDate(dateDto.getEnd());
        System.out.println(start);
        System.out.println(end);
        long dau = dataService.calculateDAU(start, end);

        return ResponseDetails.ok().put("dau",dau);

}


}
