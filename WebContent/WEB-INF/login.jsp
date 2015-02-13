<jsp:include page="template-top.jsp" />
<!--==============================Content=================================-->
	<div class="content"><div class="ic"> </div>
		<div class="container_12">
         <form class="form col-md-12 center-block" method="post" action="instagram-login.do">
            <div class="form-group">
              <button class="btn btn-success btn-lg btn-block">Login with Instagram</button>
            </div>
           </form>
           <form class="form col-md-12 center-block" method="post" action="twitter-login.do">
            <div class="form-group">
              <button class="btn btn-danger btn-lg btn-block">Login with Twitter</button>
            </div>
           </form>
           <form class="form col-md-12 center-block" method="post" action="login.do">
            <div class="form-group">
              <input type="text" class="form-control input-lg" placeholder="User name" name="userName">
            </div>
            <div class="form-group">
              <input type="password" class="form-control input-lg" placeholder="Password" name="password">
            </div>
            <div class="form-group">
              <button class="btn btn-primary btn-lg btn-block">Log In</button>
              <span class="pull-right"><a href="register.do">Sign up now</a></span>
            </div>
          </form>
		<div class="clear"></div>
			<div class="grid_12">
				<a href="#" class="round">Welcome Join</a>
			</div>
		</div>
	</div>
<jsp:include page="template-bottom.jsp" />