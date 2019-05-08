<%-- 
    Document   : user.jsp
    Created on : 24-Apr-2019, 08:47:49
    Author     : MinhKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index_css.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vertical_menu.css" />
        <title>User Page</title>
        <script>
            function addNotice() {
                alert("ADD SUCCESSFUL!");
            }
        </script>
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
                    <s:if test="%{#session.CART == null || #session.CART.cart.size() == 0}">
                        <li><a href="GoToViewCartPageAction"><span class="glyphicon glyphicon-shopping-cart"></span><font color="#a39b00">(0)</font> View Cart</a></li>
                    </s:if>
                    <s:else>
                        <li><a href="GoToViewCartPageAction"><span class="glyphicon glyphicon-shopping-cart"></span>(<font color="#a39b00"><s:property value="%{#session.CART.cart.size()}"/></font>) View Cart</a></li>  
                    </s:else>

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

                <div class="col-md-3">
                    <!--        Side bar nav-->
                    <div class="vertical-menu">
                        <a href="#" class="active">Menu</a>
                        <a href="StartUpAction">All Products</a>
                        <a href="SearchByTypeAction?type=food&role=user">Food</a>
                        <a href="SearchByTypeAction?type=drink&role=user">Drink</a>                       
                    </div> 
                    <!-- Search BY NAME-->
                    <form class="form-horizontal" method="POST" action="SearchProductByNameAction">
                        <div class="input-group">
                            <input type="hidden" name="role" value="user"/>
                            <input type="text" name="search" value="<s:property value='%{#request.SEARCHTXT}'/>"  class="form-control" placeholder="Search By Name">
                            <span class="input-group-btn"><button name="btn" class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>                                      </span>  
                        </div>
                    </form>

                </div>
                <div class="col-md-9">
                    <s:if test="%{type == 'food' || type == 'drink' || #request.SEARCHTXT != null}">
                        <s:set var="productList" value="%{searchList}"/>
                    </s:if>
                    <s:else>
                        <s:set var="productList" value="%{#application.PRODUCTS}"/>
                    </s:else>
                    <s:iterator var="product" value="%{#productList}">
                        <div class="col-md-4 products">
                            <s:url id="imgPath" value="/%{#product.img}"/>                    
                            <img style="height: 280px;" class="img-responsive thumbnail" src="<s:property value='imgPath'/>"/>                                                          
                            <div class="caption">
                                <h5 class="product_title"><s:property value="{#product.name}"/></h5>
                                <h3 class="product_price"><s:property value="{#product.price}"/> VNĐ</h3>
                                <s:url var="addToCart" value="AddToCartAction" escapeAmp="false">                                    
                                    <s:param name="name" value="%{#product.name}"></s:param>  
                                    <s:param name="price" value="%{#product.price}"></s:param>                                    
                                </s:url>
                                <a href="<s:property value='%{#addToCart}'/>" class="btn btn-warning btn-lg" onclick="addNotice()">Add to Cart</a>
                            </div>
                        </div>
                    </s:iterator>
                </div>

            </div>
        </div>

    </body>
</html>
