<jsp:include page="header.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<div class="container" style="padding-top: 5;">
    <div class="col-md-offset-1 col-md-10 col-lg-offset-1 col-lg-10 container text-center">
        <div class="text-center bg-light">
            <div class="expression" style="text-align: center; font-size: 16pt">Director didn't shot any films.</div>
            <div class="text-left" style="margin: 20px; padding: 20px 0;">
                <table id="dataTable" class="table table-dark">
                    <thead>
                    <tr>
                        <th scope="col">First name</th>
                        <th scope="col">Last name</th>
                        <th scope="col">Birth date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${pageVersion eq 'Director without films'}">
                            <tr>
                                <th scope="row">${directors.firstName}</th>
                                <td>${directors.lastName}</td>
                                <td>${directors.birthDate}</td>
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

