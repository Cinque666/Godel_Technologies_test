<jsp:include page="header.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<div class="container">

    <h1>Information about Directors and films</h1><br>
    <hr>

    <form action="${pageContext.request.contextPath}/searchResult" method="POST">
        <div class="form-group">
            <label for="id">Director id:</label>
            <input type="checkbox" checked="checked"
                   onclick="this.nextSibling.style.display=this.checked?'':'none';"><input
                class="form-control" type="number" name="directorId" id="id" placeholder="Enter director's id">
        </div>
        <div class="form-group">
            <label for="date">Date:</label>
            <input type="checkbox" checked="checked"
                   onclick="this.nextSibling.style.display=this.checked?'':'none';"><input
                class="form-control" type="date" name="discoverDate" id="date" placeholder="MM/DD/YY">
        </div>
        <button type="submit" class="btn btn-primary reg-btn">Submit</button>
    </form>


    <hr>

    <div class="error-message">${errorMessage}</div>
</div>

<jsp:include page="footer.jsp"/>