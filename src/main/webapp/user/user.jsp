<html>
<head>
    <meta charset="utf-8">
    <title>Java Servlets and JSP</title>
    <link rel="stylesheet" href="user.css" type="text/css"/>
</head>

<body>
    <h1>Thanks for joining our email list</h1>

    <p>Here is the information that you entered:</p>

    <label>Full Name :</label>
    <span>${user.userName}</span><br>
    <label>Email :</label>
    <span>${user.email}</span><br>
    <label>Description :</label>
    <span>${user.description}</span><br>


    <form action="" method="get">
        <input type="hidden" name="action" value="join">
        <input type="submit" value="Return">
    </form>

</body>
</html>

