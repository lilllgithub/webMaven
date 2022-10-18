package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.Emp;
import com.po.PageBean;

public interface IEmpAction {
	public String save(HttpServletResponse response, HttpServletRequest request, Emp emp);
	public String update(HttpServletResponse response, HttpServletRequest request, Emp emp);
	public String delByid(HttpServletResponse response, HttpServletRequest request, Integer eid);
	public String findByid(HttpServletResponse response, HttpServletRequest request, Integer eid);
	public String findDetail(HttpServletResponse response, HttpServletRequest request, Integer eid);
	public String findPageAll(HttpServletResponse response, HttpServletRequest request, Integer page, Integer rows);
	public String doinit(HttpServletRequest request, HttpServletResponse response);
	
}
