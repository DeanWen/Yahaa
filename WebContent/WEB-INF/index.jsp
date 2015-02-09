<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />
<!--==============================Content=================================-->
			<div class="content"><div class="ic">More Website Templates @ TemplateMonster.com - December 30, 2013!</div>
				<div class="container_12">
					<div class="grid_6">
					<c:forEach var="photo" items="${photos}">
						<div class="p1_box left cl1">
							<div class="type"></div>
							<img src="${photo}" alt="">
							<a href="#" class="bot">Boudoir Beauty <span>7 <br>images</span></a>
						</div>
					</c:forEach> 
					</div>
						<!-- <div class="p1_box left cl2">
							<div class="type"></div>
							<div class="gray_block">
								Go ahead and read more about this <span class="col1"><a href="http://blog.templatemonster.com/free-website-templates/" rel="nofollow">free theme</a></span> created by TemplateMonster.com.<br>
								Wide variety of premium <span class="col1"><a href="http://www.templatemonster.com/category/art-photography-website-templates/" rel="nofollow">Art &amp; Photography templates</a></span> is to be found in the same name category at our site.
							</div>
							<a href="#" class="bot">Master Class<span>5 <br>comments</span></a>
						</div>
						<div class="p1_box left cl1 pos1">
							<div class="type"></div>
							<img src="images/page1_img4.jpg" alt="">
							<a href="index-2.html" class="bot">Summer Lifestyle<span>10<br>images</span></a>
						</div>
						<div class="p1_box left cl3 pos1">
							<div class="type"></div>
							<img src="images/page1_img6.jpg" alt="">
							<a href="index-3.html" class="bot">Coexisting with Nature<span><strong class="fa-angle-right fa"></strong></span></a>
						</div>
					</div>
					<div class="grid_6">
						<div class="p1_box right cl1">
							<div class="type"></div>
							<img src="images/page1_img2.jpg" alt="">
							<a href="index-2.html" class="bot">Pics of Nature <span>8 <br>images</span></a>
						</div>
						<div class="p1_box right cl3">
							<div class="type"></div>
							<img src="images/page1_img3.jpg" alt="">
							<a href="index-3.html" class="bot">Night Sky Video<span><strong class=" fa-angle-right fa"></strong></span></a>
						</div>
						<div class="p1_box right cl4">
							<div class="type"></div>
							<a href="#" class="bot">Inspirational Resources</a>
						</div>
						<div class="p1_box right cl5">
							<div class="type"></div>
							<blockquote>To me, photography is an art of observation. It’s about finding something interesting in an ordinary place... I’ve found it has little to do with the things you see and everything to do with the way you see them. </blockquote>
							<a href="#" class="bot">Elliott Erwitt <span>8 <br>comments</span></a>
						</div>
						<div class="p1_box right cl2">
							<div class="type"></div>
							<img src="images/page1_img7.jpg" alt="">
							<a href="#" class="bot">My new project<span><strong class=" fa-angle-right fa"></strong></span></a>
						</div>
					</div> -->
					<div class="clear"></div>
					<div class="grid_12">
						<a href="#" class="round"> Older Posts</a>
					</div>
				</div>
			</div>
<jsp:include page="template-bottom.jsp" />