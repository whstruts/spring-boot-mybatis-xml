package com.kfit.demo.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kfit.demo.bean.yywddhz;
import com.kfit.demo.bean.yywddmx;
import com.kfit.demo.service.SpzlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;


@Component
public class ScheduledTasks {
    @Autowired
    private SpzlService  spzlService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(fixedDelay = 60*1000)
    public void reportCurrentTime()throws Exception {

        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int hour = c.get(Calendar.HOUR_OF_DAY);
        String yywdata;
        yywdata = ExportExcelUtils.batchSnycTestceshi();
        JSONObject obj= JSONObject.parseObject(yywdata);
        obj = obj.getJSONObject("data");
        JSONArray orderList = obj.getJSONArray("orderList");
        for (int i = 0;i<orderList.size();i++)
        {
            JSONObject objorder = (JSONObject) orderList.get(i);
            yywddhz hz = JSONObject.toJavaObject(objorder,yywddhz.class);
            spzlService.InToDDHZ(hz);
            JSONArray order_detailList = objorder.getJSONArray("order_detailList");
            for (int j= 0;j<order_detailList.size();j++)
            {
                JSONObject objorder_detail = (JSONObject) order_detailList.get(j);
                yywddmx mx = JSONObject.toJavaObject(objorder_detail,yywddmx.class);
                spzlService.InToDDMX(mx);
            }
        }
        spzlService.YYW_AddHZ();
        System.out.println("Do YYW_AddHZ time is  " + dateFormat.format(new Date()));
    }

}
