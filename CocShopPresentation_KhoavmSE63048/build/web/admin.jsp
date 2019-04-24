<%-- 
    Document   : admin
    Created on : 12-Dec-2018, 23:34:57
    Author     : MinhKhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="search_form.css"/>
        <title>Admin Page</title>
    </head>
    <body>

        <div id="mainDiv">
            <h1>
                Welcome <s:property value="%{#session.USERNAME}"></s:property><s:a href="LogOutAction">(Log out)</s:a>
            </h1>
                <div id="accountDiv">

                <%--===================Account information===================  %--%>
                <h1>User Information</h1>
                <s:form method="POST" action="SearchAction" theme="simple">
                    <label>Search by full name</label>
                    <s:textfield name="txtSearch" value="%{txtSearch}"/>                  
                    <s:submit value="Search"/>
                </s:form>

                <s:if test="%{txtSearch != null and txtSearch != ''}">
                    <s:if test="%{accountList != null and accountList.size() > 0}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Username</th>
                                    <th>Fullname</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Role</th>
                                    <th>Delete</th>
                                    <th>Update</th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator  value="%{accountList}" status="counter">
                                    <tr>
                                        <td><s:property value="%{#counter.count}"/></td>
                                        <td><s:property value="username"/></td>
                                        <td><s:property value="fullname"/></td>
                                        <td><s:property value="email"/></td>
                                        <td><s:property value="phone"/></td>
                                        <td><s:property value="role"/></td>
                                        <td>
                                            <s:url var="delete" value="DeleteAction">
                                                <s:param name="txtLastName" value="txtSearch"/>
                                                <s:param name="id" value="username"/>
                                            </s:url>
                                            <s:a href="%{#delete}">Delete</s:a>
                                            </td>
                                            <td>
                                            <s:form method="POST" theme="simple" action="EditAction">
                                                <s:hidden name="id" value="%{username}"/>
                                                <s:hidden name="txtLastName" value="%{txtSearch}"/>
                                                <s:submit value="Edit"/>
                                            </s:form>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>

                    </s:if>
                    <s:if test="%{accountList.size() == 0}">
                        <h1>No result found!!</h1>
                    </s:if>
                </s:if>
            </div>
            <div id="productDiv">
                <%--==================Product information====================== %--%>  
                <h1>
                    Product Information
                    <s:url var="Insert" value="GetSupIdAction"/>
                    <s:a href="%{#Insert}">Click here to insert product</s:a>   
      
                </h1>
                  <h2><s:property value="%{insertSuccess}"/></h2>
                    <s:form method="POST" action="SearchProductAction" theme="simple">
                    <label>Search by product</label>
                    <s:textfield name="txtProductSearch" value="%{txtProductSearch}" />
                    <s:select name="typeSelect" list="{'All','Food','Drink'}"/>            
                    <s:submit value="Search"/>
                </s:form>

                <s:if test="%{txtProductSearch != null and txtProductSearch != ''}">
                    <s:if test="%{searchList != null and searchList.size() > 0}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Product Id</th>
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Description</th>
                                    <s:if test="%{typeSelect == 'All'}"><th>Type</th></s:if>    
                                        <th>Quantity</th>
                                        <th>Sup ID</th>
                                        <th>Delete</th>
                                        <th>Update</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <s:iterator  value="%{searchList}" status="counter">
                                    <tr>
                                        <td><s:property value="%{#counter.count}"/></td>
                                        <td><s:property value="proID"/></td>
                                        <td><s:property value="proName"/></td>
                                        <td><s:property value="price"/></td>
                                        <td><s:property value="description"/></td>
                                        <s:if test="%{typeSelect == 'All'}"><td><s:property value="type"/></td></s:if>    
                                        <td><s:property value="quantity"/></td>
                                        <td><s:property value="supID"/></td>

                                        <td>
                                            <s:url var="delete" value="DeleteProductAction">
                                                <s:param name="txtProductName" value="%{txtProductSearch}"/>
                                                <s:param name="typeSelected"  value="%{typeSelect}"/>
                                                <s:param name="proId" value="%{proID}"/>
                                            </s:url>
                                            <s:a href="%{#delete}">Delete</s:a>
                                            </td>
                                            <td>
                                            <s:form method="POST" theme="simple" action="EditProductAction">
                                                <s:hidden name="txtProductName" value="%{txtProductSearch}"/>
                                                <s:hidden name="typeSelected" value="%{typeSelect}"/>
                                                <s:hidden name="proId" value="%{proID}"/>
                                                <s:submit value="Edit"/>
                                            </s:form>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>

                    </s:if>
                    <s:if test="%{searchList.size() == 0}">
                        <h1>No result found!!</h1>
                    </s:if>
                </s:if>
                        
            </div>

        </div>

    </body>
</html>
