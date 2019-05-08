<%-- 
    Document   : error.jsp
    Created on : 24-Apr-2019, 17:00:20
    Author     : MinhKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1><font color="red"><s:property value="%{err}"/></font></h1>
    </body>
</html>
