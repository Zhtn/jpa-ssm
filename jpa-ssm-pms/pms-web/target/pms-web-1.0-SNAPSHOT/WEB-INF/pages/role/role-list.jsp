<%--
  Created by IntelliJ IDEA.
  User: Z
  Date: 2019/9/27
  Time: 14:16
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

    <!-- DataTables -->
    <link href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap4.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/plugins/datatables/buttons.bootstrap4.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- Responsive datatable examples -->
    <link href="${pageContext.request.contextPath}/plugins/datatables/responsive.bootstrap4.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- Multi Item Selection examples -->
    <link href="${pageContext.request.contextPath}/plugins/datatables/select.bootstrap4.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- App css -->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.css">

    <link href="${pageContext.request.contextPath}/assets/css/icons.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/css/metismenu.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet" type="text/css"/>

    <script src="${pageContext.request.contextPath}/assets/js/modernizr.min.js"></script>
</head>

<body>

<!-- Begin page -->
<div id="wrapper">
    <!--  Left Sidebar Start -->
    <%@include file="/base/left.jsp" %>
    <!-- Left Sidebar End -->

    <!-- Start right Content here -->
    <div class="content-page">

        <!-- Top Bar Start -->
      <%--  <%@include file="/base/top.jsp" %>--%>
        <!-- Top Bar End -->


        <!-- Start Page content -->
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div class="card-box table-responsive">
                            <h4 class="m-t-0 header-title">角色信息列表</h4>

                            <table id="roleTable" data-toolbar="#kd-toolbar"></table>
                            <div id="kd-toolbar">
                                <div class="btn-group">
                                    <button data-toggle="modal" data-target="#saveRoleModal"
                                            class="btn btn-primary waves-effect m-b-5">
                                        <i class="fa fa-plus"></i>添加角色
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> <!-- end row -->
            </div> <!-- container -->
        </div> <!-- content -->

        <footer class="footer text-right">
            2018 © Highdmin. - Coderthemes.com
        </footer>
    </div>
    <!-- End Right content here -->
</div>
<!-- END wrapper -->


<!--添加角色模态框-->
<div class="modal fade none-border" id="saveRoleModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title mt-0"><strong>添加角色</strong></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>

            <div class="modal-body">
                <form id="saveRoleForm">
                    <div class="row">
                        <div class="col-md-6">
                            <label class="control-label">角色名</label><span id="nameMsgSpan"></span>
                            <input class="form-control form-white" placeholder="请输入角色名" type="text" name="name"
                                   id="saveRoleName"/>
                        </div>
                        <div class="col-md-6">
                            <label class="control-label">描述</label>
                            <input class="form-control form-white" placeholder="请输入角色描述" type="text" name="describe"
                                   id="saveRoleDesc"/>
                        </div>

                        <div class="col-md-6">
                            <label class="control-label">关键字</label>
                            <input class="form-control form-white" placeholder="请输入关键字" type="text" name="code"
                                   id="saveRoleCode"/>
                        </div>

                        <input type="hidden" name="uuid" id="saveRoleUuid">
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-light waves-effect" data-dismiss="modal">关 闭</button>
                <button id="saveRoleButton" type="button" class="btn btn-danger waves-effect" data-dismiss="modal"
                        onclick="saveRole()">添 加
                </button>
            </div>
        </div>
    </div>
</div>


<!--修改角色模态框-->
<div class="modal fade none-border" id="updateRoleModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title mt-0"><strong>修改角色</strong></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>

            <div class="modal-body">
                <form id="updateRoleForm">
                    <div class="row">
                        <div class="col-md-6">
                            <label class="control-label">角色名</label><span id="nameMsgSpan1"></span>
                            <input class="form-control form-white" placeholder="请输入角色名" type="text" name="name"
                                   id="updateRoleName"/>
                        </div>

                        <div class="col-md-6">
                            <label class="control-label">描述</label>
                            <input class="form-control form-white" placeholder="请输入角色描述" type="text" name="describe"
                                   id="updateRoleDesc"/>
                        </div>

                        <div class="col-md-6">
                            <label class="control-label">关键字</label>
                            <input class="form-control form-white" placeholder="请输入关键字" type="text" name="code"
                                   id="updateRoleCode"/>
                        </div>

                        <input type="hidden" name="uuid" id="updateRoleUuid">

                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-light waves-effect" data-dismiss="modal">关 闭</button>
                <button id="updateRoleButton" type="button" class="btn btn-danger waves-effect" data-dismiss="modal"
                        onclick="updateRole()">确认修改
                </button>
            </div>
        </div>
    </div>
</div>

<%--添加权限模态框--%>
<div class="modal fade none-border" id="add-permission">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title mt-0"><strong>添加权限</strong></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <form role="form" id="addPermissionForm">
                    <div class="row" id="role">

                    </div>
                    <input type="hidden" name="uuid" id="addRolePermissionUuid">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light waves-effect" data-dismiss="modal">关 闭</button>
                <button id="addRoleButton" type="button" class="btn btn-danger waves-effect waves-light save-category" data-dismiss="modal" onclick="addPermission();">添加权限</button>
            </div>
        </div>
    </div>
</div>

<!-- jQuery  -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/waves.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.slimscroll.js"></script>

<!-- Required datatable js -->
<script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap4.min.js"></script>
<!-- Buttons examples -->
<script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.buttons.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/buttons.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/jszip.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/pdfmake.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/vfs_fonts.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/buttons.html5.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/buttons.print.min.js"></script>

<!-- Key Tables -->
<script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.keyTable.min.js"></script>

<!-- Responsive examples -->
<script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.responsive.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/responsive.bootstrap4.min.js"></script>

<!-- Selection table -->
<script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.select.min.js"></script>

<!-- App js -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.core.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.app.js"></script>

<%--bootstrap表格所需路径--%>
<script src="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/bootstrap-table-zh-CN.js"></script>

<script>var contextPath = '${pageContext.request.contextPath}'</script>

<script src="${pageContext.request.contextPath}/js/role/roleList.js"></script>


</body>
</html>
