var rows;

var operateEvents = {

    /*删除信息提示方法*/
    'click #deleteUser': function (e, value, row, index) {
        if (confirm("你确定要删除吗？")) {
            var uuid = row.uuid;
            $.ajax({
                type: "POST",// 方法类型
                dataType: "json",// 预期服务器返回的数据类型
                url: contextPath + "/user/deleteUser.do",//url
                data: {"uuid": uuid},
                success: function (result) {
                    if (result.status == 200) {
                        alert(result.msg);
                        $("#userTable").bootstrapTable('refreshOptions', {pageNumber: 1});
                    } else {
                        alert(result.msg);
                    }
                }
            })
        }
    },

    /*更新用户的页面回显方法*/
    'click #updateUser': function (e, value, row, index) {
        var uuid = row.uuid;
        $.ajax({
            type: "POST",// 方法类型
            dataType: "json",// 预期服务器返回的数据类型
            url: contextPath + "/user/findUserByUuid.do",//url
            data: {"uuid": uuid},
            success: function (result) {
                $("#updateUserModal").modal("show");
                if (result.status == 200) {
                    $("#updateUserUuid").val(result.data.uuid);
                    $("#updateUserName").val(result.data.name);
                    $("#updateUserAge").val(result.data.age);
                    $("#updateUserGender").val(result.data.gender);
                    $("#updateUserAddress").val(result.data.address);
                    $("#updateUserEmail").val(result.data.email);
                    $("#updateUserTelephone").val(result.data.telephone);
                    $("#updateUserUsername").val(result.data.username);
                    $("#updateUserPassword").val(result.data.password);
                    $("#updateUserStatus").val(result.data.status);
                }
            }
        })
    },

    /*绑定角色的页面回显方法*/
    'click #addRole': function (e, value, row, index) {
        $.ajax({
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: contextPath + "/role/findAjax.do",//url
            success: function (result) {

                $("#role").empty();

                if (result.status == 200) {
                    for (var i = 0; i < result.data.length; i++) {
                        $("#role").append(
                            '<span><input name="userRoleUuid" id="' + result.data[i].uuid + '" type="checkbox" value="' + result.data[i].uuid + '" />' + result.data[i].name + ' </span> '
                        )
                    }
                    $("#add-role").modal("show");
                    $("#addUserRoleUuid").val(row.uuid)

                    var uuid = row.uuid;
                    $.ajax({
                        type: "POST",//方法类型
                        dataType: "json",//预期服务器返回的数据类型
                        url: contextPath + "/user/findUserByUuid.do",//url
                        data: {"uuid": uuid},
                        success: function (result) {
                            if (result.status == 200) {
                                for (var x = 0; x < result.data.roles.length; x++) {
                                    $("#" + result.data.roles[x].uuid).attr("checked", "checked");
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

/*用户数据的主表*/
$(function () {
    $('#userTable').bootstrapTable({
        url: contextPath + "/user/findAll.do",
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
                title: '姓名',
                align: 'center'
            }, {
                field: 'gender',
                title: '性别',
                align: 'center'
            }, {
                field: 'age',
                title: '年龄',
                align: 'center'
            }, {
                field: 'address',
                title: '地址',
                align: 'center'
            }, {
                field: 'email',
                title: '邮箱',
                align: 'center'
            }, {
                field: 'telephone',
                title: '电话',
                align: 'center'
            }, {
                field: 'username',
                title: '用户名',
                align: 'center'
            }, {
                field: 'status',
                title: '状态',
                align: 'center',
                formatter: function (value, row, index) {
                    if (row.status == "1") {
                        return [
                            '<span style="color:#2eff65;font-weight:900;">已开启</span>'
                        ]
                    } else if (row.status == '0') {
                        return [
                            '<span style="color:#ff6762;font-weight:900;">已关闭</span>'
                        ]
                    }
                    return value;
                }
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
                        '<button type="button" id="addRole" class="btn btn-success waves-effect btn-sm m-b-5" >绑定角色</button>&nbsp',
                        '<button type="button" id="updateUser" class="btn btn-success waves-effect btn-sm m-b-5" >修改</button>&nbsp',
                        '<button type="button" id="deleteUser" class="btn btn-danger waves-effect btn-sm m-b-5" >删除</button>'
                    ].join('');
                },
                events: operateEvents,
            }
        ]
    })
});

/*保存用户信息方法*/
function saveUser() {
    $.ajax({
        type: "POST",// 方法类型
        dataType: "json",// 预期服务器返回的数据类型
        url: contextPath + "/user/saveUser.do",//url
        data: $('#saveUserForm').serialize(),
        success: function (result) {
            if (result.status == 200) {

                window.history.go(0);
                alert(result.msg);
                $("#userTable").bootstrapTable('refreshOptions', {pageNumber: 1});
            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}

/*更改用户信息方法*/
function updateUser() {
    $.ajax({
        type: "POST",// 方法类型
        dataType: "json",// 预期服务器返回的数据类型
        url: contextPath + "/user/updateUser.do",//url
        data: $('#updateUserForm').serialize(),
        success: function (result) {
            if (result.status == 200) {

                alert(result.msg);
                $("#userTable").bootstrapTable('refreshOptions', {pageNumber: 1});
            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}

/*绑定角色的方法*/
function addRole() {

    //当表单提交时，调用所有的校验方法
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: contextPath + "/user/addRole.do",//url
        data: $('#addRoleForm').serialize(),
        success: function (result) {
            if (result.status == 200) {

                alert(result.msg);
                $("#userTable").bootstrapTable('refreshOptions', {pageNumber: 1});
            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}
