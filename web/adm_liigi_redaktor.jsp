<%-- 
    Document   : adm_liigi_redaktor
    Created on : Dec 8, 2012, 9:14:37 PM
    Author     : anneli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administratiivüksuste liigi redaktor</title>
    </head>
    <body>
        <h1>Administratiivüksuste liigi redaktor</h1>
        <!--veateated-->
    <c:if test="${not empty errors}">
        <div style="color:red">
            <c:forEach var="error" items="${errors}">
                <c:out value="${error}"></c:out><br />
            </c:forEach>
        </div>
        <br/><br/>
    </c:if>

    <table >
        <form method="POST">
            <tr >
                <td bgcolor="silver">
                    Kood: <input name="ay_liik_kood" value=""><br/>
                    Nimetus: <input name="ay_liik_nimi" value=""><br/>
                    Kommentaar: <br/>
                    <textarea name='ay_liik_komm' id='textAreaId'>Kommentaar 
                    </textarea><br/>
                    Allub: <select name="ay_liik_ylemus"
                                   <%
                                       Object items = request.getAttribute("vormiAndmed");
                                       if (items != null) {
                                           //String [] arr=(String[])items;
                                           Object obs[][] = (Object[][]) items;
                                           //out.print(items);

                                           for (int a = 0; a < obs.length; a++) {
                                               String tt = obs[a][0].toString();
                                               String it = obs[a][1].toString();
                                               out.println("<option value='" + tt + "'>" + it + "</option>");
                                           }
                                       }
                                   %>
                                   <option value=''>uu</option>
                    </select>
                    <br/>

                </td>
                <td valign="top" bgcolor=""silver">Alluvad<br/>
                    <input type=TEXT name="muutuja_allujate_kollektsioonist" value="Allujate_kollekts_isend" > <!-- <//%=textFields[j]%>  -->
                    <input type="submit" name="save_ad_liik" value="Eemalda">
                   
                </td>

            <tr  bgcolor="#99CCFF" >

                <td colspan="2" align="right"><input type="submit" name="save_ad_liik" value="Salvesta"><input type="submit" name="cancel_ad_liik" value="Loobu"></td>
            </tr>
        </form>
    </table>
</body>
</html>
