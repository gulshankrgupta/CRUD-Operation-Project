<%@ page isELIgnored="false"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<h1 style="color:red;text-align:center">Report Page</h1>

<c:choose>
  <c:when test="${!empty empsList}">
      <table  border="1"  align="center"  bgcolor="yellow">
        <tr><th>empno </th><th>ename </th><th>job </th><th> sal</th><th>deptno</th><th>operations</th>    </tr>
     
    <c:forEach var="emp"  items="${empsList}">
     <tr>
        <td>${emp.empno} </td>
        <td>${emp.ename} </td>
        <td>${emp.job} </td>
        <td>${emp.sal} </td>
        <td>${emp.deptno} </td>
        <td><a href="edit?id=${emp.empno}"><img src="images/edit.png" width='25px' height='25px'></a> 
           &nbsp;&nbsp;
           <a href="delete?id=${emp.empno}" onclick="return confirm('are u sure to delete employee??')"><img src="images/delete.png" width='25px' height='25px'> </a>
         </td>
        
     </tr>
    </c:forEach>
    
     </table>
  </c:when>
   <c:otherwise>
             <h1 style="color:red;text-align:center">No Records found</h1>
   </c:otherwise>
</c:choose>
   <center><h1  style="color:maroon">${resultMsg}  </h1>

<br><br>
 <h1  style="text-align:center"> <a href="./">home <img src='images/home.jpg' width='50px' height='50px'></a>  </h1>
 <br><br>
 <h1  style="text-align:center"> <a href="register">Register Employee <img src='images/add.jpg' width='50px' height='50px'></a>  </h1>
 