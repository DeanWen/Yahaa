<jsp:include page="template-top.jsp" />
<!--==============================Content=================================-->
	<div class="content"><div class="ic"> </div>
		<div class="container_12">
			<div class="grid_6">
				<div class=" left cl3 pos1">
				<form action="login.do" method="POST">
					<table>
						<tbody>
							<tr>
								<th>User Name</th>
								<td class="center"><input class="text" placeholder="Name"
									name="username" type="text" /></td>
							</tr>
							<tr>
								<th>Password</th>
								<td class="center"><input class="text" placeholder="Password"
									name="password" type="password" />
								</td>
							</tr>
							<tr>
								<td class="center" colspan="2"><input id="login"
									type="submit" name="action" value="Login" /></td>
							</tr>
						</tbody>
					</table>
				</form>
				</div>
			</div>
			
			<div class="grid_6">
				<div class=" right cl2">
				<form action="twitterLogin.do" method="POST">
					<input id="login" type="submit" name="action" value="Twitter Login" />
				</form>
				<form action="flickrLogin.do" method="POST">
					<input id="login" type="submit" name="action" value="Flickr Login" />
				</form>
				</div>
			</div>
			
		<div class="clear"></div>
			<div class="grid_12">
				<a href="#" class="round">Welcome Join</a>
			</div>
		</div>
	</div>
<jsp:include page="template-bottom.jsp" />