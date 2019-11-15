var rows;

var operateEvents = {

    /*删除信息提示方法*/
    'click #deleteRole': function (e, value, row, index) {
        if (confirm("你确定要删除吗？")) {
            var uuid = row.uuid;
            $.ajax({
                type: "POST",// 方法类型
                dataType: "json",// 预期服务器返回的数据类型
                url: contextPath + "/role/deleteRole.do",//url
                data: {"uuid": uuid},
                success: function (result) {
                    if (result.status == 200) {
                        alert(result.msg);
                        $("#roleTable").bootstrapTable('refreshOptions', {pageNumber: 1});
                    } else {
                        alert(result.msg);
                    }
                }
            })
        }
    },

    /*修改角色模态框页面回显的方法*/
    'click #updateRole': function (e, value, row, index) {
        var uuid = row.uuid;
        $.ajax({
            type: "POST",// 方法类型
            dataType: "json",// 预期服务器返回的数据类型
            url: contextPath + "/role/findRoleByUuid.do",//url
            data: {"uuid": uuid},
            success: function (result) {
                $("#updateRoleModal").modal("show");

                if (result.status == 200) {
                    $("#updateRoleUuid").val(result.data.uuid);
                    $("#updateRoleName").val(result.data.name);
                    $("#updateRoleDesc").val(result.data.describe);
                    $("#updateRoleCode").val(result.data.code);
                }
            }
        })
    },

    /*绑定权限的页面回显方法*/
    'click #addPermission': function (e, value, row, index) {
        $.ajax({
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: contextPath + "/permission/findAllPermission.do",//url
            success: function (result) {

                $("#role").empty();
                if (result.status == 200) {
                    for (var i = 0; i < result.data.length; i++) {
                        $("#role").append(
                            '<span><input name="rolePermissionUuid" id="'+result.data[i].uuid+'" type="checkbox" value="'+result.data[i].uuid+'" />'+result.data[i].name+' </span> '
                        )
                    }
                    $("#add-permission").modal("show");
                    $("#addRolePermissionUuid").val(row.uuid)

                    var uuid = row.uuid;
                    $.ajax({
                        type: "POST",//方法类型
                        dataType: "json",//预期服务器返回的数据类型
                        url: contextPath + "/role/findRoleByUuid.do",//url
                        data: {"uuid": uuid},
                        success: function (result) {
                            if (result.status == 200) {
                                for(var x = 0; x < result.data.permissions.length; x++){
                                    $("#"+result.data.permissions[x].uuid).attr("checked","checked");
                                }
                            }
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

/*角色数据的主表*/
$(function () {
    $('#roleTable').bootstrapTable({
        /*请求数据表格*/
        url: contextPath + "/role/findAll.do",
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
                field: 'name',
                title: '角色名',
                align: 'center'
            }, {
                field: 'describe',
                title: '描述',
                align: 'center'
            }, {
                field: 'code',
                title: '关键字',
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
                        '<button type="button" id="addPermission" class="btn btn-success waves-effect btn-sm m-b-5" >绑定权限</button>&nbsp',
                        '<button type="button" id="updateRole" class="btn btn-success waves-effect btn-sm m-b-5" >修改</button>&nbsp',
                        '<button type="button" id="deleteRole" class="btn btn-danger waves-effect btn-sm m-b-5" >删除</button>'
                    ].join('');
                },
                events: operateEvents,
            }
        ]
    })
});

/*保存角色信息方法*/
function saveRole() {
    $.ajax({
        type: "POST",// 方法类型
        dataType: "json",// 预期服务器返回的数据类型
        url: contextPath + "/role/saveRole.do",//url
        data: $('#saveRoleForm').serialize(),
        success: function (result) {
            if (result.status == 200) {

                window.history.go(0);
                alert(result.msg);
                $("#roleTable").bootstrapTable('refreshOptions', {pageNumber: 1});
            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}

/*修改角色信息方法*/
function updateRole() {
    $.ajax({
        type: "POST",// 方法类型
        dataType: "json",// 预期服务器返回的数据类型
        url: contextPath + "/role/updateRole.do",//url
        data: $('#updateRoleForm').serialize(),
        success: function (result) {
            if (result.status == 200) {

                alert(result.msg);
                $("#roleTable").bootstrapTable('refreshOptions', {pageNumber: 1});
            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}

/*绑定权限的更新方法*/
function addPermission() {

    //当表单提交时，调用所有的校验方法
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: contextPath + "/role/addPermission.do",//url
        data: $('#addPermissionForm').serialize(),
        success: function (result) {
            if (result.status == 200) {

                alert(result.msg);
                $("#roleTable").bootstrapTable('refreshOptions', {pageNumber: 1});
            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}