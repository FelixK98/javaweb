<%-- 
    Document   : admin.jsp
    Created on : 24-Apr-2019, 08:47:30
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
        <title>Admin Page</title>
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
                    <li class="active"><a href="GoToAddProductPageAction?type=food" >Add Food</a></li>
                    <li class="active"><a href="GoToAddProductPageAction?type=drink" >Add Drink</a></li>
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
                        <a href="StartUpAction">Accounts</a>
                        <a href="SearchByTypeAction?type=food&role=admin">Food</a>
                        <a href="SearchByTypeAction?type=drink&role=admin">Drink</a>                       
                    </div> 
                    <!-- Search BY NAME-->
                    <!--Food SEARCH TEXT-->
                    <s:if test="%{type == 'food'}">
                        <form class="form-horizontal" method="POST" action="SearchFoodByNameAction">
                            <div class="input-group">
                                <input type="hidden" name="role" value="admin"/>
                                <input type="hidden" name="type" value="food"/>
                                <input type="text" name="search" value="<s:property value='%{SEARCHTXT}'/>"  class="form-control" placeholder="Search By Food Name">
                                <span class="input-group-btn"><button name="btn" class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>                                      </span>  
                            </div>
                        </form>                              
                    </s:if>     
                    <!--Drink SEARCH TEXT--> 
                    <s:elseif test="%{type == 'drink'}">
                        <form class="form-horizontal" method="POST" action="SearchDrinkByNameAction">
                            <div class="input-group">
                                <input type="hidden" name="role" value="admin"/>
                                <input type="hidden" name="type" value="drink"/>
                                <input type="text" name="search" value="<s:property value='%{SEARCHTXT}'/>"  class="form-control" placeholder="Search By Drink Name">
                                <span class="input-group-btn"><button name="btn" class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>                                      </span>  
                            </div>
                        </form>                        
                    </s:elseif>
                    <!--Account SEARCH TEXT--> 
                    <s:else >
                        <form class="form-horizontal" method="GET" action="SearchAccountByNameAction">
                            <div class="input-group">
                                <input type="hidden" name="role" value="admin"/>                                
                                <input type="text" name="search" value="<s:property value='%{search}'/>"  class="form-control" placeholder="Search By UserName">
                                <span class="input-group-btn"><button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>                                      </span>  
                            </div>
                        </form>                        
                    </s:else>
                </div>
                <!--SHOW FOOD TABLE-->
                <s:if test="%{type == 'food'}">
                    <div class="col-md-9">                        
                        <s:set var="list" value="%{searchList}"/>                                                                                                  
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <th>No</th>
                                <th>Food Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Supplier</th>                                
                                <th style="text-align: center;" colspan="2">Action</th>                                
                                </thead>
                                <tbody>
                                    <s:iterator var="dto" value="%{#list}" status="counter"> 
                                        <tr>
                                            <td><s:property value="%{#counter.count}" /></td>
                                            <td><s:property value="%{#dto.name}" /></td>
                                            <td><s:property value="%{#dto.price}" /></td>
                                            <td><s:property value="%{#dto.quantity}" /></td>
                                            <td><s:property value="%{#dto.supplier}" /></td>                                        
                                            <td><form action="EditProductController" method="POST">
                                                    <input type="hidden" name="name" value="<s:property value='%{#dto.name}' />">                                                    
                                                    <input type="hidden" name="search" value="<s:property value='%{search}'/>"/>
                                                    <input type="hidden" name="type" value="food"/>
                                                    <input type="submit" class="btn btn-success btn-block" value="Edit" />
                                                </form>
                                            </td>
                                            <td>
                                                <form action="DeleteProductAction">                                                   
                                                    <input type="hidden" name="name" value="<s:property value='%{#dto.name}'/>"/>
                                                    <input type="hidden" name="type" value="food"/>
                                                    <input type="hidden" name="search" value="<s:property value='%{search}'/>"/>
                                                    <input type="submit" class="btn btn-danger btn-block" value="Delete" />
                                                </form>
                                            </td>
                                        </tr>                                   
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </s:if>
                <!--SHOW DRINK TABLE-->
                <s:elseif test="%{ type == 'drink'}">
                    <div class="col-md-9">                        
                        <s:set var="list" value="%{searchList}"/>                                                                                                  
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <th>No</th>
                                <th>Drink Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Supplier</th>                                
                                <th style="text-align: center;" colspan="2">Action</th>                                
                                </thead>
                                <tbody>
                                    <s:iterator var="dto" value="%{#list}" status="counter"> 
                                        <tr>
                                            <td><s:property value="%{#counter.count}" /></td>
                                            <td><s:property value="%{#dto.name}" /></td>
                                            <td><s:property value="%{#dto.price}" /></td>
                                            <td><s:property value="%{#dto.quantity}" /></td>
                                            <td><s:property value="%{#dto.supplier}" /></td>                                        
                                            <td>
                                                <form action="EditProductController" method="POST">
                                                    <input type="hidden" name="name" value="<s:property value='%{#dto.name}'/>">                                                    
                                                    <input type="hidden" name="search" value="<s:property value='%{search}'/>"/>
                                                    <input type="hidden" name="type" value="drink"/>
                                                    <input type="submit" class="btn btn-success btn-block" value="Edit" />
                                                </form>
                                            </td>
                                            <td>
                                                <form action="DeleteProductAction">                                                    
                                                    <input type="hidden" name="name" value="<s:property value='%{#dto.name}'/>"/>
                                                    <input type="hidden" name="type" value="drink"/>
                                                    <input type="hidden" name="search" value="<s:property value='%{search}'/>"/>
                                                    <input type="submit" class="btn btn-danger btn-block" value="Delete" />
                                                </form>
                                            </td>
                                        </tr>                                   
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </s:elseif>

                <!--SHOW ACCOUNT TABLE-->
                <s:else>
                    <div class="col-md-9">
                        <s:if test="%{search=='' || search==null}">
                            <s:set var="list" value="%{#application.ACCOUNTS}"/>                            
                        </s:if>
                        <s:else>
                            <s:set var="list" value="%{searchList}"/>
                        </s:else>
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <th>No</th>
                                <th>User Name</th>
                                <th>Full Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th style="text-align: center;" colspan="2">Action</th>
                                </thead>
                                <tbody>
                                    <s:iterator var="dto" value="%{#list}" status="counter"> 
                                        <s:if test="%{#dto.role != 'admin'}">
                                            <tr>
                                                <td><s:property value="%{#counter.count}" /></td>
                                                <td><s:property value="%{#dto.username}" /></td>
                                                <td><s:property value="%{#dto.fullname}" /></td>
                                                <td><s:property value="%{#dto.email}" /></td>
                                                <td><s:property value="%{#dto.phone}" /></td>             
                                                <td>
                                                    <form action="EditAccountController">                                                        <input type="hidden" name="role" value="admin"/>
                                                        <input type="hidden" name="name" value="<s:property value='%{#dto.username}'/>"/>
                                                        <input type="hidden" name="search" value="<s:property value="%{search}"/>"/>
                                                        <input type="submit" class="btn btn-success btn-block" value="Edit" />
                                                    </form>
                                                </td>
                                                <td>
                                                    <form action="DeleteAccountAction">
                                                        <input type="hidden" name="name" value="<s:property value='%{#dto.username}'/>"/>
                                                        <input type="hidden" name="search" value="<s:property value='%{search}'/>"/>
                                                        <input type="submit" class="btn btn-danger btn-block" value="Delete" />
                                                    </form>
                                                </td>

                                            </tr>
                                        </s:if>

                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </s:else>



            </div>
        </div>

    </body>
</html>
