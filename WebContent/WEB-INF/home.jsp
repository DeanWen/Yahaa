<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="template-top.jsp" />
<!--==============================Content=================================-->
<div class="content">
	<div class="ic"></div>
	<div class="container_12">
		<div class="grid_6">
			<c:forEach var="tweet" items="${timeline}">
				<c:choose>
					<c:when test="${fn:length(tweet.getContent()) mod 2 eq 0}">

						<div class="p1_box left cl2">
							<blockquote>${tweet.getContent()}</blockquote>
							<form action="likeTweet.do" method="POST" name="likeTweet_form">
							<input type="hidden" name="id" value="${tweet.getId()}"/>
							<a class="bot"><span>
							<c:choose>
                                <c:when test="${tweet.getFavorited()}">
                                    <em class = "icon-heart"><input class ="heart" type="submit" name="button" value ="Unlike"/></em> 
                                </c:when> 
                                <c:otherwise>
                                    <i class = "icon-heart"><input class ="heart" type="submit" name="button" value ="Like"/></i>
                                </c:otherwise> 
                            </c:choose>                           
                            </span></a>							
							</form>
						</div>

					</c:when>
					<c:otherwise>
						<div class="p1_box left cl5">
							<blockquote>${tweet.getContent()}</blockquote>
							<form action="likeTweet.do" method="POST" name="likeTweet_form">
							<input type="hidden" name="id" value="${tweet.getId()}"/>
                            <a class="bot"><span>
                            <c:choose>
                                <c:when test="${tweet.getFavorited()}">
                                    <em class = "icon-heart"><input class ="heart" type="submit" name="button" value ="Unlike"/></em> 
                                </c:when> 
                                <c:otherwise>
                                    <i class = "icon-heart"><input class ="heart" type="submit" name="button" value ="Like"/></i>
                                </c:otherwise> 
                            </c:choose>                      
                            </span></a>
							</form>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<div class="grid_6">
            <c:forEach var="photo" items="${photos}">
                <div class="p1_box right cl3 pos1">
                    <img src="${photo.getUrl() }" alt="">

                    <form action="likeFlickr.do" method="POST" name="likeFlickr_form">
							<input type="hidden" name="id" value="${photo.getId()}"/>
                            <a class="bot"><span>
                            <c:choose>
                                <c:when test="${photo.getFavorited()}">
                                    <em class = "icon-heart"><input class ="heart" type="submit" name="button" value ="Unlike"/></em> 
                                </c:when> 
                                <c:otherwise>
                                    <i class = "icon-heart"><input class ="heart" type="submit" name="button" value ="Like"/></i>
                                </c:otherwise> 
                            </c:choose>
                            </span></a>
					</form>

                </div>
            </c:forEach>
        </div>
		<div class="clear"></div>
		<div class="grid_12">
			<a href="#" class="round"> Back to Top</a>
		</div>
	</div>
</div>
<jsp:include page="template-bottom.jsp" />