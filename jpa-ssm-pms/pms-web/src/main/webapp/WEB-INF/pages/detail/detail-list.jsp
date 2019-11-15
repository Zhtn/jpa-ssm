<%--
  Created by IntelliJ IDEA.
  User: Z
  Date: 2019/9/29
  Time: 14:28
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
    <%--bootstrap所需路径--%>
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.css">
    <!-- App css -->
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
       <%-- <%@include file="/base/top.jsp" %>--%>
        <!-- Top Bar End -->


        <!-- Start Page content -->
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div class="card-box table-responsive">
                            <h4 class="m-t-0 header-title">问题详情列表</h4>

                            <table id="detailTable" data-toolbar="#kd-toolbar"></table>
                            <div id="kd-toolbar">
                                <div class="btn-group">
                                    <button data-toggle="modal" data-target="#saveDetailModal"
                                            class="btn btn-primary waves-effect m-b-5">
                                        <i class="fa fa-plus"></i>添加问题
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

<!--修改项目模态框-->
<div class="modal fade none-border" id="updateDetailModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title mt-0"><strong>修改详细问题信息</strong></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <form id="updateDetailForm">
                    <div class="row">
                        <div class="col-md-6">
                            <label class="control-label">问题模块</label>
                            <input class="form-control form-white" placeholder="请输入问题模块" type="text" name="model"
                                   id="updateDetailModel"/>
                        </div>
                        <div class="col-md-6">
                            <label class="control-label">具体功能</label>
                            <input class="form-control form-white" placeholder="请输入具体功能" type="text"
                                   name="function"
                                   id="updateDetailFunction"/>
                        </div>
                        <div class="col-md-6">
                            <label class="control-label">具体描述</label>
                            <input class="form-control form-white" placeholder="请输入具体描述" type="text"
                                   name="describe"
                                   id="updateDetailDescribe"/>
                        </div>
                        <div class="col-md-6">
                            <label class="control-label">提交者</label>
                            <input class="form-control form-white" placeholder="请输入提交者" type="text"
                                   name="renderName"
                                   id="updateDetailRenderName"/>
                        </div>
                        <div class="col-md-6">
                            <label class="control-label">优先级</label>
                            <select class="form-control " name="level" id="updateDetailLevel">
                                <option>高</option>
                                <option>中</option>
                                <option>低</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label class="control-label">处理状态</label>
                            <select class="form-control " name="status" id="updateDetailStatus">
                                <option value="1">已解决</option>
                                <option value="0">未解决</option>
                            </select>
                        </div>
                        <input type="hidden" name="uuid" id="updateDetailUuid">
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-light waves-effect" data-dismiss="modal">关 闭</button>
                <button id="updateDetailButton" type="button" class="btn btn-danger waves-effect" data-dismiss="modal"
                        onclick="updateDetail()">确认修改
                </button>
            </div>
        </div>
    </div>
</div>

<!--提交问题模态框-->
<div class="modal fade none-border" id="saveDetailModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title mt-0"><strong>提交问题</strong></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <form id="saveDetailForm">

                    <%--<input type="text" name="itemUuid" id="saveItemUuid">--%>
                    <div class="row">
                        <div class="col-md-6">
                            <label class="control-label">问题模块</label>
                            <input class="form-control form-white" placeholder="请输入问题模块" type="text" name="model"
                                   id="saveDetailModel"/>
                        </div>
                        <div class="col-md-6">
                            <label class="control-label">具体功能</label>
                            <input class="form-control form-white" placeholder="请输入具体功能" type="text"
                                   name="function"
                                   id="saveDetailFunction"/>
                        </div>
                        <div class="col-md-6">
                            <label class="control-label">具体描述</label>
                            <input class="form-control form-white" placeholder="请输入具体描述" type="text"
                                   name="describe"
                                   id="saveDetailDescribe"/>
                        </div>
                        <div class="col-md-6">
                            <label class="control-label">提交者</label>
                            <input class="form-control form-white" placeholder="请输入提交者" type="text"
                                   name="renderName"
                                   id="saveDetailRenderName"/>
                        </div>
                        <div class="col-md-6">
                            <label class="control-label">优先级</label>
                            <select class="form-control " name="level" id="saveDetailLevel">
                                <option value="null">请选择</option>
                                <option>高</option>
                                <option>中</option>
                                <option>低</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label class="control-label">处理状态</label>
                            <select class="form-control " name="status" id="saveDetailStatus">
                                <option value="null">请选择</option>
                                <option value="1">已解决</option>
                                <option value="0">未解决</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-light waves-effect" data-dismiss="modal">关 闭</button>
                <button id="saveDetailButton" type="button" class="btn btn-danger waves-effect" data-dismiss="modal"
                        onclick="saveDetail()">提 交
                </button>
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

<script src="${pageContext.request.contextPath}/js/detail/detailList.js"></script>

<%--定义全局变量传输项目的uuid--%>

<%--<script>var uuid = '${uuid}';</script>--%>
<script>var itemUuid = '${itemUuid}';</script>

</body>
</html>


