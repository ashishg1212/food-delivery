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
        
        <!-- Welcome message -->
        <div class="navbar-nav ml-auto">            
            <span class="nav-item">
                <span class="nav-link" style="color: black; font-weight: bold; font-size: 30px">
                    Welcome, Guest!
                </span>
            </span>
        </div>
        
        <!-- Search bar -->
        <div class="navbar-search">
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2 navbar-search-input" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0 btn-search" type="submit">Search</button>
            </form>
        </div>

        <!-- Login and signup links -->
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="signup.jsp">Signup</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Restaurant cards -->
    <div class="container">
        <h2 class="mt-5 mb-4">Tap Foods</h2>
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
                } else { // If no restaurants are available %>
            <div class="col">
                <p>No restaurants available.</p>
            </div>
            <% } %>
        </div>
    </div>
</body>
</html>