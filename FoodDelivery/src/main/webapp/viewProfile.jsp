<%@ page import="com.tap.model.User" %>
<%@ page import="com.tap.daoimpl.UserDaoI" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Profile</title>
    <!-- Bootstrap CSS link -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; /* Light gray background */
            padding-top: 50px; /* Add padding to the top */
        }

        .container {
            max-width: 600px; /* Limit container width */
        }

        .profile-card {
            background-color: #fff; /* White background */
            border-radius: 10px; /* Rounded corners */
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Shadow effect */
        }

        .profile-card p {
            margin-bottom: 10px; /* Add space between paragraphs */
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="profile-card">
            <h2>MyProfile</h2>
            <% 
                User user = (User) session.getAttribute("user");
                if (user != null) {
                    UserDaoI userDao = new UserDaoI();
                    User userProfile = userDao.getUser(user.getUserId());
                    if (userProfile != null) {
            %>
            <p>Id: <%= userProfile.getUserId() %></p>
            <p>Name: <%= userProfile.getName() %></p>
            <p>Email: <%= userProfile.getEmail() %></p>
            <p>Phone no: <%= userProfile.getPhoneNo() %></p>
            <p>Address: <%= userProfile.getAddress() %></p>
            <p>Username: <%= userProfile.getUsername() %></p>
            <!-- Add more user details here -->
            <% } else { %>
            <p>No user details found.</p>
            <% }
                } else { %>
            <p>User not logged in.</p>
            <% } %>
        </div>
    </div>

    <!-- Bootstrap JavaScript and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
