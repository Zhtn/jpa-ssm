<%--
  Created by IntelliJ IDEA.
  User: Z
  Date: 2019/10/23
  Time: 7:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Highdmin - Responsive Bootstrap 4 Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <!-- App favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico">

    <!-- App css -->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/css/metismenu.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet" type="text/css" />

    <script src="${pageContext.request.contextPath}/assets/js/modernizr.min.js"></script>

</head>


<body>

<!-- Begin page -->
<div id="wrapper">

    <!-- ========== Left Sidebar Start ========== -->
    <%@include file="/base/left.jsp" %>
    <!-- Left Sidebar End -->    <!-- ========== Left Sidebar Start ========== -->
    <%@include file="/base/left.jsp" %>
    <!-- Left Sidebar End -->



    <!-- ============================================================== -->
    <!-- Start right Content here -->
    <!-- ============================================================== -->

    <div class="content-page">

        <!-- Top Bar Start -->
        <%@include file="/base/top.jsp" %>
        <!-- Top Bar End -->



        <!-- Start Page content -->
        <div class="content">
            <div class="container-fluid">

                <div class="row">
                    <div class="col-sm-6 offset-3">
                        <div class="text-center mt-5">
                            <h1 class="text-error">403</h1>
                            <h4 class="text-uppercase text-danger mt-3">Page Not Found</h4>
                            <p class="text-muted mt-3">It's looking like you may have taken a wrong turn. Don't worry... it
                                happens to the best of us. Here's a
                                little tip that might help you get back on track.</p>

                            <a class="btn btn-md btn-custom waves-effect waves-light mt-3" href="${pageContext.request.contextPath}/system/login.do"> Return Home</a>
                        </div>

                    </div><!-- end col -->
                </div>
                <!-- end row -->

            </div> <!-- container -->

        </div> <!-- content -->

        <footer class="footer text-right">
            2018 © Highdmin. - Coderthemes.com
        </footer>

    </div>


    <!-- ============================================================== -->
    <!-- End Right content here -->
    <!-- ============================================================== -->


</div>
<!-- END wrapper -->



<!-- jQuery  -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/waves.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.slimscroll.js"></script>

<!-- App js -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.core.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.app.js"></script>

</body>
</html>
