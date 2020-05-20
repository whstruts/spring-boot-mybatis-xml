package com.kfit.demo.service;

import com.kfit.demo.bean.*;
import com.kfit.demo.mapper.SpzlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SpzlService {

	@Autowired
	private SpzlMapper spzlMappper;
    public List<Spzl> getspzlx(){ return spzlMappper.getspzlx();}
	public String getmpnbysn(String goods_sn) {return  spzlMappper.getmpnbysn(goods_sn);}
	public List<Spzl> getyycsjsp(hh h1) {return  spzlMappper.getyycsjsp(h1);}
	public List<Spzl> getyycsjsp() {return  spzlMappper.getyycsjspa();}
	public List<Spzl> getxyysp() {return  spzlMappper.getxyysp();}
	public List<Spmpn> getmpn(){return spzlMappper.getmpn();}
	public List<Spzl> getKC() {
		return spzlMappper.getKC();
	}
	public List<Spb> getspb() {
		return spzlMappper.getspb();
	}
	public List<kcb> getkcb() {
		return spzlMappper.getkcb();
	}
	public List<Khzl> getkhb() {
		return spzlMappper.getkhb();
	}

	public List<User> getuser(String sum_date){ return spzlMappper.getusers(sum_date);} //20190426 whstruts

	public void ItoUsers(User user){
		spzlMappper.insert(user);
	}
	public void InToDDHZ(yywddhz hz){
		spzlMappper.inserthz(hz);
	}
	public void InToDDMX(yywddmx mx){
	    spzlMappper.insertmx(mx);
	}
	public void YYW_AddHZ(){
		spzlMappper.YYW_AddHZ();
	}
	public List<Spzl> getysbspnew(String sdate) {return  spzlMappper.getysbnew(sdate);}
}
