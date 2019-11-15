var rows;
var operateEvents = {

    /*删除项目提示方法*/
    'click #deleteDetail': function (e, value, row, index) {
        if (confirm("你确定要删除吗？")) {
            var uuid = row.uuid;
            $.ajax({
                type: "POST",// 方法类型
                dataType: "json",// 预期服务器返回的数据类型
                url: contextPath + "/detail/deleteDetail.do",//url
                data: {"uuid": uuid},
                success: function (result) {
                    if (result.status == 200) {
                        alert(result.msg);
                        $("#detailTable").bootstrapTable('refreshOptions', {pageNumber: 1});
                    } else {
                        alert(result.msg);
                    }
                }
            })
        }
    },

    /*修改项目方法*/
    'click #updateDetail': function (e, value, row, index) {
        var uuid = row.uuid;
        $.ajax({
            type: "POST",// 方法类型
            dataType: "json",// 预期服务器返回的数据类型
            url: contextPath + "/detail/findDetailByUuid.do",//url
            data: {"uuid": uuid},
            success: function (result) {
                $("#updateDetailModal").modal("show");
                if (result.status == 200) {
                    $("#updateDetailUuid").val(result.data.uuid);
                    $("#updateDetailModel").val(result.data.model);
                    $("#updateDetailFunction").val(result.data.function);
                    $("#updateDetailDescribe").val(result.data.describe);
                    $("#updateDetailRenderName").val(result.data.renderName);
                    $("#updateDetailLevel").val(result.data.level);
                    $("#updateDetailStatus").val(result.data.status);
                    $("#updateItemDetailUuid").val(result.data.item);
                }
            }
        })
    },

    /*提交问题的方法*/
    'click #saveDetail': function (e, value, row, index) {
        var uuid = row.uuid;
        $.ajax({
            type: "POST",// 方法类型
            dataType: "json",// 预期服务器返回的数据类型
            url: contextPath + "/detail/findItemByUuid.do",//url
            data: {"uuid": uuid},
            success: function (result) {
                $("#submitDetailModal").modal("show");
                if (result.status == 200) {
                    $("#submitItemUuid").val(result.data.item);
                }
            }
        })
    },
};

/*问题数据的主表*/
$(function () {
    $('#detailTable').bootstrapTable({
        /*请求数据表格*/
        url: contextPath + "/detail/findDetailByItemId.do?itemUuid=" + itemUuid,
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
                field: 'model',
                title: '问题模块',
                align: 'center'
            }, {
                field: 'function',
                title: '具体功能',
                align: 'center'
            }, {
                field: 'describe',
                title: '具体描述',
                align: 'center'
            }, {
                field: 'renderName',
                title: '提交者',
                align: 'center'
            }, {
                field: 'level',
                title: '优先级',
                align: 'center'
            }, {
                field: 'status',
                title: '处理状态',
                align: 'center',
                formatter: function (value, row, index) {
                    if (row.status == "1") {
                        return [
                            '<span style="color:#2eff65;font-weight:900;">已解决</span>'
                        ]
                    } else if (row.status == '0') {
                        return [
                            '<span style="color:#ff6762;font-weight:900;">未解决</span>'
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
                        '<button type="button" id="updateDetail" class="btn btn-success waves-effect btn-sm m-b-5" >修改</button>&nbsp',
                        '<button type="button" id="deleteDetail" class="btn btn-danger waves-effect btn-sm m-b-5" >删除</button>',
                    ].join('');
                },
                events: operateEvents,
            }
        ]
    })
});


/*更改用户信息方法*/
function updateDetail() {
    $.ajax({
        type: "POST",// 方法类型
        dataType: "json",// 预期服务器返回的数据类型
        url: contextPath + "/detail/updateDetail.do",//url
        data: $('#updateDetailForm').serialize(),
        success: function (result) {
            if (result.status == 200) {
                alert(result.msg);
                $("#detailTable").bootstrapTable('refreshOptions', {pageNumber: 1});
            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}


/*提交问题方法*/
function saveDetail() {
    $.ajax({
        type: "POST",// 方法类型
        dataType: "json",// 预期服务器返回的数据类型
        url: contextPath + "/detail/submitDetail.do?itemUuid=" + itemUuid,
        data: $('#saveDetailForm').serialize(),
        success: function (result) {
            if (result.status == 200) {

                window.history.go(0);
                alert(result.msg);
                $("#detailTable").bootstrapTable('refreshOptions', {pageNumber: 1});
            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}