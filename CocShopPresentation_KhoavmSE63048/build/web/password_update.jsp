<%-- 
    Document   : password_update
    Created on : 15-Dec-2018, 18:05:12
    Author     : MinhKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="update_insert_style.css">
        <title>Change Password Page</title>
        <style>
            input[type=submit],input[type=text],input[type=password]{               
                width: 360px;
                border-radius: 4px;


            }
            span{
                display: block;
                text-align: center;
                color: red;
            }
        </style>
    </head>
    <body>
        <div>
            <h1>Change Password Page</h1>
            <font color="red"><s:property value="executeErr"/></font>
            <s:form method="POST" action="ChangePasswordAction" theme="simple" >
                <label>Username</label><font><s:property value="%{errs.usernameErr}" /></font><br/>
                <s:textfield name="username" value="%{username}"  /><br/>

                <label>Password</label><font><s:property value="%{errs.passwordErr}" /></font><br/> 
                <s:password name="password"  value="%{password}" /><br/>

                <label>New Password</label><font><s:property value="%{errs.newpasswordErr}" /></font><br/> 
                <s:password name="newPassword"  value="%{newPassword}" /><br/>

                <label>Confirm</label><font><s:property value="%{errs.confirmErr}" /></font> <br/>
                <s:password name="confirm"  value="%{confirm}" /><br/>

                <s:submit value="Register"/>

                <span><s:property value="%{errs.incorectUsernameOrPasswordErr}" /></span>
            </s:form>  
        </div>

    </body>
</html>
