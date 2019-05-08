<%-- 
    Document   : login.jsp
    Created on : 22-Apr-2019, 15:58:49
    Author     : MinhKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>
        <title>Login Page</title>
        <script>
            function loginValidate() {
                document.getElementById("username").innerHTML = "";
                document.getElementById("password").innerHTML = "";
                var username = document.forms["loginForm"]["username"].value;
                var password = document.forms["loginForm"]["password"].value;
                var rs = true;
                if (username.length === 0) {
                    rs = false;
                    document.getElementById("username").innerHTML = "Please enter Username field";
                }
                if (password.length === 0) {
                    rs = false;
                    document.getElementById("password").innerHTML = "Please enter Password field";
                }
                return rs;
            }
        </script>
    </head>
    <body>
        <div id="loginForm" class="container">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <!--LOGIN PANEL-->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-title"><center>Sign In</center></div>
                        </div>
                        <div class="panel-body">
                            <%--LOGIN FORM --%>
                            <form class="form-horizontal" name="loginForm" onsubmit="return loginValidate()" method="POST" action="LoginAction">
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
                                    <input type="text" name="username" placeholder="Username" class="form-control" value="">
                                    <font id="username" color="red"></font>
                                </div>
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
                                    <input type="password" name="password" placeholder="Password" class="form-control" value="">
                                    <font id="password" color="red"></font>
                                </div>
                                <div style="margin-bottom: 0px" class="form-group">						                                    
                                    <input type="submit" class="btn btn-warning btn-block" value="Login">                                    
                                </div>
                                    
                            </form>                            
                        </div>

                    </div>

                </div>                
            </div>
                            <a href="StartUpAction"><center style="color: green" class="text-center text-success">Back to Home page</center></a>
            <div class="row">
                <!--SIGN UP SUCCESSFUL NOTICE-->
                <center><font style="font-weight: 700;" color="green"><s:property value="%{successNotify}"/></font></center>
                
                <!--LOGIN FAIL NOTICE-->
                <center><font style="font-weight: 700;" color="RED"><s:property value="%{err}"/></font></center>
            </div>
        </div>


    </body>
</html>
