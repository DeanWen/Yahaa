<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="template-top.jsp" />
<!--==============================Content=================================-->
    <div class="content"><div class="ic">More Website Templates @ TemplateMonster.com - December 30, 2013!</div>
        <div class="container_12">
            
            <c:forEach var="tweet" items="${timeline}">
            <c:choose>
            <c:when test="${fn:length(tweet) mod 2 eq 0}" >
            <div class="grid_6">
                <div class="p1_box left cl2">
                    <blockquote>${tweet}</blockquote>
                    <a href="#" class="bot">Elliott Erwitt <span>8 <br>comments</span></a>
                </div>
            </div>
            </c:when>
            <c:otherwise>
            <div class="grid_6">
                <div class="p1_box right cl5">
                    <blockquote>${tweet}</blockquote>
                    <a href="#" class="bot">Elliott Erwitt <span>8 <br>comments</span></a>
                </div>
            </div>
            </c:otherwise>
            </c:choose>         
            </c:forEach>

            <div class="clear"></div>
            <div class="grid_12">
                <a href="#" class="round"> Older Posts</a>
            </div>
        </div>
    </div>
<jsp:include page="template-bottom.jsp" />