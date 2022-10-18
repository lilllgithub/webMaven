package com.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.serializer.PropertyFilter;

public class AJAXUtil {
/**������������Ӧת��json*/
	public static void printString(HttpServletResponse response,String s){
		response.setCharacterEncoding("utf-8");
		PrintWriter out=null;
		try {
			out = response.getWriter();
			System.out.println("����ǰ�˵�json�ַ���:"+s);
			out.print(s);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	};
/**
 * ��������
 * */
public static PropertyFilter filterProperys(final String ... PropNames){
	PropertyFilter propertyFilter=new PropertyFilter() {
		
		@Override
		public boolean apply(Object arg0, String PropName, Object arg2) {
			for(String pname:PropNames){
				if(PropName.equals(pname)){
					return false;//���˸�����
				}
			}
			return true;//�����˸�����
		}
	};
	return propertyFilter;
	
}
	
}
