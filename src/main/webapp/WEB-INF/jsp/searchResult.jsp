<jsp:include page="header.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<div class="container" style="padding-top: 5;">
    <div class="col-md-offset-1 col-md-10 col-lg-offset-1 col-lg-10 container text-center">
        <div class="text-center bg-light">
            <div class="text-left" style="margin: 20px; padding: 20px 0;">
                <table id="dataTable" class="table table-dark">
                    <thead>
                    <tr>
                        <th scope="col">First name</th>
                        <th scope="col">Last name</th>
                        <th scope="col">Birth date</th>
                        <th scope="col">Film name</th>
                        <th scope="col">Release date</th>
                        <th scope="col">Genre</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="film" items="${sortedFilms}">
                        <c:choose>
                            <c:when test="${pageVersion eq 'From Date'}">
                                <c:forEach var="director" items="${directors}">
                                    <c:choose>
                                        <c:when test="${director.id eq film.directorId}">
                                            <tr>
                                                <th scope="row">${director.firstName}</th>
                                                <td>${director.lastName}</td>
                                                <td>${director.birthDate}</td>
                                                <td>${film.name}</td>
                                                <td>${film.releaseDate}</td>
                                                <td>${film.genre}</td>
                                            </tr>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:when test="${pageVersion eq 'One Director'}">
                                <tr>
                                    <th scope="row">${directors.firstName}</th>
                                    <td>${directors.lastName}</td>
                                    <td>${directors.birthDate}</td>
                                    <td>${film.name}</td>
                                    <td>${film.releaseDate}</td>
                                    <td>${film.genre}</td>
                                </tr>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${pageVersion eq 'Director without films'}">
                            <tr>
                                <th scope="row">${directors.firstName}</th>
                                <td>${directors.lastName}</td>
                                <td>${directors.birthDate}</td>
                                <td>${film.name}</td>
                                <td>${film.releaseDate}</td>
                                <td>${film.genre}</td>
                            </tr>
                        </c:when>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>

<script type="text/javascript">
    $(document).ready(function () {
        $('#dataTable').DataTable({
            "language": {
                "lengthMenu": "Отображать _MENU_ записей",
                "zeroRecords": "Извините - поиск не дал результатов",
                "info": "Отображаемая страница _PAGE_ из _PAGES_",
                "infoEmpty": "0 записей",
                "paginate": {
                    "first": "Первая",
                    "last": "Последняя",
                    "next": "Следующая",
                    "previous": "Предыдущая"
                },
                "search": "Поиск:",
                "infoFiltered": "(отфильтровано из _MAX_ записей)",
            }
        });
    });
</script>

