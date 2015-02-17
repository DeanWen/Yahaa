<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="template-top.jsp" />
<!--==============================Content=================================-->
<div class="content">
	<div class="ic"></div>
	<div class="container_12">
        <table>
            <tr>
                <th>Rank</th>
                <th>User</th>
                <th>Likes</th>
            </tr>
            <c:set var="count" value="0" scope="page" />
			<c:forEach var="user" items="${list}">
			<c:set var="count" value="${count + 1}" scope="page"/>
            <tr>
                <td><c:out value="${count}" /></td>      
                <td>${user.getScreen_Name()}</td>
                <td>${user.getLikeGiven()}</td>
            </tr>        
			</c:forEach>
        </table>
	</div>
</div>
<jsp:include page="template-bottom.jsp" />