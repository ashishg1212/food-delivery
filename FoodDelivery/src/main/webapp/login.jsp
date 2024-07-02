<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .login-box {
            width: 350px;
            background-color: #fff;
            border: 2px solid #ccc;
            padding: 40px;
            margin: 70px auto;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        }

        .login-box h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input[type="text"],
        .form-group input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .form-group button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .form-group button:hover {
            background-color: #45a049;
        }

        .signup-link {
            text-align: center;
            margin-top: 20px;
        }

        .signup-link a {
            color: #4CAF50;
            text-decoration: none;
            font-size: 14px;
        }

        .signup-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="login-box">
                    <h2>Login</h2>
                    <%-- Check if there's an error parameter in the URL --%>
                    <% String error = request.getParameter("error");
                       if (error != null && error.equals("invalid")) { %>
                          <div class="alert alert-danger" role="alert">
                              Invalid username or password. Please try again.
                          </div>
                    <% } %>
                    <form action="LoginServlet" method="post">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" id="username" name="username" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" id="password" name="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                    </form>
                    <div class="signup-link">
                        Don't have an account? <a href="signup.jsp">Sign up</a>
                    </div>
                    <div class="signup-link">
                        Do you want to <a href="index">Explore ?</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</body>
</html>