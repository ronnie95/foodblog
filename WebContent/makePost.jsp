<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="UploadFileServlet" method="post" enctype="multipart/form-data" name="form1" id="form1">
		<center>
			<table border="1">
				<tr>
					<td align="center"><b>Make New Post</b></td>
				</tr>
				<tr>
					<td>
						<textarea name="text1"><% String txt=(String)session.getAttribute("orderlist");
							System.out.println(txt);
							out.println(txt);
						%>
						</textarea>
						Upload images : <input name="file" type="file" id="file" multiple>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="submit" name="Submit" value="Submit Post">
					</td>
				</tr>
			</table>
		</center>
	</form>
            

</body>
</html>