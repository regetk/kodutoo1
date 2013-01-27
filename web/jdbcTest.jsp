<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <html>
  <head>

    <sql:query var="liigid" dataSource="hsqldb" >
       select * from RIIGI_ADMIN_YKSUSE_LIIK
    </sql:query>

  </head>
  <body>
    <h1>JDBC JNDI Resource Test</h1>
    <table width='600' border='1'> 
      <tr>
        <th align='left'>Kood</th>
        <th align='left'>Nimi</th>
        </tr>
      <c:forEach var="liik" items="${liigid.rows}">
        <tr>
           <td> ${liik.kood}</td>
           <td> ${liik.nimetus} </td>
         </tr>
      </c:forEach>
    </table>
  </body>
  </html>