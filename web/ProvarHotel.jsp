<%-- 
    Document   : ProvarHotel
    Created on : 21-oct-2015, 22:58:20
    Author     : Rio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Provar HotelWS</h1>
        <form action="HotelServlet" method="POST">
        <table summary="">
            <tr>
            <th>Identificador de l'hotel</th>
            <td><select name=idHotel>
                    <option selected VALUE=1> 1</option>
                    <option VALUE=2>2
                </select>
            </td>
            <br>
            <th>Data</th>
            <td><select name=fecha>
                    <option selected VALUE=6102015> 6102015</option>
                    <option VALUE=7102015>7102015
                </select>
            </td>
            </tr>
        </table>
        <br>
        <br>
        <input name=Provar type=submit value="Provar">
        <h4>Es consultaran les places lliures i es reservara una habitacio.</h4>
        </form>
        <br>
        <h3><a href="index.html">Tornar al menu</a></h3>
    </body>
</html>
