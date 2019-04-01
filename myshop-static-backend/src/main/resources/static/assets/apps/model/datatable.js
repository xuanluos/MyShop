var Datatables = function () {

    const _DatatableSetting = {

        // Internationalisation. For more info refer to http://datatables.net/manual/i18n
        "language": {
            "aria": {
                "sortAscending": ": 以升序排列此列",
                "sortDescending": ": 以降序排列此列"
            },
            "processing": "处理中...",
            "metronicAjaxRequestGeneralError": "无法完成请求，请检查你的网络",
            "emptyTable": "表中数据为空",
            "info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "infoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "infoFiltered": "(由 _MAX_ 项结果过滤)",
            "lengthMenu": "显示 _MENU_ 项结果",
            "search": "搜索:",
            "zeroRecords": "没有匹配结果",
            "paginate": {
                "page": "页码",
                "pageOf": "/"
            }
        },

        // setup buttons extentension: http://datatables.net/extensions/buttons/
        buttons: [

        ],

        searching: false,
        sort: false,

        paging: true,
        StateSave: true,
        deferRender: true,

        "lengthMenu": [
            [5, 10, 15],
            [5, 10, 15 ] // change per page values here
        ],
        // set the initial value
        "pageLength": 10,

        "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable

        // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
        // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js).
        // So when dropdowns used the scrollable div should be removed.
        //"dom": "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
    }

    var handlerInitDataTables = function () {
        $('#datatable').dataTable(_DatatableSetting);
    }

    var handleAjaxDataTables = function (url,columns) {

        var grid = new Datatable();

        var ajaxSetting = {
            "bStateSave": true,
            "ordering": false,
            "ajax": {
                "url": url, // ajax source
            },
            columns: columns
        }

        var settings = $.extend({},_DatatableSetting,ajaxSetting);

        grid.init({
            src: $("#ajaxDatatable"),
            onSuccess: function (grid, response) {
                // grid:        grid object
                // response:    json object of server side ajax response
                // execute some code after table records loaded
            },
            onError: function (grid) {
                // execute some code on network or other general error
            },
            onDataLoad: function (grid) {
                // execute some code on ajax data load
                $(function () {
                    SweetAlert.init();
                })
            },
            loadingMessage: '查询中...',
            dataTable: settings
        });

        return grid;
    }
    return {

        //main function to initiate the module
        initDataTables: function () {

            handlerInitDataTables();
        },

        initAjaxDataTables: function (url,columns) {

            return handleAjaxDataTables(url,columns);
        }

    };

}();