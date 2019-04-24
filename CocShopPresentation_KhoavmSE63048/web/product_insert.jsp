<%-- 
    Document   : product_insert
    Created on : 15-Dec-2018, 11:21:48
    Author     : MinhKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="update_insert_style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Insert Page</title>
        <style>
       
                input[type=submit],input[type=text],input[type=password],select{               
                    width: 550px;
                    border-radius: 4px;
                }
            
        </style>
    </head>
    <body>
        <div>
            <h1>Insert Product Page</h1>
            <font color="red"><s:property value="%{errs.executeErr}"/></font>
            <s:form method="POST" action="InsertAction" theme="simple" >

                <label>Product ID</label><font><s:property value="%{errs.proIdErr}"/></font><br/>
                <s:textfield name="proId" value="%{dto.proID}" /><br/>

                <label>Product Name</label><font><s:property value="%{errs.proNameErr}"/></font><br/>
                <s:textfield name="proName" value="%{dto.proName}" /><br/>

                <label>Price(VND)</label><font><s:property value="%{errs.priceErr}" /></font><br/> 
                <s:textfield name="price"  value="%{dto.price}" /><br/>

                <label>Description</label><font><s:property value="%{errs.descriptionErr}"/><br/>
                <s:textfield name="description" value="%{dto.description}" label="Email"/><br/>

                <label>Type</label><br/>           
                <select name="type">
                    <s:iterator var="foodType" value="%{typeList}">
                        <option><s:property value="%{#foodType}"/></option>
                    </s:iterator>
                </select><br/>

                <label>Quantity</label><font><s:property value="%{errs.quantityError}"/></font><br/>
                <s:textfield name="quantity" value="%{dto.quantity}" label="Phone"/><br/>

                <label>Supplier ID</label><font><br/>            
                <select name="supId">
                    <s:iterator var="sup" value="%{supList}">
                        <option><s:property value="%{#sup}"/></option>
                    </s:iterator>
                </select><br/>                       
                <s:submit value="Insert"/>
            </s:form>
        </div>

    </body>
</html>