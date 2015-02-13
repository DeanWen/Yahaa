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
							<form action="likeTweet.do" method="POST" id="likeTweet_form">
							<input type="hidden" name="id" value="${tweet.getId()}"/>
							<a href="javascript:{}" onclick="document.getElementById('my_form').submit(); return false;" class="bot"> <span>${tweet.getLikeCount()}
									<br>Likes
							</span></a>
							</form>
						</div>

					</c:when>
					<c:otherwise>
						<div class="p1_box left cl5">
							<blockquote>${tweet.getContent()}</blockquote>
							<form action="likeTweet.do" method="POST" id="likeTweet_form">
							<input type="hidden" name="id" value="${tweet.getId()}"/>
							<a href="javascript:{}" onclick="document.getElementById('my_form').submit(); return false;" class="bot"> <span>${tweet.getLikeCount()}
									<br>Likes
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
                    <div class="type"></div>
                    <img src="${photo.getUrl() }" alt="">
                    <a class="bot">"${photo.getTitle()}"<span><br>${photo.getLikeCount()}
                    	<br>Likes
                    </span></a>
                </div>
            </c:forEach>
        </div>
		<div class="clear"></div>
		<div class="grid_12">
			<a href="#" class="round"> Older Posts</a>
		</div>
	</div>
</div>
<jsp:include page="template-bottom.jsp" />