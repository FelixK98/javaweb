<%-- 
    Document   : index
    Created on : 22-Apr-2019, 14:21:24
    Author     : MinhKhoa
--%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index_css.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vertical_menu.css" />
        <title>Index Page</title>
    </head>
    <body>       
        <s:include value="head_image.jsp"/>
        <br/>
        <!--NAV BAR-->
        <div class="container">
            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <a class="navbar-brand" href="StartUpAction">TheCoffeeHouse</a>
                </div>

<!--                <ul class="nav navbar-nav">
                    <li class="active"><a href="StartUpAction">Home</a></li>                    
                </ul>-->

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="GoToRegisterPageAction"><span class="glyphicon glyphicon-user"></span> Sign up</a></li>
                    <li><a href="GoToLoginPageAction"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    <li><a href="#"></a></li>
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
                        <a href="SearchByTypeAction?type=food&role=guest">Food</a>
                        <a href="SearchByTypeAction?type=drink&role=guest">Drink</a>                       
                    </div> 
                    <!-- Search BY NAME-->
                    <s:form theme="simple" class="form-horizontal" method="POST" action="SearchProductByNameAction">
                        <div class="input-group">
                            <input type="hidden" name="role" value="guest"/>
                            <input  type="text" name="search" value="<s:property value='%{#request.SEARCHTXT}'/>"  class="form-control" placeholder="Search By Name"/>
                            <span class="input-group-btn"><button name="btn"  class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>                                      </span>  
                        </div>
                    </s:form>

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
                                <a href="GoToLoginPageAction" class="btn btn-warning btn-lg">Buy Now</a>
                            </div>
                        </div>
                    </s:iterator>
                </div>

            </div>
        </div>

    </body>
</html>
