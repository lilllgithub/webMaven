package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.*;

public interface IEmpService {
	/**添加**/
	public boolean save(Emp emp);
	/**修改**/
	public boolean update(Emp emp);
	/**删除**/
	public boolean delByEid(Integer eid);
	/**查询单个**/
	public Emp findByEid(Integer eid);
	/**查询分页全部**/
	public List<Emp> findPageAll(PageBean pb);
	/**总记录数**/
	public int findMaxRows();
	
}
