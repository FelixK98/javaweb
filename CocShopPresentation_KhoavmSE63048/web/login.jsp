<%-- 
    Document   : index
    Created on : 12-Dec-2018, 16:48:43
    Author     : MinhKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="update_insert_style.css">

        <style>
            input[type=submit],input[type=text],input[type=password]{               
                width: 100%;
                border-radius: 4px;


            }
            font{
                display: block;
                text-align: center;
            }
            a{
                color: #056833;
                font-size: 15px;
                display: block;
                text-decoration: none;
                text-align: center;
                
            }
            a:hover {
                text-decoration: underline;               
}
span{
    color: #04e8c6;
}
        </style> 

    </head>
    <body>
        <div>
            <h1>Login Page</h1>
            <s:form action="LoginAction" method="POST" theme="simple">
                <label>Username</label><br/>
                <s:textfield name="username" value="%{username}"/> <br/>                 
                <label>Password</label><br/>
                <s:password name="password" value=""/> <br/>                 
                <s:submit name="action" value="Login"/><br/>
                <a href="register.jsp">Register an account</a>
                <a href="password_update.jsp">Change password</a>
                <font>
                <s:property value="%{err}"/><s:property value="%{errs.incorectUsernameOrPasswordErr}"/>
                <span><s:property value="%{registerSuccess}"/><s:property value="%{successChangePassword}"/></span>
                
                </font>
            </s:form>

        </div>
    </body>
</html>
