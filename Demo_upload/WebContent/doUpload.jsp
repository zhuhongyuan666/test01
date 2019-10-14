<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//上传

	//服务器路径
	String path = request.getServletContext().getRealPath("/");
	//判断文件是否为上传表单
	boolean flag = ServletFileUpload.isMultipartContent(request);

	boolean flag1 = true;
	
	String fileName=null;
	if (flag) {
		//为上传表单
		//DiskFlieItemFactory
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		
		//解析为表单元素
		List<FileItem> list = servletFileUpload.parseRequest(request);
		String userName = null;
		for (FileItem fi : list) {
			if (!fi.isFormField()) {
				//文件表单元素
				fileName = fi.getName();
				//后缀名
				String fileHouzhui = fileName.substring(fileName.lastIndexOf(".") + 1);
				List<String> houZhuiList = new ArrayList<String>();
				houZhuiList.add("jpg");
				houZhuiList.add("bmp");
				houZhuiList.add("gif");
				if (houZhuiList.contains(fileHouzhui)) {
					File file = new File(path, fileName);
					//上传
					fi.write(file);//写到哪个路径      服务器文件路径
				} else {
					flag1 = false;
				}
			} else {
				//普通表单元素
				if ("userName".equals(fi.getFieldName())) {
					//上传人普通表单元素
					userName = fi.getString("UTF-8");
				}
			}
		}
		if (flag1) {
			out.print(userName + "上传成功文件路径为" + path);
		out.print("<img src='upload/"+fileName+"' />")
		} else {
			out.print("文件类型不对,上传失败");
		}
	}
%>