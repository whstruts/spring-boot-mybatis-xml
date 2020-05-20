package com.kfit.demo.mapper;

import com.kfit.demo.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SpzlMapper {
	
	//#{name}:参数占位符

	@Select("select goods_id,goods_name,goods_number,ypdm,cdmc,gg,txm,shop_price,dw,jx,pzwh,bz,zbz,yxq,ph,isretail,pch,scrq from yzy_goods " +
			"where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1 " +
			"and ((ISRETAIL = 0 and goods_number+1 > bz * 20) or (ISRETAIL = 1 and goods_number+1 > bz * 1.5) )" +
			"and goods_sn like 'KC%' and bz > zbz and shop_price > 0 " +
			"or (goods_sn like 'YMD%'and is_on_sale = 1 and bz > 0)")
	public List<Spzl> getKC();

	@Select("select goods_id as g_in_sn,goods_sn as g_sn,txm as g_bar_code,goods_name as g_name,ypdm as g_name_code,'' as g_alias,pzwh as g_license_no," +
			"gg as g_specifications,dw as g_unit,zbz as  g_middle_package,bz as g_big_package,cdmc as g_manufacture,cddm as g_manufacture_code," +
			"shop_price*0.91 as g_price,shop_price_yyw as g_base_price,shop_price_yyw as g_cost_price,goods_number as g_realnum," +
			"'' as g_purview,isretail as g_can_split,'' as g_order_cate,'0' as g_area,'0' as g_control,'1' as g_status from yzy_goods " +
			"where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1   " +
			"and  (ISRETAIL = 1 and goods_number+1 > bz * 1.5) " +
			"and goods_sn like 'KX%' and bz > zbz and shop_price > 0 " )
	public List<Spb> getspb();

	@Select("select goods_id as g_in_sn,goods_id as gb_id_no,'' as gb_ku_no,'' as gb_hw_no,ph as gb_batch_no," +
			"yxq as gb_end_time, goods_number as gb_number from yzy_goods " +
			"where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1   " +
			"and  (ISRETAIL = 1 and goods_number+1 > bz * 1.5) " +
			"and goods_sn like 'KX%' and bz > zbz and shop_price > 0 " )
	public List<kcb> getkcb();

	@Select("select code as  c_internal_code,dwbh as  c_code,name as  c_name ,jcpym as  c_name_code ,	'1' as  c_status ,"+
			"'1' as  c_isxs ,'0' as  c_is_wholesaler ,zzzch as  c_license_no,	zzyxqz as  c_license_endtime ,	xkzh as  c_allow_no,"+
			"xkzyxqz as  c_allow_endtime ,gspzh as  c_gsp_no ,gspzyxqz as  c_gsp_endtime ,dwjb as  c_customer_type,'' as  c_purview ,"+
			"'' as  c_area ,'' as  c_salesman,	qyfr as  c_legal,	'' as  c_legal_no,shr1 as  c_contact,email as  c_email,telephone as  c_tel,"+
			"fzrlxdh as  c_phone,'' as  c_province,'' as  c_city,'' as  c_address from zt_kh where xkzyxqz <> '0000-00-00' and zzyxqz <> '0000-00-00' LIMIT 58")
	public List<Khzl> getkhb();



	@Select("select goods_sn,case  when isretail = 1 then zbz else bz end goods_mpn,jx,gg,dw from hykx.yzy_goods ")
	public List<Spmpn> getmpn();

	@Select("select goods_sn,goods_name,gg,cdmc,case  when isretail = 1 then zbz else bz end mpn,"+
			" bz,shop_price,dw,jx,pzwh,txm from hykx.yzy_goods  where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1   " +
			" and  (ISRETAIL = 1 and goods_number+1 > bz * 1.5) " +
			" and goods_sn like 'YSBKX%' and bz > zbz and shop_price > 0 LIMIT #{aaa},#{bbb}")
	public List<Spzl> getyycsjsp(hh h1);

    @Select("SELECT goods_id,goods_number,ROUND(DJ*1.15,2) as gyj_price,shop_price," +
			" goods_sn,goods_name,gg,case  when isretail = 1 then zbz else bz end mpn, "+
            " cdmc,bz,dw,jx,pzwh,txm,goods_sn,yxq,zbz,case  when isretail = 1 then '拆零' else '整件' end sfcl " +
			" FROM hykx.yzy_goods " +
			" WHERE RPAD( YXQ, 10, '-01' ) >  DATE_ADD(SYSDATE(),INTERVAL 365 DAY) " +
            " AND is_on_sale = 1  AND goods_sn LIKE 'YSBKX%' AND shop_price > 0 ")
    public List<Spzl> getxyysp();

    @Select("SELECT goods_id,goods_number,ROUND(DJ*1.15,2) as gyj_price,shop_price," +
            " goods_sn,goods_name,gg,"+
            " cdmc,bz,dw,jx,pzwh,txm " +
            " FROM hykx.yzy_goods " +
           "  where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1   " +
            " and  ((ISRETAIL = 1 and goods_number+1 > bz/2) or (ISRETAIL = 0 and goods_number+1 > bz*2))" +
            " and goods_sn like 'YSBKX%' and bz > zbz and shop_price > 0 and from_unixtime(add_time,'%Y-%m-%d')= #{sdate}")
    public List<Spzl> getysbnew(String sdate);

	@Select("select goods_sn,goods_name,gg,cdmc,case  when isretail = 1 then zbz else bz end mpn,"+
			" bz,shop_price,dw,jx,pzwh,txm from hykx.yzy_goods  where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1   " +
			" and  (ISRETAIL = 1 and goods_number+1 > bz * 1.5) " +
			" and goods_sn like 'YSBKX%' and bz > zbz and shop_price > 0 ")
	public List<Spzl> getyycsjspa();

	@Select("select goods_name,dw,jx,gg,cdmc,bz,pzwh  from yzy_goods group by goods_name,dw,jx,gg,cdmc,bz ")
	public List<Spzl> getspzlx();


	@Select("SELECT u.user_name,u.user_id,k.name,u.user_money - u.frozen_money kyje FROM " +
			"huayuan_shop.yzy_reg_extend_info i,huayuan_shop.zt_kh k,huayuan_shop.yzy_users u, " +
			"(select user_id,sum(user_money) sum_money from yzy_account_log where change_time <= UNIX_TIMESTAMP(#{sum_date})+86400 GROUP BY user_id) x  " +
			"WHERE k. CODE = i.content AND i.reg_field_id = 12 AND i.user_id = u.user_id AND k. NAME IS NOT NULL " +
			"and u.user_id = x.user_id and x.sum_money - u.frozen_money > 0 ORDER BY u.user_money - u.frozen_money desc ")
	public List<User> getusers(String sum_date);


	@Select("select case  when isretail = 1 then zbz else bz end goods_mpn from hykx.yzy_goods where goods_sn = #{goods_sn}")
	public String getmpnbysn(String goods_sn);

	@Insert("INSERT INTO demo(name) VALUES(#{name})")
	void insert(User user);

	@Insert("replace INTO yyw_ddhz(mph_order_sn,erp_sn,pre_subtotal,subtotal,vouchers_price,number,price,pre_price,accept_name,mobile,address,internal_code,order_status,express_price,payment_method,order_addTime) "+
			" VALUES(#{mph_order_sn},#{erp_sn},#{pre_subtotal},#{subtotal},#{vouchers_price},#{number},"+
			"#{price},#{pre_price},#{accept_name},#{mobile},#{address},#{internal_code},#{order_status},#{express_price},#{payment_method},#{order_addTime})")
	void inserthz(yywddhz hz);

	@Insert("replace INTO yyw_ddmx(order_detail_id,mph_order_sn,erp_sn,name,manufacture,license_no,specifications,unit,middle_package,big_package,"+
			"can_split,pre_subtotal,subtotal,vouchers_price,number,send_number,price,pre_price,pd_sale_time,od_unit_price,preferential_unit_price) "+
			"VALUES(#{order_detail_id},#{mph_order_sn},#{erp_sn},#{name},#{manufacture},#{license_no},#{specifications},#{unit},#{middle_package},#{big_package},"+
			"#{can_split},#{pre_subtotal},#{subtotal},#{vouchers_price},#{number},#{send_number},#{price},#{pre_price},#{pd_sale_time},#{od_unit_price},#{preferential_unit_price}) ")
	void insertmx(yywddmx mx);

	@Select("select goods_id,goods_name,goods_number,ypdm,cdmc,gg,txm,shop_price,dw,jx,pzwh,bz,zbz,yxq,ph,isretail,pch,scrq from yzy_goods where goods_id = #{goods_id}")
	public Spzl getById(long goods_id);
	
	@Select("select goods_id,goods_name,goods_number,ypdm,cdmc,gg,txm,shop_price,dw,jx,pzwh,bz,zbz,yxq,ph,isretail,pch,scrq from yzy_goods where goods_name like #{goods_name}")
	public List<Spzl> getspByName(String goods_name);

	@Select("call YYW_AddHZ() ")
	void YYW_AddHZ();

}
