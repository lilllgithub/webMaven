package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.*;

public interface IEmpService {
	/**���**/
	public boolean save(Emp emp);
	/**�޸�**/
	public boolean update(Emp emp);
	/**ɾ��**/
	public boolean delByEid(Integer eid);
	/**��ѯ����**/
	public Emp findByEid(Integer eid);
	/**��ѯ��ҳȫ��**/
	public List<Emp> findPageAll(PageBean pb);
	/**�ܼ�¼��**/
	public int findMaxRows();
	
}
