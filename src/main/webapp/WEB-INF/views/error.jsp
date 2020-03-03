<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
    <p><b>Page URL:</b> <span>${url}</span></p>

    <p><b>Occurred:</b> <span>${timestamp}</span></p>

    <p><b>Response Status:</b> <span>${status}</span></p>

    <p><b>Cause:</b> <span>${exception}</span></p>

    <p><a href = "${pageContext.request.contextPath}/">На главную</a></p>

</body>
</html>