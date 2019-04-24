<%-- 
    Document   : register.jsp
    Created on : 15-Dec-2018, 16:23:54
    Author     : MinhKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="update_insert_style.css">
        <style>
            input[type=submit],input[type=text],input[type=password]{               
                width: 450px;
                border-radius: 4px;                                
            }

        </style>
        <title>Register Page</title>
    </head>
    <body>
        <div>
            <h1>Register Page</h1>
            <font color="red"><s:property value="executeErr"/></font>
            <s:form method="POST" action="RegisterAction" theme="simple" >
                <label>Username</label><font><s:property value="%{errs.usernameErr}" /></font><br/>
                <s:textfield name="username" value="%{dto.username}"  /><br/>

                <label>Password</label><font><s:property value="%{errs.passwordErr}" /></font><br/> 
                <s:password name="password"  value="%{dto.password}" /><br/>

                <label>Confirm</label><br/> 
                <s:password name="confirm"  value="%{confirm}" /><br/>

                <label>Fullname</label><font><s:property value="%{errs.fullnameErr}" /></font><br/> 
                <s:textfield name="fullname"  value="%{dto.fullname}" /><br/>

                <label>Email</label><font><s:property value="%{errs.emailErr}"/><s:property value="%{errs.duplicteEmailErr}"/></font><br/>
                <s:textfield name="email" value="%{dto.email}" label="Email"/><br/>

                <label>Phone</label><font><s:property value="%{errs.phoneErr}"/><s:property value="%{errs.duplicatePhoneErr}"/></font><br/>
                <s:textfield name="phone" value="%{dto.phone}" label="Phone"/><br/>               
                <s:submit value="Register"/>
            </s:form>  
        </div>

    </body>
</html>
