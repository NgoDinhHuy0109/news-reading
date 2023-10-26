<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign in</title>
    <link rel="stylesheet" href="sign_in/sign_in.css">
</head>
<body>
    <div class="box" >
        <form action="" method="post">
            <h2>Sign in</h2>
            <input type="hidden" name="action" value="add">
            <div class="inputBox">
                <input type="text" name="username" value="${users.username}" required>
                <span>Username</span>
                <i></i>
            </div>
            <div class="inputBox">
                <input type="password" name="password" value="${users.password}" required>
                <span>Password</span>
                <i></i>
            </div>
            <p>${error}</p>
            <div class="links">
                <a href="#">Forgot Password</a>
                <a href="./sign_in/sign_up.html">Signup</a>
            </div>
            <input type="submit" value="Login" id="submit">
        </form>
    </div>
</body>
</html>