package com.mapper;
import java.util.List;

import org.springframework.stereotype.Service;

import com.po.*;
@Service("SalaryDao")
public interface ISalaryMapper {
	 /**���н��*/
	 public int save(Salary sa);
	 /**�޸�н��*/
	 public int updateByEid(Salary sa);
	 /**ɾ��н��*/
	 public int  delByEid(Integer eid);
	 /**��ѯ����Ա��н��*/
	 public Salary findByEid(Integer eid);

}
