<%-- 
    Document   : order_success
    Created on : 29-Apr-2019, 13:39:08
    Author     : MinhKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
        <title>Order Success Page</title>
    </head>
    <body>
         <!--HEADER IMAGE-->
        <s:include value="head_image.jsp"/>
        <br/>

        <!--NAV BAR-->
        <div class="container">
            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <a class="navbar-brand" href="StartUpAction">TheCoffeeHouse</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="StartUpAction">Home</a></li>
                    <li><a href="GoToPaymentHistoryPageAction"><span class="glyphicon glyphicon-calendar"></span> History</a></li>                    
                </ul>
                <ul class="nav navbar-nav navbar-right">                    
                    <li><a href="LogOutAction"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                    <li><a href="StartUpAction"><span class="glyphicon glyphicon-user"></span> Hi, <s:property value="%{#session.USER}"/></a></li>
                </ul>
            </nav>
        </div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-4"></div>
                        <div class="col-lg-4">
                            <img class="img-responsive" src="${pageContext.request.contextPath}/img/success_order.jpg"/>
                        </div>
                    </div>
                </div>
    </body>
</html>
