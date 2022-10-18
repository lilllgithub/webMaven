package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.po.*;

import com.util.DaoServiceUtil;
@Service("EmpServiceImpl")
public class EmpServiceImpl implements IEmpService {
	@Resource(name="DaoService")
    private DaoServiceUtil daoService;
	
	public DaoServiceUtil getDaoService() {
		return daoService;
	}

	public void setDaoService(DaoServiceUtil daoService) {
		this.daoService = daoService;
	}

	@Override
	public boolean save(Emp emp) {
		//添加员工方法
		int code=daoService.getEmpMapper().save(emp);
		if(code>0){
			//得出最新员工的eid
			int maxeid=daoService.getEmpMapper().findMaxEid(emp);
			//添加薪资
			Salary sa=new Salary(maxeid,emp.getEmoney());
			daoService.getSalaryMapper().save(sa);
			//添加福利
			String[] wids=emp.getWids();
			if(wids!=null&&wids.length>0){
				for(int i=0;i<wids.length;i++){
					EmpWelfare ewf=new EmpWelfare(maxeid,new Integer(wids[i]));
					daoService.getEmpwelfareMapper().save(ewf);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Emp emp) {
		//修改员工信息
		int code=daoService.getEmpMapper().update(emp);
		if(code>0){
			/**************薪资***************/
			//得出改员工的eid
			Salary oldsa=daoService.getSalaryMapper().findByEid(emp.getEid());
			//修改薪资
			if(oldsa!=null&&oldsa.getEmoney()!=null){//如果原来有薪资则修改如果没有则添加新的
				Salary sa=new Salary(oldsa.getEid(),oldsa.getEmoney());
				daoService.getSalaryMapper().updateByEid(sa);
			}else{//如果没有
				Salary sa=new Salary(emp.getEid(),emp.getEmoney());
				daoService.getSalaryMapper().save(sa);
			}
			/*******************************/
			//修改福利
			/**************福利***************/
			//如有之前有福利则删除
			List<Welfare> lswf=daoService.getEmpwelfareMapper().findByEid(emp.getEid());
			if(lswf!=null&&lswf.size()>0){
				daoService.getEmpwelfareMapper().delByEid(emp.getEid());
			}
			//添加
			String[] wids=emp.getWids();
			if(wids!=null&&wids.length>0){
				for(int i=0;i<wids.length;i++){
					EmpWelfare ewf=new EmpWelfare(emp.getEid(),new Integer(wids[i]));
					daoService.getEmpwelfareMapper().save(ewf);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean delByEid(Integer eid) {
		//删除薪资
		daoService.getSalaryMapper().delByEid(eid);
		//删除福利
		daoService.getEmpwelfareMapper().delByEid(eid);
		//删除员工
				int code=daoService.getEmpMapper().delByEid(eid);
				if(code>0){
					return true;
				}
		return false;
	}

	@Override
	public Emp findByEid(Integer eid) {
		//查询员工
		Emp oldemp=daoService.getEmpMapper().findByEid(eid);
		//查询薪资
		Salary oldsa=daoService.getSalaryMapper().findByEid(eid);
		if(oldsa!=null&&oldsa.getEmoney()!=null){
			oldemp.setEmoney(oldsa.getEmoney());
		}
		//查询福利
		List<Welfare> oldlswf=daoService.getEmpwelfareMapper().findByEid(eid);
		if(oldlswf!=null&&oldlswf.size()>0){
			String[] wids=new String[oldlswf.size()];
			for(int i=0;i<oldlswf.size();i++){
				Welfare wf=oldlswf.get(i);
				wids[i]=wf.getWid()+"";
			}
			oldemp.setWids(wids);
			oldemp.setLswf(oldlswf);;
		}
		
		return oldemp;
	}

	@Override
	public List<Emp> findPageAll(PageBean pb) {
		if(pb==null){
			pb=new PageBean();
		}
		return daoService.getEmpMapper().findPageAll(pb);
	}

	@Override
	public int findMaxRows() {
		// TODO Auto-generated method stub
		return daoService.getEmpMapper().findMaxRows();
	}



}
