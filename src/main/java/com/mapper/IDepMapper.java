package com.mapper;

import java.util.List;

import com.po.Dep;
import org.springframework.stereotype.Service;

@Service("DepDao")
public interface IDepMapper {
	/**查询全部部门**/
	public List<Dep> findAll();

	
}
