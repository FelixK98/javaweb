<%-- 
    Document   : add_product
    Created on : 27-Apr-2019, 08:24:10
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
        <title>Add Product Page</title>
        <script>
            function validateAddProduct() {
                document.getElementById("name").innerHTML = "";
                document.getElementById("des").innerHTML = "";
                document.getElementById("price").innerHTML = "";
                document.getElementById("quantity").innerHTML = "";
                var name = document.forms["productForm"]["name"].value;
                var des = document.forms["productForm"]["des"].value;
                var price = document.forms["productForm"]["price"].value;
                var quantity = document.forms["productForm"]["quantity"].value;
                var rs = true;
                if (name.length === 0 || name.length > 50) {
                    rs = false;
                    if (name.length === 0) {
                        document.getElementById("name").innerHTML = "Please enter Product Name field";
                    } else
                        document.getElementById("name").innerHTML = "Product Name field only accept from 1-50 characters";
                }
                if (des.length === 0 || des.length > 500) {
                    rs = false;
                    if (des.length === 0) {
                        document.getElementById("des").innerHTML = "Please enter Description field";
                    } else
                        document.getElementById("des").innerHTML = "Description field only accept from 1-500 characters";
                }
                if (price.length === 0 || price.match("[^0-9]")) {
                    rs = false;
                    if (price.length === 0) {
                        document.getElementById("price").innerHTML = "Please enter Price field";
                    } else {
                        document.getElementById("price").innerHTML = "Please enter integer value from 1000-1000000";
                    }
                } else {
                    var priceValue = parseInt(price, 10);
                    if (priceValue < 1000 || priceValue > 1000000) {
                        rs = false;
                        document.getElementById("price").innerHTML = "Please enter integer value from 1000-1000000";
                    }
                }
                if (quantity.length === 0 || quantity.match("[^0-9]")) {
                    rs = false;
                    if (quantity.length === 0) {
                        document.getElementById("quantity").innerHTML = "Please enter Quantity field";
                    } else
                        document.getElementById("quantity").innerHTML = "Please enter integer value from 1-10000";
                } else {
                    var quantityValue = parseInt(quantity, 10);
                    if (quantityValue < 1 || quantityValue > 10000) {
                        rs = false;
                        document.getElementById("quantity").innerHTML = "Please enter integer value from 1-10000";
                    }
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
                            <div class="panel-title"><center>Add <s:property value="%{type}"/></center></div>
                        </div>
                        <div class="panel-body">
                            <%--Add Product FORM --%>
                            <form name="productForm" class="form-horizontal"  onsubmit="return validateAddProduct()" method="POST" action="AddProductAction">
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-apple"></i></span> 
                                    <input type="text"  name="name" placeholder="Product Name" class="form-control" value="<s:property value='%{name}'/>">
                                    <font color="red" id="name"><s:property value="%{err}"/></font>
                                </div>
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-info-sign"></i></span>                                     
                                    <textarea name="des" class = "form-control" rows = "3" placeholder="Product Detail"><s:property value="%{des}"/></textarea>
                                    <font color="red" id="des"></font>
                                </div>
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-usd"></i></span> 
                                    <input type="text" name="price" placeholder="Price" class="form-control" value="<s:property value='%{price}'/>">
                                    <font color="red" id="price"></font>
                                </div>
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-sort-by-order"></i></span> 
                                    <input type="text" name="quantity" placeholder="Quantity" class="form-control" value="<s:property value='%{quantity}'/>">
                                    <font color="red" id="quantity"></font>
                                </div>
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-bed"></i></span>                          
                                    <select name="supplier" class="selectpicker form-control" >
                                        <s:iterator  var="supDTO" value="%{#application.SUPPLIERS}">
                                            <option value="<s:property value='%{#supDTO.id}'/>"><s:property value="%{#supDTO.name}"/></option>
                                        </s:iterator>
                                    </select>
                                </div>
                                <div  class="form-group">						                                    
                                    <input type="submit" class="btn btn-warning btn-block" value="Add Product">                                    
                                </div>
                                <input type="hidden" name="search"  value="<s:property value='%{search}'/>"/>
                                <input type="hidden" name="type"  value="<s:property value="%{type}"/>"/>
                            </form>                            
                        </div>
                    </div>
                </div>                
            </div>
            <a href="StartUpAction" ><p class="text-center text-success">Back to Home page</p></a>
        </div>
    </body>
</html>
