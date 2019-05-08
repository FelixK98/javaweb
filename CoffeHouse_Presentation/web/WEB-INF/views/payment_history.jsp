<%-- 
    Document   : payment_history
    Created on : 29-Apr-2019, 22:16:55
    Author     : MinhKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>

        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
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
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <s:if test="%{orderList.size() == 0}">
                        <h1 class="text-center text-success">You haven't purchased any product</h1>
                    </s:if>
                    <s:else>
                        <form action="RemoveCartItemActon" method="POST">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <th>No</th>
                                    <th>Product Name</th>                                    
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Date</th>                                
                                    </thead>
                                    <tbody>
                                        <s:iterator var="dto" value="%{orderList}" status="counter">                                        
                                            <tr>
                                                <td><s:property value="%{#counter.count}" /></td>
                                                <td><s:property value="%{#dto.name}" /></td>
                                                <td><s:property value="%{#dto.quantity}" /></td>
                                                <td><s:property value="%{#dto.price}" /></td>
                                                <td><s:property value="%{#dto.date}"/></td>                                                
                                            </tr>                                       
                                        </s:iterator>                                    
                                    </tbody>
                                </table>
                            </div>
                        </form>
                    </s:else>                    
                </div>

            </div>                    
        </div>
    </body>
</html>

