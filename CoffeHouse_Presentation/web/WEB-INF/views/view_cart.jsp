<%-- 
    Document   : view_cart
    Created on : 28-Apr-2019, 14:40:23
    Author     : MinhKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>

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

                <div class="col-md-9">
                    <s:if test="%{#session.CART == null || #session.CART.cart.size() == 0}">
                        <h1 class="text-center text-success">Your cart is empty</h1>
                    </s:if>
                    <s:else>
                        <form action="RemoveCartItemActon" method="POST">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <th>No</th>
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Action</th>                                
                                    </thead>
                                    <tbody>

                                        <s:iterator value="%{#session.CART.cart}" status="counter">                                        
                                            <tr>
                                                <td><s:property value="%{#counter.count}" /></td>
                                                <td><s:property value="%{value.name}" /></td>
                                                <td><s:property value="%{value.price}" /></td>
                                                <td><s:property value="%{value.quantity}" /></td>
                                                <td> <input type="checkbox"  name="removeItem" value="<s:property value='%{value.name}'/>"/> </td>                                                        
                                            </tr>                                       
                                        </s:iterator>
                                        <s:url var="orderAction" value="OrderProductAction" escapeAmp="false">
                                            <s:param name="disCountPrice" value="%{disCountPrice}"/>
                                            <s:param name="code" value="%{code}"/>
                                        </s:url>
                                    <td colspan="4"><a href="<s:property value='%{orderAction}'/>" class="btn btn-success btn-block">Click here to Order</a></td>

                                    <td><input type="submit" class="btn btn-danger btn-block" value="Remove"/></td>

                                    </tbody>
                                </table>
                            </div>
                            <input type="hidden" name="disCountPrice" value="<s:property value='%{disCountPrice}'/>"/>
                            <input type="hidden" name="code" value="<s:property value='%{code}'/>"/>
                        </form>
                    </s:else>
                    <center><font color="red" ><s:property  value="%{err}"/></font></center>

                </div>
                <div class="col-md-3">
                    <s:if test="%{#request.isCodeAvailable == null}">
                        <div class="col-md-12">
                            <div class="well well-lg" style="font-size: 20px">
                                <form class="form-horizontal" method="POST" action="CheckDiscountCodeAction">
                                    <div class="input-group">                                   
                                        <input type="text" name="code" value="<s:property value='%{code}'/>"  class="form-control" placeholder="Discount Code">                                        
                                        <span class="input-group-btn"><button name="btn" class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button></span>                                          
                                    </div>
                                    <font style="font-size: 14px;" color="red"><s:property value="codeChk"/><font>
                                </form>
                            </div>
                        </div>
                        <div class="col-md-12">                           
                            <div class="well well-lg text-warning text-center" style="font-size: 20px"><strong>Total: <s:property value="%{#session.CART.computeTotalPayment()}"/> VNĐ</strong></div>
                        </div>
                    </s:if>
                    <s:else>
                        <div class="col-md-12">
                            <div class="well well-lg" style="font-size: 20px">
                                <form class="form-horizontal" method="POST" action="CheckDiscountCodeAction">
                                    <div class="input-group">                                   
                                        <input type="text" name="code" value="<s:property value='%{code}'/>"  class="form-control" placeholder="Discount Code">                                        
                                        <span class="input-group-btn"><button name="btn" class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button></span>                                          
                                    </div>
                                    <font style="font-size: 14px;" color="green"><s:property value="codeChk"/><font>
                                </form>
                                
                            </div>
                        </div>
                        <div class="col-md-12">                           
                            <div class="well well-lg text-success text-center" style="font-size: 20px"><strong>Total: <del class="text-warning"><s:property value="%{#session.CART.computeTotalPayment()}"/></del> <s:property value="%{disCountPrice}"/> VNĐ</strong></div>
                        </div>
                    </s:else>






                </div>

            </div>                    
        </div>
    </body>
</html>
