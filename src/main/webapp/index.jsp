<%-- 
    Document   : index
    Created on : Feb 10, 2015, 12:53:22 PM
    Author     : eennis
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Hotel Lab</title>
        <link href="content/bootstrap_cerulean.min.css" rel="stylesheet" />
        <link href="content/hotel_lab.css" rel="stylesheet" type="text/css"/>  
    </head>
    <body>

        <nav class="navbar navbar-default navbar-static-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="" class="pad-left-sm navbar-brand" id="homeBtn">Hotel Lab (MavenJPA)</a>
                </div>
            </div>
        </nav>

        <div class="container">
            <div class="row">

                <div class="col-lg-4">
                    <form id="searchForm" action="/hotelweb" method="post">
                        <table class="table">
                            <tr>
                                <td>
                                    <label for="dropdown">Search Type: </label>
                                    <select id="dropdown" name="dropdown">
                                        <option value="all">All</option>
                                        <option value="byCity">City</option>
                                        <option value="byState">State</option>
                                    </select>
                                </td>
                                <td>
                                    <div class="form-group hidden" id="searchByGroup">
                                        <label for="searchVal">Search By: </label>
                                        <input type="text" name="searchVal" id="searchVal" value="" class="required"/>
                                    </div>
                                </td>
                            </tr>
                            <tr><td><button type="button" class="btn btn-primary" id="searchBtn">Search</button></td></tr>
                        </table>
                    </form>
                </div>    

                <div class="col-lg-4">
                    <table class="table sortable" id="hotelList">
                        <thead>
                            <h1>Hotels</h1>
                            <tr>
                                <div class="form-group">
                                    <th id="hotelIdHeader">Hotel Id</th>
                                    <th id="hotelNameHeader">Name</th>
                                </div>
                            </tr>
                        </thead>
                        <% int i = 0;%>
                        <tbody>
                            <c:forEach var="hotel" items="${hotels}">
                                <tr>
                                    <div class="form-group">
                                        <td class="small_td">${hotel.hotelId}</td>
                                        <td class="selectable notselected" id="${hotel.hotelId}" arraySpace="<%= i++%>">${hotel.hotelName}</td>
                                    </div>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </div>

                <div class="col-lg-4">

                    <form id="hotelForm" class="hotelselected" action="${resetAction}" method="POST">
                        <table class="table">
                            <th><h1>Hotel Info</h1></th>

                            <tr>
                                <div class="form-group">
                                    <td><label for="name">Hotel Name:</label></td>
                                    <td><input type="text" name="name" id="name" value="${hotelName}" class="required"/></td>
                                </div>
                            </tr>

                            <tr>
                                <div class="form-group">
                                    <td><label for="address">Address:</label></td>
                                    <td><input type="text" name="address" id="address" value="${address}" class="required"/></td>
                                </div>
                            </tr>

                            <tr>
                                <div class="form-group">
                                    <td><label for="city">City:</label></td>
                                    <td><input type="text" name="city" id="city" value="${city}" class="required"/></td>
                                </div>
                            </tr>

                            <tr>
                                <div class="form-group">
                                    <td><label for="state">State:</label></td>
                                    <td><input type="text" name="state" id="state" value="${state}" class="required"/></td>
                                </div>
                            </tr>

                            <tr>
                                <div class="form-group">
                                    <td><label for="postal">Postal Code:</label></td>
                                    <td><input type="text" name="postal" id="postal" value="${postal}" class="required"/></td>
                                </div>
                            </tr>

                            <tr>
                                <div class="form-group">
                                    <td><label for="notes">Notes:</label></td>
                                    <td><input type="textarea" name="notes" id="notes" value="${notes}" class=""/></td>
                                </div>
                            </tr>

                        </table>
                        <button type="button" class="btn btn-primary" id="insertBtn">Create New</button>
                        <button type="button" class="btn btn-primary" id="updateBtn">Update</button>
                        <button type="button" class="btn btn-danger" id="deleteBtn">Delete</button>
                        <button type="button" class="btn btn-primary" id="clearBtn">Clear Form</button>

                        <br><br>
                                <p class="hidden" id="error">Hmm</p>    
                                </form>

                </div>
            </div>
            <h2>Number of Sessions: ${sessionCount}</h2>
            <h2 id="test"></h2>
        </div>

        <script src="scripts/jquery-1.10.2.min.js"></script>
        <script src="scripts/jquery.validate.min.js"></script>
        <script src="scripts/bootstrap.min.js"></script>
        <script src="scripts/bootstrap-sortable.js" type="text/javascript"></script>
        <script src="scripts/hotel_lab_web.js" type="text/javascript"></script>
    </body>
</html>
