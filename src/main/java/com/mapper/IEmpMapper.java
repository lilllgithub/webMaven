package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.*;
@Service("EmpDao")
public interface IEmpMapper {
	/**���**/
	public int save(Emp emp);
	/**�޸�**/
	public int update(Emp emp);
	/**ɾ��**/
	public int delByEid(Integer eid);
	/**��ѯ����**/
	public Emp findByEid(Integer eid);
	/**��ѯ��ҳȫ��**/
	public List<Emp> findPageAll(PageBean pb);
	/**�ܼ�¼��**/
	public int findMaxRows();
	/**���ID��**/
	public int findMaxEid(Emp emp);
	
}
