var rows;

var operateEvents = {

    /*删除项目提示方法*/
    'click #deleteItem': function (e, value, row, index) {
        if (confirm("你确定要删除吗？")) {
            var uuid = row.uuid;
            $.ajax({
                type: "POST",// 方法类型
                dataType: "json",// 预期服务器返回的数据类型
                url: contextPath + "/item/deleteItem.do",//url
                data: {"uuid": uuid},
                success: function (result) {
                    if (result.status == 200) {
                        alert(result.msg);
                        $("#itemTable").bootstrapTable('refreshOptions', {pageNumber: 1});
                    } else {
                        alert(result.msg);
                    }
                },
                error: function (result) {
                    alert("删除失败");
                }
            })
        }
    },

    /*查询问题方法*/
    'click #itemDetail': function (e, value, row, index) {
        var uuid = row.uuid;
        window.location.href = contextPath + "/system/detailList.do?itemUuid=" + uuid;
    },

    /*点击提交问题按钮的方法*/
    'click #submitDetail': function (e, value, row, index) {
        var uuid = row.uuid;
        $.ajax({
            type: "POST",// 方法类型
            dataType: "json",// 预期服务器返回的数据类型
            url: contextPath + "/item/findItemByUuid.do",//url
            data: {"uuid": uuid},
            success: function (result) {
                $("#submitDetailModal").modal("show");
                if (result.status == 200) {
                    $("#submitItemUuid").val(result.data.uuid);
                }
            }
        })
    },

    /*修改项目方法*/
    'click #updateItem': function (e, value, row, index) {
        var uuid = row.uuid;
        $.ajax({
            type: "POST",// 方法类型
            dataType: "json",// 预期服务器返回的数据类型
            url: contextPath + "/item/findItemByUuid.do",//url
            data: {"uuid": uuid},

            success: function (result) {
                $("#updateItemModal").modal("show");
                if (result.status == 200) {
                    $("#updateItemUuid").val(result.data.uuid);
                    $("#updateItemName").val(result.data.name);
                    $("#updateItemNumber").val(result.data.number);
                    $("#updateItemProblem").val(result.data.problem);
                }
            }
        })
    },
};


/*项目数据的主表*/
$(function () {
    $('#itemTable').bootstrapTable({
        /*请求数据表格*/
        url: contextPath + "/item/findItemAll.do",
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
                title: '项目名称',
                align: 'center'
            }, {
                field: 'number',
                title: '合同编号',
                align: 'center'
            }, {
                field: 'problem',
                title: '项目问题',
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
                        '<button type="button" id="updateItem" class="btn btn-success waves-effect btn-sm m-b-5" >修改</button>&nbsp',
                        '<button type="button" id="deleteItem" class="btn btn-danger waves-effect btn-sm m-b-5" >删除</button>',
                        '<button type="button" id="submitDetail" class="btn btn-success waves-effect btn-sm m-b-5">提交问题</button>&nbsp',
                        '<button type="button" id="itemDetail" class="btn btn-danger waves-effect btn-sm m-b-5">问题详情</button>'
                    ].join('');
                },
                events: operateEvents,
            }
        ]
    })
});

/*添加项目方法*/
function saveItem() {
    $.ajax({
        type: "POST",// 方法类型
        dataType: "json",// 预期服务器返回的数据类型
        url: contextPath + "/item/saveItem.do",//url
        data: $('#saveItemForm').serialize(),
        success: function (result) {
            if (result.status == 200) {

                window.history.go(0);
                alert(result.msg);
                $("#itemTable").bootstrapTable('refreshOptions', {pageNumber: 1});
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
function submitDetail() {
    $.ajax({
        type: "POST",// 方法类型
        dataType: "json",// 预期服务器返回的数据类型
        url: contextPath + "/item/submitDetail.do",//url
        data: $('#submitDetailForm').serialize(),
        success: function (result) {
            if (result.status == 200) {

                window.history.go(0);
                alert(result.msg);
                $("#itemTable").bootstrapTable('refreshOptions', {pageNumber: 1});
            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}

/*修改项目信息方法*/
function updateItem() {
    $.ajax({
        type: "POST",// 方法类型
        dataType: "json",// 预期服务器返回的数据类型
        url: contextPath + "/item/updateItem.do",//url
        data: $('#updateItemForm').serialize(),
        success: function (result) {
            if (result.status == 200) {

                alert(result.msg);
                $("#itemTable").bootstrapTable('refreshOptions', {pageNumber: 1});
            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}