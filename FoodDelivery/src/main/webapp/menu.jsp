<%@ page import="java.util.List" %>
<%@ page import="com.tap.model.Menu" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .menu-item {
            border: 1px solid #dee2e6;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 20px;
            position: relative;
        }
        .food-image {
            position: absolute;
            top: 10px;
            right: 10px;
            max-width: 210px;
            max-height: 210px;
            border-radius: 5px;
        }
        .order-button {
            color: blue;
            padding: 10px 20px;
            background-color: #f8f9fa;
            border: 2px solid blue;
            border-radius: 5px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(330%, -50%);
        }
        .cart-button {
            color: white;
            background-color: green;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
    <div class="container">
        <h2 class="mt-5 mb-4">Menu</h2>
        <div class="row">
            <% List<Menu> menu = (List<Menu>) request.getAttribute("allMenu");
               if (menu != null && !menu.isEmpty()) {
                   for (Menu food : menu) { %>
                       <div class="col-md-12">
                           <div class="menu-item">
                               <div class="food-details">
                                   <h4><%= food.getName() %></h4>
                                   <p><strong>Price:</strong> <%= food.getPrice() %></p>
                                   <p><strong>Description:</strong> <%= food.getDescription() %></p>
                               </div>
                               <img src="<%= food.getImagePath() %>" class="food-image" alt="<%= food.getName() %>">
                               <button class="btn btn-primary order-button" onclick="addToCart(<%= food.getMenuId() %>)">ADD</button>
                           </div>
                       </div>
                   <% }
               } else { %>
                   <div class="col">
                       <p>No food items available in the menu.</p>
                   </div>
               <% } %>
        </div>
        <button class="btn btn-success cart-button" onclick="proceedToCheckout()">Proceed to Checkout</button>
    </div>
    <script>
        function addToCart(foodId) {
            $.ajax({
                type: 'POST',
                url: 'CartServlet',
                data: { foodId: foodId },
                success: function(response) {
                    alert('Item added to cart');
                }
            });
        }

        function proceedToCheckout() {
            window.location.href = 'CheckoutServlet';
        }
    </script>
</body>
</html>
