<%-- 
    Document   : admin_alluvusraport
    Created on : Dec 8, 2012, 9:16:11 PM
    Author     : dell
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
Paring p=new Paring();
String sql="SELECT RIIGI_ADMIN_YKSUSE_LIIK_ID, FROM RIIGI_ADMIN_YKSUSE_LIIK ";
Object tulemus[][]=p.SelectParing(sql, new ArrayList());
%>
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
                      
                    </td>
                    <td>
                        
                    </td>  
                    
                </tr>
            </table>
            
            
            
        </form>
    </body>
</html>
