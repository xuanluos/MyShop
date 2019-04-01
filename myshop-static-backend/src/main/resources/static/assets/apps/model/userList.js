var userList = function () {
    var _grid = null;
    var myScript = function () {
        _grid = Datatables.initAjaxDataTables("/user/page", [
            {"data": "id"},
            {"data": "username"},
            {"data": "phone"},
            {"data": "email"},
            {
                "data": function (row, type, val, meta) {
                    return DateTime.format(row.updated, "yyyy-MM-dd HH:mm:ss");
                }
            },
            {
                "data": function (row, type, val, meta) {
                    return '<a href="/user/form?id='+row.id+'" class="btn default green-stripe"> 编辑 </a>' +
                        '<button class="btn default red-stripe  mt-sweetalert"'+
                    'data-title="您确定要删除这项数据吗？"'+
                    'data-message=""'+
                    'data-type="info" data-show-confirm-button="true"'+
                    'data-confirm-button-class="btn-danger"'+
                    'data-show-cancel-button="true"'+
                    'data-cancel-button-class="btn-default"'+
                    'data-close-on-confirm="false"'+
                    'data-close-on-cancel="false"'+
                    'data-confirm-button-text="确定"'+
                    'data-cancel-button-text="取消"'+
                    'data-popup-title-success="数据已删除"'+
                    'data-popup-message-success=""'+
                    'data-popup-title-cancel="操作已取消"'+
                        'data-url="/user/delete?id='+row.id+
                    '" data-popup-message-cancel="">删除</button>';

                }
            }
        ]);
    }

    /**
     * 模糊查询
     */
    var handlerUserSearch = function () {
        var username = $("#username").val();
        var phone = $("#phone").val();
        var email = $("#email").val();

        setParam("username", username);
        setParam("phone", phone);
        setParam("email", email);
        _grid.getDataTable().ajax.reload();
        _grid.clearAjaxParams();
    }

    /**
     *
     * @param attribute 要查询的属性
     * @param value 要查询的属性值
     */
    function setParam(attribute, value) {
        if (value.length > 0 && value != null) {
            _grid.setAjaxParam(attribute, value);
        }
    }

    return {
        init :function () {
            myScript();
        },
        userSearch : function () {
            handlerUserSearch();
        }
    };

}();

$(function () {
    userList.init();
})
