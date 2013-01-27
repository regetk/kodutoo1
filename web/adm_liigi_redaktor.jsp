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
                        Kood: <input name="ay_liik_kood" value="<%
                            Object oKood = request.getAttribute("kood");
                            if (oKood == null) {
                                out.println("");
                            } else {
                                out.println(oKood.toString());
                            }


                                     %>"><br/>
                        Nimetus: <input name="ay_liik_nimi" value="<%
                            Object oNimi = request.getAttribute("nimi");
                            if (oNimi == null) {
                                out.println("");
                            } else {
                                out.println(oNimi.toString());
                            }


                                        %>"><br/>
                        Kommentaar: <br/>
                        <textarea name='ay_liik_komm' id='textAreaId'><%
                            Object oKomm = request.getAttribute("komm");
                            if (oNimi == null) {
                                out.println("");
                            } else {
                                out.println(oKomm.toString());
                            }


                            %>
                        </textarea><br/>
                        Allub: <select name="ay_liik_ylemus">
                            
                            <%
                                Object oYlemId = request.getAttribute("ylem_id");
                                if(oYlemId==null)out.println("<option value='0'></option>");
                                Object items = request.getAttribute("vormiAndmed");
                                Object obs[][] = (Object[][]) items;
                                if (oYlemId == null) {
                                    
                                    if (items != null) {

                                        for (int a = 0; a < obs.length; a++) {
                                            String tt = obs[a][0].toString();
                                            String it = obs[a][1].toString();
                                            out.println("<option value='" + tt + "'>" + it + "</option>");
                                        }
                                    } 
                                }
                                else {
                                        int i = Integer.parseInt((oYlemId.toString()));
                                        String tt = obs[i-1][0].toString();
                                        String it = obs[i-1][1].toString();
                                        out.println("<option value='" + tt + "'>" + it + "</option>");
                                    }
                            %>

                        </select>
                        <br/>

                    </td>
                    <td valign="top" bgcolor="silver">Alluvad<br/>
                        <select name="ay_liik_alluv">
                            <option value='0'></option>
                            <%
                                Object items2 = request.getAttribute("allumatud");

                                if (items2 != null) {

                                    Object obs2[][] = (Object[][]) items2;
                                    //out.print(items);

                                    for (int a = 0; a < obs2.length; a++) {
                                        String tt = obs2[a][0].toString();
                                        String it = obs2[a][1].toString();
                                        out.println("<option value='" + tt + "'>" + it + "</option>");
                                    }
                                }
                            %>

                        </select>
                        <input type="submit" name="lisa_alluv_listi" value="Lisa">

                    </td>

                <tr  bgcolor="#99CCFF" >

                    <td colspan="2" align="right"><input type="submit" name="save_ay_liik" value="Salvesta"><input type="submit" name="cancel_ad_liik" value="Loobu"></td>
                </tr>
            </form>
        </table>
    </body>
</html>