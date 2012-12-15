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
                  <td><input type="date" name="kuupaev" value="<%= request.getAttribute("kuupaev")%>" /></td>
                    <td>
                        <select name="ayLiik">
                            <%
                            Object items=request.getAttribute("yLiik");
                            String vYksus=(String)request.getAttribute("vYksus");
                            int valitudYksus=0;
                            if(vYksus!=null){
                            valitudYksus=Integer.parseInt(vYksus);
                            }
                            if(items!=null){
                             Object obs[][]=(Object[][])items;
                          //  out.println(valitudYksus);
                           
                            for(int a=0;a<obs.length;a++){
                                String tt=obs[a][0].toString();
                                String it=obs[a][1].toString();
                                //sest andmebaasis algavad id-d 1-st
                                if((valitudYksus-1)==a){
                                  out.println("<option selected value='"+tt+"'>"+it+"</option>");   
                                }else{
                                 out.println("<option value='"+tt+"'>"+it+"</option>");   
                                }
                                
                             }
                           
                           }
                            %>
                            
                                                          
                        </select>
                    </td>
                    <td>
                        <input type="submit" value="Värskenda" />  
                    </td>  
                    
                </tr>
            </table>
            <%
                            Object nimed=request.getAttribute("yksused");
                            if(nimed!=null){
                            Object obs[][]=(Object[][])nimed;
                            for(int a=0;a<obs.length;a++){
                                String tt=obs[a][0].toString();
                                out.println("<table><tr><td>"+tt+"</td>" + "<td><input type='submit' name='nupp"+ a + "'></td></tr></table>");
                             }
                           }
                            %>
                            <table>
                                
                                
                            </table>
            
            
        </form>
    </body>
</html>
