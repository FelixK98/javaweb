<%-- 
    Document   : sign_up
    Created on : 24-Apr-2019, 16:01:25
    Author     : MinhKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sign_up.css"/>
        <title>Sign Up Page</title>
    </head>
    <script>
        function accountValidate() {
            document.getElementById("username").innerHTML = "";
            document.getElementById("password").innerHTML = "";
            document.getElementById("confirm").innerHTML = "";
            document.getElementById("fullname").innerHTML = "";
            document.getElementById("email").innerHTML = "";
            document.getElementById("phone").innerHTML = "";

            var username = document.forms["accountForm"]["username"].value;
            var password = document.forms["accountForm"]["password"].value;
            var confirm = document.forms["accountForm"]["confirm"].value;
            var fullname = document.forms["accountForm"]["fullname"].value;
            var email = document.forms["accountForm"]["email"].value;
            var phone = document.forms["accountForm"]["phone"].value;
            var rs = true;
            if (username.length === 0 || username.length > 20) {
                rs = false;
                if (username.length === 0) {
                    document.getElementById("username").innerHTML = "Please enter User Name field";
                } else
                    document.getElementById("username").innerHTML = "User Name only accept from 1-20 characters";

            }
            if (password.length === 0 || password.length > 30) {
                rs = false;
                if (password.length === 0) {
                    document.getElementById("password").innerHTML = "Please enter Passwrd field";
                } else
                    document.getElementById("password").innerHTML = "Password only accept from 1-30 characters";
            } else if (!(password === confirm)) {
                rs = false;
                document.getElementById("password").innerHTML = "Password doesn't match Confirm";
            }
            if (fullname.length === 0 || fullname.length > 30) {
                rs = false;
                if (fullname.length === 0) {
                    document.getElementById("fullname").innerHTML = "Please enter Full Name field";
                } else
                    document.getElementById("fullname").innerHTML = "Full Name only accept from 1-30 characters";
            }
            var rex = /\S+[@]\S+[.]\S+/;
            if (email.length === 0 || !rex.test(email)) {
                rs = false;
                if (email.length === 0) {
                    document.getElementById("email").innerHTML = "Please enter Email field";
                } else
                    document.getElementById("email").innerHTML = "Email must be valid *example:abc@xyz.com*";
            }
            if (phone.length === 0 || phone.match("[^0-9]") || (phone.length !== 11 && phone.length !== 10)) {
                rs = false;
                if (phone.length === 0) {
                    document.getElementById("phone").innerHTML = "Please enter Phone field";
                } else if (phone.match("[^0-9]") || (phone.length !== 11 && phone.length !== 10))
                    document.getElementById("phone").innerHTML = "Please enter digit characters contain from 10-11 digits";
            }
            return rs;

        }
    </script>
    <body>
        <div id="signUp" class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-title"><center>Sign Up</center></div>
                        </div>
                        <div class="panel-body">
                            <%--SIGN UP FORM --%>
                            <form class="form-horizontal" name="accountForm" onsubmit="return accountValidate()" method="POST" action="SignUpAction">
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
                                    <input type="text" name="username" placeholder="Username" class="form-control" value="<s:property value="%{username}"/>">
                                    <font color="red" id="username"><s:property value="%{err}"/></font>
                                </div>
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
                                    <input type="password" name="password" placeholder="Password" class="form-control" value="<s:property value="%{password}"/>"/>
                                    <font color="red" id="password"></font>
                                </div>
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-ok-sign"></i></span> 
                                    <input type="password" name="confirm" placeholder="Password Confirmation" class="form-control" value="<s:property value="%{confirm}"/>"/>
                                    <font color="red" id="confirm"></font>
                                </div>
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-sort-by-alphabet"></i></span> 
                                    <input type="text" name="fullname" placeholder="Full Name" class="form-control" value="<s:property value="%{fullname}"/>"/>
                                    <font color="red" id="fullname"></font>
                                </div>
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span> 
                                    <input type="text" name="email" placeholder="Email" class="form-control" value="<s:property value="%{email}"/>"/>
                                    <font color="red" id="email"></font>
                                </div>
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span> 
                                    <input type="text" name="phone" placeholder="Phone Number" class="form-control" value="<s:property value="%{phone}"/>"/>
                                    <font color="red" id="phone"></font>
                                </div>
                                <div  class="form-group">						                                    
                                    <input type="submit" class="btn btn-warning btn-block" value="Regist Your Account"/>                                    
                                </div>
                                <!--COMEBACK-->

                            </form>                            
                        </div>

                    </div>

                </div>                
            </div>
            <a href="StartUpAction" ><p class="text-center text-success">Back to Home page</p></a>
        </div>
    </body>
</html>
