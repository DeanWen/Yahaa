<jsp:include page="template-top.jsp" />
<!--==============================Content=================================-->
<div class="content">
	<div class="ic"></div>
	<div class="container_12">
		<form action="setPassword.do" method="POST">
			<table>
				<tbody>
					<tr>
						<th>User Name</th>
						<td class="center"><input class="text" placeholder="Name"
							name="username" type="text" value="${sessionScope.user.screen_Name}" /></td>
					</tr>
					<tr>
						<th>Password</th>
						<td class="center"><input class="text" placeholder="Password"
							name="password" type="password" /></td>
					</tr>
					<tr>
						<td class="center" colspan="2"><input id="login"
							type="submit" name="action" value="Done" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>
<jsp:include page="template-bottom.jsp" />