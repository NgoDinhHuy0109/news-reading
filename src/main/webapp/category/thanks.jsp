<html>
<head>
    <meta charset="utf-8">
    <title>Java Servlets and JSP</title>
    <link rel="stylesheet" href="category.css" type="text/css"/>
</head>

<body>
    <h1>Thanks for joining our email list</h1>

    <p>Here is the information that you entered:</p>

    <label>Category Name :</label>
    <span>${category.categoryName}</span><br>
    <label>Description :</label>
    <span>${category.description}</span><br>


    <form action="" method="get">
        <input type="hidden" name="action" value="join">
        <input type="submit" value="Return">
    </form>

</body>
</html>

