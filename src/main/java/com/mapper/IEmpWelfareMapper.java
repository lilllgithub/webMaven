package com.mapper;
import java.util.List;

import org.springframework.stereotype.Service;

import com.po.*;
@Service("EmpWelfareDao")
public interface IEmpWelfareMapper {
	/**���Ա������*/
	 public int save(EmpWelfare ewf);
	 /**����Ա�����ɾ����������*/
	 public int  delByEid(Integer eid);
	 /**����Ա��ID��ѯ��Ա����Ӧ�ĸ���*/
	 public List<Welfare> findByEid(Integer eid);
}
