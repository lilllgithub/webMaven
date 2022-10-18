package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.*;
@Service("EmpDao")
public interface IEmpMapper {
	/**添加**/
	public int save(Emp emp);
	/**修改**/
	public int update(Emp emp);
	/**删除**/
	public int delByEid(Integer eid);
	/**查询单个**/
	public Emp findByEid(Integer eid);
	/**查询分页全部**/
	public List<Emp> findPageAll(PageBean pb);
	/**总记录数**/
	public int findMaxRows();
	/**最大ID数**/
	public int findMaxEid(Emp emp);
	
}
