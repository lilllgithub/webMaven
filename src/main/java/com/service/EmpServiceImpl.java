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
		//���Ա������
		int code=daoService.getEmpMapper().save(emp);
		if(code>0){
			//�ó�����Ա����eid
			int maxeid=daoService.getEmpMapper().findMaxEid(emp);
			//���н��
			Salary sa=new Salary(maxeid,emp.getEmoney());
			daoService.getSalaryMapper().save(sa);
			//��Ӹ���
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
		//�޸�Ա����Ϣ
		int code=daoService.getEmpMapper().update(emp);
		if(code>0){
			/**************н��***************/
			//�ó���Ա����eid
			Salary oldsa=daoService.getSalaryMapper().findByEid(emp.getEid());
			//�޸�н��
			if(oldsa!=null&&oldsa.getEmoney()!=null){//���ԭ����н�����޸����û��������µ�
				Salary sa=new Salary(oldsa.getEid(),oldsa.getEmoney());
				daoService.getSalaryMapper().updateByEid(sa);
			}else{//���û��
				Salary sa=new Salary(emp.getEid(),emp.getEmoney());
				daoService.getSalaryMapper().save(sa);
			}
			/*******************************/
			//�޸ĸ���
			/**************����***************/
			//����֮ǰ�и�����ɾ��
			List<Welfare> lswf=daoService.getEmpwelfareMapper().findByEid(emp.getEid());
			if(lswf!=null&&lswf.size()>0){
				daoService.getEmpwelfareMapper().delByEid(emp.getEid());
			}
			//���
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
		//ɾ��н��
		daoService.getSalaryMapper().delByEid(eid);
		//ɾ������
		daoService.getEmpwelfareMapper().delByEid(eid);
		//ɾ��Ա��
				int code=daoService.getEmpMapper().delByEid(eid);
				if(code>0){
					return true;
				}
		return false;
	}

	@Override
	public Emp findByEid(Integer eid) {
		//��ѯԱ��
		Emp oldemp=daoService.getEmpMapper().findByEid(eid);
		//��ѯн��
		Salary oldsa=daoService.getSalaryMapper().findByEid(eid);
		if(oldsa!=null&&oldsa.getEmoney()!=null){
			oldemp.setEmoney(oldsa.getEmoney());
		}
		//��ѯ����
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
