<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传</title>
</head>
<body>
<form enctype="multipart/form-data" method="post" action="doUpload.jsp">
上传人<input type="text" name="userName"/><br/>
请选择上传文件<input type="file" name="uploadFile"/>
<br>
<hr>
<input type="submit" value="上传">
</form>

</body>
</html>