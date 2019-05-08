<%-- 
    Document   : user_update
    Created on : 26-Apr-2019, 11:46:02
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
        <title>Update Page</title>
        <script>
            function accountUpdateValidate() {
               
                document.getElementById("fullname").innerHTML = "";
                document.getElementById("email").innerHTML = "";
                document.getElementById("phone").innerHTML = "";
//            var password = document.forms["editForm"]["password"].value;       
                var fullname = document.forms["editForm"]["fullname"].value;
                var email = document.forms["editForm"]["email"].value;
                var phone = document.forms["editForm"]["phone"].value;
                var rs = true;
//            if (password.length === 0 || password.length > 30) {
//                rs = false;
//                if (password.length === 0) {
//                    document.getElementById("password").innerHTML = "Please enter Password field";
//                } else
//                    document.getElementById("password").innerHTML = "Password only accept from 1-30 characters";
//            }
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
    </head>
    <body>
        <div id="signUp" class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-title"><center>Edit</center></div>
                        </div>
                        <div class="panel-body">
                            <%--UPDATE FORM --%>
                            <form class="form-horizontal" name="editForm" onsubmit="return accountUpdateValidate()" method="POST" action="UpdateUserController">
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
                                    <input type="text" readonly name="username" placeholder="Username" class="form-control" value="<s:property value='%{dto.username}'/>"/>
                                </div>
                                <s:if test="%{ role == 'user'}">
                                    <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
                                        <input type="password" name="password" placeholder="Password" class="form-control" value="<s:property value='%{dto.password}' />"/>
                                        <font id="password" color="red"></font>
                                    </div> 
                                </s:if>
                                <s:else>
                                    <input type="hidden" name="password" value="<s:property value='%{dto.password}'/>"/>                                    
                                </s:else> 
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-sort-by-alphabet"></i></span> 
                                    <input type="text" name="fullname" placeholder="Full Name" class="form-control" value="<s:property value='%{dto.fullname}' />"/>
                                    <font id="fullname" color="red"></font>
                                </div>
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span> 
                                    <input type="text" name="email" placeholder="Email" class="form-control" value="<s:property value='%{dto.email}'/>"/>
                                    <font id="email" color="red"></font>
                                </div>
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span> 
                                    <input type="text" name="phone" placeholder="Phone Number" class="form-control" value="<s:property value='%{dto.phone}'/>"/>
                                    <font id="phone" color="red"></font>
                                </div>
                                <div  class="form-group">						                                    
                                    <input type="submit" class="btn btn-warning btn-block" value="Update User">                                    
                                </div>
                                <input type="hidden" name="search"  value="<s:property value='%{search}'/>"/>
                                <input type="hidden" name="role"  value="<s:property value='%{#session.ROLE}'/>"/>
                            </form>                            
                        </div>
                    </div>
                </div>                
            </div>
        </div>
    </body>
</html>
