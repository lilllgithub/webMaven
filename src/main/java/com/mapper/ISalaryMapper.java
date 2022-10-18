package com.mapper;
import java.util.List;

import org.springframework.stereotype.Service;

import com.po.*;
@Service("SalaryDao")
public interface ISalaryMapper {
	 /**添加薪资*/
	 public int save(Salary sa);
	 /**修改薪资*/
	 public int updateByEid(Salary sa);
	 /**删除薪资*/
	 public int  delByEid(Integer eid);
	 /**查询单个员工薪资*/
	 public Salary findByEid(Integer eid);

}
