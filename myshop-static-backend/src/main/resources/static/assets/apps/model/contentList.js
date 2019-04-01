var contentList = function () {
    var _grid = null;
    var myScript = function () {
        _grid = Datatables.initAjaxDataTables("/content/page", [
            {"data": "id"},
            {"data": "title"},
            {"data": "subTitle"},
            {"data": "titleDesc"},
            {"data": function (row, type, val, meta) {
                    return '<a href="'+row.url+'">查看</a>';
                }},
            {"data": function (row, type, val, meta) {
                    return '<a href="'+row.pic+'">查看</a>';
                }},
            {"data": function (row, type, val, meta) {
                    return '<a href="'+row.pic2+'">查看</a>';
                }},
            {"data": "content"},
            {
                "data": function (row, type, val, meta) {
                    return DateTime.format(row.updated, "yyyy-MM-dd HH:mm:ss");
                }
            },
            {
                "data": function (row, type, val, meta) {
                    return '<a href="/content/form?id='+row.id+'" class="btn default green-stripe"> 编辑 </a>' +
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
                        'data-url="/content/delete?id='+row.id+
                        '" data-popup-message-cancel="">删除</button>';
                }
            }
        ]);
    }

    /**
     * 模糊查询
     */
    var handlerContentListSearch = function () {
        var title = $("#title").val();
        var subTitle = $("#subTitle").val();
        var titleDesc = $("#titleDesc").val();

        setParam("title", title);
        setParam("subTitle", subTitle);
        setParam("titleDesc", titleDesc);
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
        contentSearch : function () {
            handlerContentListSearch();
        }
    };

}();

$(function () {
    contentList.init();
})
