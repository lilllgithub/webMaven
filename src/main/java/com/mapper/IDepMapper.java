package com.mapper;

import java.util.List;

import com.po.Dep;
import org.springframework.stereotype.Service;

@Service("DepDao")
public interface IDepMapper {
	/**��ѯȫ������**/
	public List<Dep> findAll();

	
}
