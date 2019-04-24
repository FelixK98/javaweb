<%-- 
    Document   : view_cart
    Created on : 16-Dec-2018, 03:56:47
    Author     : MinhKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="update_insert_style.css"/>
        <title>View Page</title>
        <style>
            font{
                display: block;
            }
        </style>

    </head>
    <body>
        <div>
            <s:if test="%{#session.CART != null}">
                <s:if test="%{#session.CART.cart != null}">
                    <s:form action = "RemoveItemsAction" theme="simple">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{#session.CART.cart}" status="counter">
                                    <tr>
                                        <td><s:property value="%{#counter.count}"/></td>
                                        <td><s:property value="%{value.proName}"/></td>
                                        <td><s:property value="%{value.price}"/></td>
                                        <td><s:property value="%{value.quantity}"/></td>
                                        <td><s:checkbox name="selectedItem" fieldValue="%{key}"/></td>
                                    </tr>
                                </s:iterator> 
                                <tr>
                                    <td colspan="3"><s:a href="GetProductAction">Click here to buy more</s:a></td>
                                    <td>Total: <s:property value="%{#session.CART.getTotal()}"/></td>
                                    <td><s:submit value="Remove"/></td>
                                </tr>
                            </tbody>
                        </table>
                    </s:form>
                    <s:form action="OrderAction" theme="simple"> 
                        <s:submit value="Click here to order"/>
                    </s:form> 
                </s:if>

            </s:if>
            <s:if test="%{#session.CART == null}">
                <h1>You haven't add any product<s:a href="GetProductAction">(Add product)</s:a></h1>
           
            </s:if >
            <s:if test="%{#session.CART.cart == null}">
                <h1>There isn't any item in your cart<s:a href="GetProductAction">(Add product)</s:a></h1>
            </s:if>    
            <font><s:property value="%{quantityErr}"/></font>
        </div>


    </body>
</html>
