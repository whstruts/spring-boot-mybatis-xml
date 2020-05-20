package com.kfit.demo.mapper;

import com.kfit.demo.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpzlMappper {
	
	//#{name}:参数占位符

	@Select("select goods_id,goods_name,goods_number,ypdm,cdmc,gg,txm,shop_price,dw,jx,pzwh,bz,zbz,yxq,ph,isretail,pch,scrq from yzy_goods " +
			"where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1 " +
			"and ((ISRETAIL = 0 and goods_number+1 > bz * 20) or (ISRETAIL = 1 and goods_number+1 > bz * 1.5) )" +
			"and goods_sn like 'KX%' and bz > zbz and shop_price > 0 " +
			"or (goods_sn like 'YMD%'and is_on_sale = 1 and bz > 0)")
	public List<Spzl> getKC();

	@Select("select goods_id as g_in_sn,goods_sn as g_sn,txm as g_bar_code,goods_name as g_name,ypdm as g_name_code,'' as g_alias,pzwh as g_license_no," +
			"gg as g_specifications,dw as g_unit,zbz as g_middle_package,bz as g_big_package,cdmc as g_manufacture,cddm as g_manufacture_code," +
			"shop_price as g_price,shop_price as g_base_price,shop_price as g_cost_price,goods_number as g_realnum," +
			"'' as g_purview,isretail as g_can_split,'' as g_order_cate,'0' as g_area,'0' as g_control,'1' as g_status from yzy_goods " +
			"where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1 and is_sy > 0  " +
			"and ((ISRETAIL = 0 and goods_number+1 > bz * 20) or (ISRETAIL = 1 and goods_number+1 > bz * 1.5) )" +
			"and goods_sn like 'KX%' and bz > zbz and shop_price > 0 " +
			"or (goods_sn like 'YMD%'and is_on_sale = 1 and bz > 0)")
	public List<Spb> getspb();

	@Select("select goods_id as g_in_sn,goods_id as gb_id_no,'' as gb_ku_no,'' as gb_hw_no,ph as gb_batch_no," +
			"yxq as gb_end_time, goods_number as gb_number from yzy_goods " +
			"where RPAD(YXQ,10,'-15') >sysdate()  and is_on_sale = 1 and is_sy > 0 " +
			"and ((ISRETAIL = 0 and goods_number+1 > bz * 20) or (ISRETAIL = 1 and goods_number+1 > bz * 1.5) )" +
			"and goods_sn like 'KX%' and bz > zbz and shop_price > 0 " +
			"or (goods_sn like 'YMD%'and is_on_sale = 1 and bz > 0)")
	public List<kcb> getkcb();

	@Select("select code as  c_internal_code,dwbh as  c_code,name as  c_name ,jcpym as  c_name_code ,	'1' as  c_status ,"+
			"'1' as  c_isxs ,'0' as  c_is_wholesaler ,zzzch as  c_license_no,	zzyxqz as  c_license_endtime ,	xkzh as  c_allow_no,"+
			"xkzyxqz as  c_allow_endtime ,gspzh as  c_gsp_no ,gspzyxqz as  c_gsp_endtime ,dwjb as  c_customer_type,'' as  c_purview ,"+
			"'' as  c_area ,'' as  c_salesman,	qyfr as  c_legal,	'' as  c_legal_no,shr1 as  c_contact,email as  c_email,telephone as  c_tel,"+
			"fzrlxdh as  c_phone,'' as  c_province,'' as  c_city,'' as  c_address from zt_kh where xkzyxqz <> '0000-00-00' and zzyxqz <> '0000-00-00' LIMIT 58")
	public List<Khzl> getkhb();



	@Select("select goods_sn,case  when isretail = 1 then zbz else bz end goods_mpn from hykx.yzy_goods ")
	public List<Spmpn> getmpn();

	@Select("select goods_name,dw,jx,gg,cdmc,bz,pzwh  from yzy_goods group by goods_name,dw,jx,gg,cdmc,bz ")
	public List<Spzl> getspzlx();


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
	
}
