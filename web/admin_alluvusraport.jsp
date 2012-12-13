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
                            Object read[]=(Object[])items;
                            out.print(items);
                            /*
                            for(int a=0;a<obs.length;a++){
                                String tt=obs[a][0].toString();
                                String it=obs[a][1].toString();
                                out.println("<option value='"+it+"'>"+tt+"</option>");
                             }
                            * */
                            %>
                            
                                <option value=''>uu</option>
                           
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
