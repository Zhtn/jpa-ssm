<%--
  Created by IntelliJ IDEA.
  User: Z
  Date: 2019/9/28
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>问题管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description"/>
    <meta content="Coderthemes" name="author"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

    <!-- App favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico">

    <!-- App css -->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/css/icons.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/css/metismenu.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet" type="text/css"/>

    <script src="${pageContext.request.contextPath}/assets/js/modernizr.min.js"></script>

</head>


<body class="account-pages">

<!-- Begin page -->
<div class="accountbg"
     style="background: url(${pageContext.request.contextPath}/assets/images/bg-1.jpg);background-size: cover;"></div>

<div class="wrapper-page account-page-full">

    <div class="card">

        <div class="card-block">

            <div class="account-box">

                <div class="card-box p-5">

                    <h2 class="text-uppercase text-center pb-4">
                        <a href="index.html" class="text-success">
                            <span><img src="${pageContext.request.contextPath}/assets/images/logo.png" alt=""
                                       height="26"></span>
                        </a>
                    </h2>

                    <form action="${pageContext.request.contextPath}/user/login.do" METHOD="post">
                        <div class="form-group m-b-20 row">
                            <div class="col-12">
                                <label for="username">用户名</label>
                                <input class="form-control" type="text" id="username" required="username" name="username"
                                       placeholder="请输入用户名">
                            </div>
                        </div>

                        <div class="form-group row m-b-20">
                            <div class="col-12">
                                <a  class="text-muted pull-right"><small>忘记密码?</small></a>
                                <label for="password">密码</label>
                                <input class="form-control" type="password" required="password" id="password" name="password"
                                       placeholder="请输入密码">
                            </div>
                        </div>

                        <div class="form-group row m-b-20">
                            <div class="col-12">

                                <div class="checkbox checkbox-custom">
                                    <input id="remember" type="checkbox" checked="">
                                    <label for="remember">
                                        Remember me
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group row text-center m-t-10">
                            <div class="col-12">
                                <button class="btn btn-block btn-custom waves-effect" type="submit">登录</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>

        </div>
    </div>
    <div class="m-t-40 text-center">
        <p class="account-copyright">2019 © 山东鑫超网络科技</p>
    </div>

</div>

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
