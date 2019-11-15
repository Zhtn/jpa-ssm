var rows;

var operateEvents = {

    /*删除信息提示方法*/
    'click #deletePermission': function (e, value, row, index) {
        if (confirm("你确定要删除吗？")) {
            var uuid = row.uuid;
            $.ajax({
                type: "POST",// 方法类型
                dataType: "json",// 预期服务器返回的数据类型
                url: contextPath + "/permission/deletePermission.do", // url
                data: {"uuid": uuid},

                success: function (result) {
                    if (result.status == 200) {
                        alert(result.msg);
                        $("#permissionTable").bootstrapTable('refreshOptions', {pageNumber: 1});
                    } else {
                        alert(result.msg);
                    }
                }
            })
        }
    },

    /*每列修改按钮弹出模态框的回显内容方法*/
    'click #updatePermission': function (e, value, row, index) {

        /*每次首先清空列表再查询，再循环遍历查询父级权限方法*/
        $.ajax({
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: contextPath + "/permission/findAllPermission.do",//url
            success: function (result) {
                if (result.status == 200) {

                    $("#updateParentPermissionId").empty();
                    $("#updateParentPermissionId").append("<option value=''>无上级权限</option>");

                    for (var i = 0; i < result.data.length; i++) {
                        var option = $("<option>").val(result.data[i].uuid).text(result.data[i].name);
                        $("#updateParentPermissionId").append(option);
                    }

                    var uuid = row.uuid;

                    // 回显本行页面信息的方法
                    $.ajax({
                        type: "POST",//方法类型
                        dataType: "json",//预期服务器返回的数据类型
                        url: contextPath + "/permission/findPermissionByUuid.do",//url
                        data: {"uuid": uuid},
                        success: function (result) {
                            if (result.status == 200) {
                                $("#updatePermissionUuid").val(result.data.uuid);
                                $("#updatePermissionName").val(result.data.name);
                                $("#updatePermissionUrl").val(result.data.url);
                                if (result.data.parentPermission == null || typeof result.data.parentPermission == "undefined" || result.data.parentPermission == "") {
                                    $("#updateParentPermissionId").val();
                                } else {
                                    $("#updateParentPermissionId").val(result.data.parentPermission.uuid);
                                    console.log($("#updateParentPermissionId").val());
                                }
                                $("#updatePermissionCode").val(result.data.code);
                                $("#updatePermissionDescribe").val(result.data.description);
                                $("#updatePermissionGenerateMenu").val(result.data.generateMenu);
                                $("#updatePermissionZindex").val(result.data.zindex);
                            }
                            $("#updatePermissionModal").modal("show");
                        }
                    })
                } else {
                    alert(result.msg);
                }
            },
            error: function () {
                alert("异常！");
            }
        });
    }
};

/*权限数据的主表*/
$(function () {
    $('#permissionTable').bootstrapTable({
        /*请求数据表格*/
        url: contextPath + "/permission/findAll.do",
        dataType: 'json',
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        method: 'post',
        striped: true,
        clickToSelect: true,
        pagination: true,
        search: true,
        pageNumber: 1,
        pageSize: 10,
        pageList: "[1,2,10, 25, 50, 100, All]",
        showRefresh: true,
        sidePagination: 'server',
        local: 'zh-CN',
        cache: false,
        smartDisplay: true,
        classes: "table table-bordered table-hover table-striped table-sm table-borderless",
        responseHandler: function (res) {
            // 在Ajax获取到数据，渲染表格之前，修改数据源
            var nres = [];
            nres.push({total: res.totalElements, rows: res.content});
            return nres[0];
        },
        columns: [
            {
                checkbox: true,
            }, {
                field: 'parentPermission.name',
                title: '当前权限的父权限',
                align: 'center'
            }, {
                field: 'name',
                title: '权限名称',
                align: 'center'
            }, {
                field: 'url',
                title: '路径',
                align: 'center'
            }, {
                field: 'code',
                title: '关键字',
                align: 'center'
            }, {
                field: 'description',
                title: '描述',
                align: 'center'
            }, {
                field: 'generateMenu',
                title: '菜单',
                align: 'center',
                formatter: function (value, row, index) {
                    if (row.generateMenu == "1") {
                        return [
                            '<span style="color:#2eff65;font-weight:900;">已生成菜单</span>'
                        ]
                    } else if (row.generateMenu == '0') {
                        return [
                            '<span style="color:#ff6762;font-weight:900;">未生成菜单</span>'
                        ]
                    }
                    return value;
                }
            }, {
                field: 'zindex',
                title: '排序',
                align: 'center'
            }, {
                field: 'createTimeStr',
                title: '创建时间',
                align: 'center'
            }, {
                field: 'updateTimeStr',
                title: '更新时间',
                align: 'center'
            }, {
                field: 'uuid',
                title: '操作',
                align: 'center',
                formatter: function (value, row, index) {
                    return [
                        '<button type="button" id="updatePermission" class="btn btn-success waves-effect btn-sm m-b-5" >修改</button>&nbsp',
                        '<button type="button" id="deletePermission" class="btn btn-danger waves-effect btn-sm m-b-5" >删除</button>'
                    ].join('');
                },
                events: operateEvents,
            }
        ]
    })
});

/*jsp页面添加权限模态框之前的父级权限查询方法*/
function openSavePermissionModal() {
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: contextPath + "/permission/findAllPermission.do",//url
        success: function (result) {
            if (result.status == 200) {
                $("#saveParentPermissionId").empty();
                var option1 = $("<option>").val("").text("无上级权限");
                $("#saveParentPermissionId").append(option1);
                for (var i = 0; i < result.data.length; i++) {
                    $("#saveParentPermissionId").append("<option value='" + result.data[i].uuid + "'>" + result.data[i].name + "</option>");
                }
            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("异常！");
        }
    });

}

/*添加权限信息方法*/
function savePermission() {
    $.ajax({
        type: "POST",// 方法类型
        dataType: "json",// 预期服务器返回的数据类型
        url: contextPath + "/permission/savePermission.do",//url
        data: $('#savePermissionForm').serialize(),
        success: function (result) {
            if (result.status == 200) {

                window.history.go(0);
                alert(result.msg);
                $("#permissionTable").bootstrapTable('refreshOptions', {pageNumber: 1});
            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}

/*每行修改按钮的更新权限信息方法*/
function updatePermission() {
    $.ajax({
        type: "POST",// 方法类型
        dataType: "json",// 预期服务器返回的数据类型
        url: contextPath + "/permission/updatePermission.do",//url
        data: $('#updatePermissionForm').serialize(),

        success: function (result) {
            if (result.status == 200) {

                alert(result.msg);
                $("#permissionTable").bootstrapTable('refreshOptions', {pageNumber: 1});
            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}