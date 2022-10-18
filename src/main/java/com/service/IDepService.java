package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.*;
import com.po.PageBean;

public interface IDepService {
	/**查询全部部门**/
	public List<Dep> findAll();

	
}
