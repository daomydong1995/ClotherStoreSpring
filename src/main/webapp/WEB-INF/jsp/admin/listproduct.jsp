<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nhom6pro
  Date: 1/25/2018
  Time: 2:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" type="image/png" href="/resources/assets/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>Light Bootstrap Dashboard by Creative Tim</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <meta name="viewport" content="width=device-width"/>


    <!-- Bootstrap core CSS     -->
    <link href="/resources/assets/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Animation library for notifications   -->
    <link href="/resources/assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="/resources/assets/css/light-bootstrap-dashboard.css?v=1.4.0" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="/resources/assets/css/demo.css" rel="stylesheet"/>


    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="/resources/assets/css/pe-icon-7-stroke.css" rel="stylesheet"/>
    <script src="/resources/assets/js/jquery.twbsPagination.js" type="text/javascript"></script>
    <script src="/resources/assets/js/jquery.3.2.1.min.js" type="text/javascript"></script>
    <script src="/resources/assets/js/jquery.twbsPagination.js" type="text/javascript"></script>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Danh Sách Sản Phẩm Có Trong Cửa Hàng</h4>
                        <%--<p class="category">Here is a subtitle for this table</p>--%>
                    </div>
                    <div class="content table-responsive table-full-width">
                        <table class="table table-hover table-striped">
                            <thead>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>UnitPrice</th>
                            <th>Discount</th>
                            <th>Action</th>
                            <th>Action</th>
                            </thead>
                            <tbody class="tbody">
                            </tbody>
                        </table>
                        <ul id="pagination-demo" class="pagination-sm"></ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<!--   Core JS Files   -->

<script src="/resources/assets/js/bootstrap.min.js" type="text/javascript"></script>

<!--  Charts Plugin -->
<script src="/resources/assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="/resources/assets/js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>

<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<script src="/resources/assets/js/light-bootstrap-dashboard.js?v=1.4.0"></script>

<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
<script src="/resources/assets/js/demo.js"></script>

<script type="text/javascript">
    $(document).ready(function () {

        demo.initChartist();
        loadData();
        $.notify({
            icon: 'pe-7s-gift',
            message: "${message}"

        }, {
            type: 'info',
            timer: 4000
        });

    });
    function loadData(pageIndex) {
        if (pageIndex == null) pageIndex = 1;
        $.ajax({
            url: "/admin/productList",
            type: "GET",
            contentType: "application/json;charset=utf-8",
            data: {pagePro: pageIndex},
            dataType: "json",
            success: function (result) {
                var html = '';
                var data = result.data;
                $.each(data, function (key, product) {
                    var img = '<img class="img-thumbnail" style="cursor:pointer" src="/resources/uploads/'+product.image+'" height="120px" width="50px"/>';
                    html += '<tr>';
                    html += '<td>' + img + '</td>'
                    html += '<td>' + product.name + '</td>';
                    html += '<td>' + product.quantity + '</td>';
                    html += '<td>' + product.unitPrice + '</td>';
                    html += '<td>' + product.discount + '</td>';
                    html += '<td>' +
                        ' <a  class="btn btn-primary" href="/admin/editproduct?id='+product.id+'"><img src="/resources/assets/img/icon/edit.svg " height="20" width="20" ></a>' +
                        '</td>';
                    html += '<td>' +
                        '<a class="btn btn-default" href="/deletepro/'+product.id+'"><img src="/resources/assets/img/icon/delete.svg " height="20"\n' +
                        '                width="20"></a>' +
                        '</td>';
                    html += '</tr>';
                });
                $('.tbody').html(html);
                paging(result.total, function () {
                    loadData();
                })
            },
            error: function (errormessase) {
                alert(errormessase.responseText);
            }
        });
    }

    // Phân trang
    function paging(totalRow, callback) {
        var totalPage = Math.ceil(totalRow / 5)
        $('#pagination-demo').twbsPagination({
            totalPages: totalPage,
            visiblePages: 5,
            onPageClick: function (event, page) {
                loadData(page);
            }
        });
    }
</script>

</body>
</html>
