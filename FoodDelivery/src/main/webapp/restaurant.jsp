<%@ page import="java.util.List" %>
<%@ page import="com.tap.model.Restaurant" %>
<%@ page import="com.tap.model.User" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Restaurants</title>
    <!-- Bootstrap CSS link -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap JavaScript and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: #e8f2fa;
        }
        
        .restaurant-card {
            background-color: #f8f9fa;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            max-width: 300px;
        }

        .restaurant-card img {
            padding: 20px 20px;
            width: 250px;
            height: 150px;
            border-radius: 10px;
            margin-bottom: 10px;
        }

        .restaurant-card h5 {
            text-align: center;
            margin-bottom: -2px;
            font-size: 1.5rem;
        }

        .restaurant-card p {
            margin-bottom: -1px;
        }

        .navbar-search-input {
            height: 30px;
            width: auto;
        }

        .btn-search {
            height: 30px;
            padding: 0 10px;
            font-size: 1rem;
        }

        .navbar-search {
            width: 50%;
            display: flex;
            justify-content: center;
        }

        .navbar-brand {
            margin-left: 0;
            margin-right: auto;
        }

        .nav-link {
            margin-right: 1rem;
        }
    </style>
</head>
<body>

<!-- Navigation bar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <!-- Logo -->
    <a class="navbar-brand " href="#">
        <img src="https://www.pngitem.com/pimgs/m/22-222412_home-delivery-hd-png-download.png" width="150" height="80" alt="Logo">
    </a>

    <!-- Welcome message and user profile -->
    <div class="navbar-nav ml-auto">
        <%
            // Retrieve the user object from the session
            User user = (User) session.getAttribute("user");
            if (user != null) { // If user is logged in
        %>
        <!-- User welcome message with username -->
        <span class="nav-item">
            <span class="nav-link" style="color: black; font-weight: bold; font-size: 30px">
                Welcome, <%= user.getUsername() %>!
            </span>
        </span>
        <% } else { // If user is not logged in %>
        <!-- Guest welcome message -->
        <span class="nav-item">
            <span class="nav-link" style="color: black; font-weight: bold; font-size: 30px">
                Welcome, Guest!
            </span>
        </span>
        <% } %>
    </div>

    <!-- Search bar -->
    <div class="navbar-search">
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2 navbar-search-input" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0 btn-search" type="submit">Search</button>
        </form>
    </div>

    <!-- User profile dropdown -->
    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <%= user.getUsername() %>
        </button>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="viewProfile.jsp">View Profile</a>
            <a class="dropdown-item" href="editProfile.jsp">Edit Profile</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="LogoutServlet">Logout</a>
            
        </div>
    </div>
</nav>

<!-- Restaurant cards -->
<div class="container">
    <h2 class="mt-5 mb-4">Restaurants</h2>
    <div class="row">
        <%
            // Retrieve the list of restaurants from the request attribute
            List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");
            if (restaurants != null && !restaurants.isEmpty()) { // If there are restaurants available
                for (Restaurant restaurant : restaurants) { // Iterate through each restaurant
        %>
        <!-- Restaurant card -->
        <div class="col-md-4">
            <div class="restaurant-card">
                <!-- Restaurant image -->
                <img src="<%= restaurant.getImagePath() %>">
                <!-- Restaurant name with a link to the menu -->
                <h5 class="card-title"><a href="MenuServlet?restaurantId=<%= restaurant.getRestaurantId() %>"> <%= restaurant.getName() %></a> </h5>
                <!-- Restaurant details -->
                <p class="card-text">Cuisine: <%= restaurant.getCuisineType() %></p>
                <p class="card-text">Rating: <%= restaurant.getRating() %> . <%= restaurant.getEta() %> mins</p>
                <p class="card-text">Address: <%= restaurant.getAddress() %></p>
            </div>
        </div>
        <%
                } // End of for loop
            } else { // If no restaurants are available
        %>
        <div class="col">
            <p>No restaurants available.</p>
        </div>
        <% } %>
    </div>
</div>

</body>
</html>
