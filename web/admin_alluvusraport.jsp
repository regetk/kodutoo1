<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administratiivüksuste alluvusraport</title>
    </head>
    <body>
        <h1>Administratiivüksuste alluvusraport!</h1>
        <form name="varskenda" method="get" action="">
            <table>
                <tr>
                    <td>Kuupäev</td>
                    <td>Liik</td>
                    <td></td>
                </tr>
                <tr>
                  <td><input type="date" name="kuupaev" /></td>
                    <td>
                        <select name="ayLiik">
                            <%
                            Object items=request.getAttribute("formData");
                            //String [] arr=(String[])items;
                            Object obs[]=(Object[])items;
                            out.println("Tere"+obs[0].toString());
                            %>
                            <c:forEach var="liik" items="${obs}">
                                <option value='<c:out value="${liik}"></c:out>'>uu</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="submit" value="Värskenda" />  
                    </td>  
                    
                </tr>
            </table>
            
            
            
        </form>
    </body>
</html>
