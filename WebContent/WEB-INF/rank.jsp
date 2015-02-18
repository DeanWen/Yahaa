<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="databeans.UserBean"%>
<jsp:include page="template-top.jsp" />
<!--==============================Content=================================-->
<div class="content">
	<div class="ic"></div>
	<div class="container_12">
        <div class="grid_6">
        <br>
        <br>
        <table>
            <thead>
            <tr>
                <th>Rank</th>
                <th>User</th>
                <th>Likes</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="count" value="0" scope="page" />
            <c:forEach var="user" items="${list}">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <tr>
                <td><c:out value="${count}" /></td>      
                <td>${user.getScreen_Name()}</td>
                <td>${user.getLikeGiven()}</td>
            </tr>        
            </c:forEach>
            </tbody>
        </table>
	        <c:choose>
	        <c:when test = "${not empty user}">
	            <form action="sendTweet.do" method="POST">
	                <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspShare with friends: <input type="text" name="text" size="40" style="padding: 2px; margin 2px;" value= "I have given ${user.getLikeGiven()} likes at Yahaa. Come join me!" required = "required"><br></p>
	                <button type="submit" style="border: 0; background: transparent; cursor:pointer;">
	                    <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img src="images/Twitter-Button.png" width="80" height="25" alt="submit" /></p>
	                </button>
	            </form>
	        </c:when>
	        </c:choose>
        </div>
        <div class="grid_6">
            <div id="rank_div" style="width: 500px; height: 400px"></div>
        </div>
	</div>
</div>

<script type="text/javascript" src="https://www.google.com/jsapi"?autoload={
            'modules':[{
              'name':'visualization',
              'version':'1.1',
              'packages':['corechart']
            }]
          }"></script>
 <script type="text/javascript">
 		google.load('visualization', '1.1', {packages: ['corechart']});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
	      var data = new google.visualization.DataTable();
	      var jsArr = new Array();  
	  	  var jsArr2 = new Array();
	  	    
	  	    <%
	  	      UserBean[] list = (UserBean[])request.getAttribute("list");
		  	    for (int i=0; i < list.length; i++) {  
		  	    %>  
	  	    jsArr[<%= i %>] = '<%=list[i].getScreen_Name()%>'; 
	  	    <%}%>
	  	    <%  
		  	    for (int i=0; i < list.length; i++) {  
		  	    %>  
		  	  jsArr2[<%= i %>] = '<%=list[i].getLikeGiven()%>'; 
		  	    <%}%>
	    	  data.addColumn('string','Name');
	    	  data.addColumn('number', 'Count');
	    	  data.addRows(jsArr.length);
	    	    <%  
		    	    for (int i=0; i < list.length; i++) {
		    	    	
		    	    %>
	    	    data.setValue(<%= i %>,0, jsArr[<%= i %>]);
	    	    data.setValue(<%= i %>,1, parseInt(jsArr2[<%= i %>]));
	    	   
	    	    <%
		    	    }
		    	  %>

	    	  
	        var options = {
	          title: 'User Rank',
	          hAxis: {title: 'Like Count',minValue:0},
	          vAxis: {minValue: 0},
	          colors: ['#5AB8E3']
	        };
	
	        var chart = new google.visualization.BarChart(document.getElementById('rank_div'));
	        chart.draw(data, options);
      }
  </script>
<jsp:include page="template-bottom.jsp" />