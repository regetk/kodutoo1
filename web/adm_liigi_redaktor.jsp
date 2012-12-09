<%-- 
    Document   : adm_liigi_redaktor
    Created on : Dec 8, 2012, 9:14:37 PM
    Author     : anneli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administratiivüksuste liigi redaktor</title>
    </head>
    <body>
        <h1>Administratiivüksuste liigi redaktor</h1>

        <table >
            <form method="POST">
                <tr >
                    <td bgcolor="silver">
                        Kood: <input name="ad_yks_liik_kood" value="${param.ad_yks_liik_kood}"><br/>
                        Nimetus: <input name="ad_yks_liik_nim" value="${param.ad_yks_liik_nim}"><br/>
                        Kommentaar: <br/>
                        <textarea name='textAreaName' id='textAreaId'>Kommentaar riigi_admin_yksuse_liik baasist
                        </textarea><br/>
                        Allub: <select name="ad_yks_liik_valik">
                            <c:forEach var="entry" items="${formData.ad_yks_liigid}">
                                <c:set var="selected" value="" />
                                <c:if test="${entry.key == param.ad_yks_liik_id}">
                                    <c:set var="selected" value="selected=\"selected\"" />
                                </c:if>
                                <option value="${entry.key}" ${selected}>${entry.value}</option>
                            </c:forEach> <br/>

                    </td>
                    <td valign="top" bgcolor="#FFCCFF">Alluvad<br/>
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
