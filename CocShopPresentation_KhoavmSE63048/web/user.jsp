<%-- 
    Document   : user
    Created on : 12-Dec-2018, 23:35:09
    Author     : MinhKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="search_form.css">
        <title>User Page</title>
        <style>
            a{
                display: block;
                text-align: center;
            }
        </style>
    </head>
    <body>


        <div id="mainDiv">
            <h1>Welcome <s:property value="%{#session.USERNAME}"/><s:a href="LogOutAction">(LogOut)</s:a></h1>
            <s:a href="view_cart.jsp">Click here to view cart</s:a>
            <div id="food">
                <s:form action="AddToCartAction" method="POST" theme="simple">
                    <label>Food Products</label>
                    <select name="product">
                        <s:iterator var="foodProduct" value="%{foodList}">
                            <option><s:property value="%{#foodProduct.proID}"/>-<s:property value="%{#foodProduct.proName}"/>-<s:property value="%{#foodProduct.price}"/></option>
                        </s:iterator>
                    </select>
                    <s:submit value="Add to Cart"/>
                </s:form>
            </div>
            <div id="drink">
                <s:form action="AddToCartAction" metho="POST" theme="simple">
                    <label>Drink Products</label>
                    <select name="product">
                        <s:iterator var="drinkProduct" value="%{drinkList}">
                            <option><s:property value="%{#drinkProduct.proID}"/>-<s:property value="%{#drinkProduct.proName}"/>-<s:property value="%{#drinkProduct.price}"/></option>
                        </s:iterator>
                    </select>
                    <s:submit value="Add to Cart"/>
                </s:form>
            </div>
        </div>




    </body>
</html>
