<%--
  Created by IntelliJ IDEA.
  User: cbrown
  Date: 2/16/2019
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Body Mass Index Calculator</title>
  </head>
  <body>
  <form name="bmiForm" action="calculateServlet" method="POST">
    <table>
      <tr>
        <td>Your Weight (lbs) :</td>
        <td><input type="text" name="weight"/></td>
      </tr>
      <tr>
        <td>Your Height (in) :</td>
        <td><input type="text" name="height"/></td>
      </tr>
      <th><input type="submit" value="Submit" name="find"/></th>
      <th><input type="reset" value="Reset" name="reset" /></th>
    </table>
    <h2>${bmi}</h2>
  </form>
  </body>
</html>
