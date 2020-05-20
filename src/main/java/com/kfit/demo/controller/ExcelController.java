package com.kfit.demo.controller;

import com.kfit.demo.bean.Spmpn;
import com.kfit.demo.bean.Spzl;
import com.kfit.demo.bean.User;
import com.kfit.demo.bean.hh;
import com.kfit.demo.service.SpzlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouLu on 2017-12-14.
 */
@Controller
public class ExcelController {

    @Autowired
    private SpzlService spzlService;
    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public void excel(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        List<Spmpn> spmpns = new ArrayList();
        spmpns = spzlService.getmpn();

        data.setName("sheet1");

        List<String> titles = new ArrayList();
        titles.add("商品编码");
        titles.add("购买倍数");
        titles.add("商品剂型");
        titles.add("商品规格");
        titles.add("包装单位");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();

        for(int i = 0;i<spmpns.size();i++)
        {
            List<Object> row = new ArrayList();
            row.add(spmpns.get(i).getGoods_sn());
            row.add(spmpns.get(i).getGoods_mpn());
            row.add(spmpns.get(i).getJx());
            row.add(spmpns.get(i).getGg());
            row.add(spmpns.get(i).getDw());
            rows.add(row);
        }
        data.setRows(rows);

        ExportExcelUtils.exportExcel(response,"购买倍数.xlsx",data);
    }

    @RequestMapping(value = "/excelspzl", method = RequestMethod.GET)
    public void excelspzl(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        List<Spzl> spzls = new ArrayList();
        spzls = spzlService.getspzlx();

        data.setName("sheet1");

        List<String> titles = new ArrayList();
        titles.add("商品名称");
        titles.add("单位");
        titles.add("剂型");
        titles.add("规格");
        titles.add("生产厂家");
        titles.add("包装");
        titles.add("批准文号");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();

        for(int i = 0;i<spzls.size();i++)
        {
            List<Object> row = new ArrayList();
            row.add(spzls.get(i).getGoods_name());
            row.add(spzls.get(i).getDw());
            row.add(spzls.get(i).getJx());
            row.add(spzls.get(i).getGg());
            row.add(spzls.get(i).getCdmc());
            row.add(spzls.get(i).getBz());
            row.add(spzls.get(i).getPzwh());
            rows.add(row);
        }
        data.setRows(rows);

        ExportExcelUtils.exportExcel(response,"商品资料.xlsx",data);
    }

    @RequestMapping(value = "/yycsp", method = RequestMethod.GET)
    public void excelspzl(HttpServletResponse response,int bn,int en) throws Exception {
        hh h1 = new hh();
        h1.setAaa(bn);
        h1.setBbb(en);
        ExcelData data = new ExcelData();
        List<Spzl> spzls = new ArrayList();
        spzls = spzlService.getyycsjsp(h1);

        data.setName("sheet1");

        List<String> titles = new ArrayList();
        titles.add("本公司商品编码");
        titles.add("通用名");
        titles.add("规格");
        titles.add("生产厂家");
        titles.add("最小拆零包装");
        titles.add("大包装数量");
        titles.add("公开价");
        titles.add("最小包装单位");
        titles.add("剂型");
        titles.add("批准文号");
        titles.add("商品条码");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();

        for(int i = 0;i<spzls.size();i++)
        {
            List<Object> row = new ArrayList();
            row.add(spzls.get(i).getGoods_sn());
            row.add(spzls.get(i).getGoods_name());
            row.add(spzls.get(i).getGg());
            row.add(spzls.get(i).getCdmc());
            row.add(spzls.get(i).getMpn());
            row.add(spzls.get(i).getBz());
            row.add(spzls.get(i).getShop_price());
            row.add(spzls.get(i).getDw());
            row.add(spzls.get(i).getJx());
            row.add(spzls.get(i).getPzwh());
            row.add(spzls.get(i).getTxm());
            rows.add(row);
        }
        data.setRows(rows);

        ExportExcelUtils.exportExcel(response,"商品发布模板.xlsx",data);
    }

    @RequestMapping(value = "/yycspa", method = RequestMethod.GET)
    public void excelspzla(HttpServletResponse response) throws Exception {

        ExcelData data = new ExcelData();
        List<Spzl> spzls = new ArrayList();
        spzls = spzlService.getyycsjsp();

        data.setName("sheet1");

        List<String> titles = new ArrayList();
        titles.add("本公司商品编码");
        titles.add("通用名");
        titles.add("规格");
        titles.add("生产厂家");
        titles.add("最小拆零包装");
        titles.add("大包装数量");
        titles.add("公开价");
        titles.add("最小包装单位");
        titles.add("剂型");
        titles.add("批准文号");
        titles.add("商品条码");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();

        for(int i = 0;i<spzls.size();i++)
        {
            List<Object> row = new ArrayList();
            row.add(spzls.get(i).getGoods_sn());
            row.add(spzls.get(i).getGoods_name());
            row.add(spzls.get(i).getGg());
            row.add(spzls.get(i).getCdmc());
            row.add(spzls.get(i).getMpn());
            row.add(spzls.get(i).getBz());
            row.add(spzls.get(i).getShop_price());
            row.add(spzls.get(i).getDw());
            row.add(spzls.get(i).getJx());
            row.add(spzls.get(i).getPzwh());
            row.add(spzls.get(i).getTxm());
            rows.add(row);
        }
        data.setRows(rows);

        ExportExcelUtils.exportExcel(response,"商品发布模板.xlsx",data);
    }

    @RequestMapping(value = "/xyysp", method = RequestMethod.GET)
    public void excelxyysp(HttpServletResponse response) throws Exception {

        ExcelData data = new ExcelData();
        List<Spzl> spzls = new ArrayList();
        spzls = spzlService.getxyysp();

        data.setName("sheet1");

        List<String> titles = new ArrayList();
        titles.add("本公司商品ID");
        titles.add("库存数量");
        titles.add("供货价格");
        titles.add("商品名称");
        titles.add("规格");
        titles.add("生产厂家");
        titles.add("包装数");
        titles.add("包装单位");
        titles.add("剂型");
        titles.add("批准文号");
        titles.add("商品条码");
        titles.add("商品编码");
        titles.add("有效期至");
        titles.add("中包装");
        titles.add("是否拆零");
        titles.add("采购起订量");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();

        for(int i = 0;i<spzls.size();i++)
        {
            List<Object> row = new ArrayList();
            row.add(spzls.get(i).getGoods_id());
            row.add(spzls.get(i).getGoods_number());
            row.add(spzls.get(i).getGyj_price());
            row.add(spzls.get(i).getGoods_name());
            row.add(spzls.get(i).getGg());
            row.add(spzls.get(i).getCdmc());
            row.add(spzls.get(i).getBz());
            row.add(spzls.get(i).getDw());
            row.add(spzls.get(i).getJx());
            row.add(spzls.get(i).getPzwh());
            row.add(spzls.get(i).getTxm());
            row.add(spzls.get(i).getGoods_sn());
            row.add(spzls.get(i).getYxq());
            row.add(spzls.get(i).getZbz());
            row.add(spzls.get(i).getSfcl());
            row.add(spzls.get(i).getMpn());
            rows.add(row);
        }
        data.setRows(rows);

        ExportExcelUtils.exportExcel(response,"供货商品列表.xlsx",data);
    }

    @RequestMapping(value = "/ymduser", method = RequestMethod.GET)
    public void excelymduser(HttpServletResponse response,String sum_date) throws Exception {

        ExcelData data = new ExcelData();
        List<User> users = new ArrayList();
        users = spzlService.getuser(sum_date);

        data.setName("sheet1");

        List<String> titles = new ArrayList();
        titles.add("药满多用户ID");
        titles.add("药满多用户编号");
        titles.add("用户名");
        titles.add("可用余额");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();

        for(int i = 0;i<users.size();i++)
        {
            List<Object> row = new ArrayList();
            row.add(users.get(i).getUser_id());
            row.add(users.get(i).getUser_name());
            row.add(users.get(i).getName());
            row.add(users.get(i).getKyje());
            rows.add(row);
        }
        data.setRows(rows);

        ExportExcelUtils.exportExcel(response,"药满多客户列表.xlsx",data);
    }

    @RequestMapping(value = "/ysbspnew", method = RequestMethod.GET)
    public void excelysbspnew(HttpServletResponse response,String rq) throws Exception {

        ExcelData data = new ExcelData();
        List<Spzl> spzls = new ArrayList();
        spzls = spzlService.getysbspnew(rq);

        data.setName("sheet1");

        List<String> titles = new ArrayList();
        titles.add("商品编号");
        titles.add("库存数量");
        titles.add("价格");
        titles.add("商品名称");
        titles.add("规格");
        titles.add("生产厂家");
        titles.add("包装数");
        titles.add("包装单位");
        titles.add("剂型");
        titles.add("批准文号");
        titles.add("商品条码");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();

        for(int i = 0;i<spzls.size();i++)
        {
            List<Object> row = new ArrayList();
            row.add(spzls.get(i).getGoods_sn());
            row.add(spzls.get(i).getGoods_number());
            row.add(spzls.get(i).getShop_price());
            row.add(spzls.get(i).getGoods_name());
            row.add(spzls.get(i).getGg());
            row.add(spzls.get(i).getCdmc());
            row.add(spzls.get(i).getBz());
            row.add(spzls.get(i).getDw());
            row.add(spzls.get(i).getJx());
            row.add(spzls.get(i).getPzwh());
            row.add(spzls.get(i).getTxm());
            rows.add(row);
        }
        data.setRows(rows);

        ExportExcelUtils.exportExcel(response,"药师帮商品列表.xlsx",data);
    }



    @RequestMapping(value = "/sntompn", method = RequestMethod.GET)
    @ResponseBody
    public String sntompn(String goods_sn){
          return spzlService.getmpnbysn(goods_sn);
    }

    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //上传文件的路径
       // String path = ExportExcelUtils.getFileInfo(request, response, file);
        String filename = file.getOriginalFilename();
        ExportExcelUtils.DoFile(filename,file);
    }

    @RequestMapping(value = "/UpLoad", method = RequestMethod.GET)
    public String toUpLoad(){
        ExportExcelUtils.batchSnycTestceshi();
        return "/UpLoad";
    }

}
