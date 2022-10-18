package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.po.Dep;
import com.po.Emp;
import com.po.PageBean;
import com.po.Welfare;
import com.util.*;
@Controller
public class EmpController implements IEmpAction {
	@Resource(name="BizService")
	private BizServiceUtil bizService;
	
	public BizServiceUtil getBizService() {
		return bizService;
	}

	public void setBizService(BizServiceUtil bizService) {
		this.bizService = bizService;
	}

	@RequestMapping(value="save_Emp.do")
	public String save(HttpServletResponse response, HttpServletRequest request, Emp emp) {
		String realpath=request.getRealPath("/");
		System.out.println("realpath:"+realpath);
		System.out.println("save==emp:"+emp.toString());
		/***********�ļ��ϴ�begin************/
		MultipartFile multipartFile=emp.getPic();
		if(multipartFile!=null && !multipartFile.isEmpty()){
			//��ȡ�ϴ��ļ�����
			String fname=multipartFile.getOriginalFilename();
			//�޸��ļ�����
			if(fname.lastIndexOf(".")!=-1){//���ں�׺
				//��ȡ��׺
				String ext=fname.substring(fname.lastIndexOf("."));
				//�����ļ���ʽ
				if(ext.equalsIgnoreCase(".jpg")||ext.equalsIgnoreCase(".jpeg")){
					//����
					String newfname=new Date().getTime()+ext;
					//�����ļ�����ָ���ϴ��ļ���·��
					File dostFile=new File(realpath+"/uppic/"+newfname);
					//�ϴ�
					try {
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), dostFile);
					    //�洢���ļ���
						emp.setFname(newfname);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		/***********�ļ��ϴ�end************/
		boolean flag=bizService.getEmpServiceImpl().save(emp);
		if(flag){
			AJAXUtil.printString(response,"1");
		}else{
			AJAXUtil.printString(response,"0");
		}
		return null;
	}

	@RequestMapping(value="update_Emp.do")
	public String update(HttpServletResponse response, HttpServletRequest request, Emp emp) {
		String realpath=request.getRealPath("/");
		System.out.println("realpath:"+realpath);
		//1���ж�ԭ���Ƿ����ļ�
		String oldfname=bizService.getEmpServiceImpl().findByEid(emp.getEid()).getFname();
		/***********�ļ��ϴ�begin************/
		MultipartFile multipartFile=emp.getPic();
		if(multipartFile!=null && !multipartFile.isEmpty()){
			//��ȡ�ϴ��ļ�����
			String fname=multipartFile.getOriginalFilename();
			//�޸��ļ�����
			if(fname.lastIndexOf(".")!=-1){//���ں�׺
				//��ȡ��׺
				String ext=fname.substring(fname.lastIndexOf("."));
				//�����ļ���ʽ
				if(ext.equalsIgnoreCase(".jpg")||ext.equalsIgnoreCase(".jpeg")){
					//����
					String newfname=new Date().getTime()+ext;
					//�����ļ�����ָ���ϴ��ļ���·��
					File dostFile=new File(realpath+"/uppic/"+newfname);
					//�ϴ�
					try {
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), dostFile);
					    //�洢���ļ���
						emp.setFname(newfname);
						//ɾ��ԭ����
						if(oldfname!=null){
							File oldfile=new File(realpath+"/uppic/"+oldfname);
							if(oldfile!=null&&!oldfname.equalsIgnoreCase("default.jpg")){
								oldfile.delete();
							}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else{
			emp.setFname(oldfname);
		}
		/***********�ļ��ϴ�end************/
		boolean flag=bizService.getEmpServiceImpl().update(emp);
		if(flag){
			AJAXUtil.printString(response,"1");
		}else{
			AJAXUtil.printString(response,"0");
		}
		return null;
	}

	@RequestMapping(value="delById_Emp.do")
	public String delByid(HttpServletResponse response, HttpServletRequest request, Integer eid) {
		boolean flag=bizService.getEmpServiceImpl().delByEid(eid);
		if(flag){
			AJAXUtil.printString(response,"1");
		}else{
			AJAXUtil.printString(response,"0");
		}
		return null;
	}

	@RequestMapping(value="findById_Emp.do")
	public String findById(HttpServletRequest request, HttpServletResponse response, Integer eid) {
		Emp oldEmp=bizService.getEmpServiceImpl().findByEid(eid);
		PropertyFilter propertyFilter=AJAXUtil.filterProperys("birthday","pic");
		String JSONStr=JSONObject.toJSONString(oldEmp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		AJAXUtil.printString(response, JSONStr);
		return null;
	}

	@RequestMapping(value="findDetail_Emp.do")
	public String findDetail(HttpServletResponse response, HttpServletRequest request, Integer eid) {
		Emp oldemp=bizService.getEmpServiceImpl().findByEid(eid);
		PropertyFilter propertyFilter=AJAXUtil.filterProperys("birthday","pic");
		String JSONStr=JSONObject.toJSONString(oldemp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		AJAXUtil.printString(response, JSONStr);
		return null;
	}

	@RequestMapping(value="findPageAll_Emp.do")
	public String findPageAll(HttpServletResponse response, HttpServletRequest request, Integer page, Integer rows) {
		Map<String,Object> map=new HashMap<String,Object>();
		PageBean pb=new PageBean();
		page=page==null||page<1?pb.getPage():page;
		rows=rows==null||rows<1?pb.getRows():rows;
		if(rows>10)rows=10;
		pb.setPage(page);
		pb.setRows(rows);
		List<Emp> lsemp=bizService.getEmpServiceImpl().findPageAll(pb);
		int maxRows=bizService.getEmpServiceImpl().findMaxRows();
		//��װeasyui��ҳ������õ�map
		map.put("page", page);
		map.put("rows", lsemp);
		map.put("total", maxRows);
		PropertyFilter propertyFilter=AJAXUtil.filterProperys("birthday","pic");
		String JSONStr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		AJAXUtil.printString(response, JSONStr);
		
		return null;
	}

	@RequestMapping(value="doinit_Emp.do")
	public String doinit(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map=new HashMap<String,Object>();
		List<Dep> lsdep=bizService.getDepServiceImpl().findAll();
		List<Welfare> lswf=bizService.getWelfareServiceImpl().findAll();
		map.put("lsdep", lsdep);
		map.put("lswf", lswf);
		PropertyFilter propertyFilter=AJAXUtil.filterProperys("birthday","pic");
		String JSONStr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		AJAXUtil.printString(response, JSONStr);
		return null;
	}

	@Override
	public String findByid(HttpServletResponse response, HttpServletRequest request, Integer eid) {
		// TODO Auto-generated method stub
		return null;
	}

}
