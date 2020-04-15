<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Excel表展示与下载</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${request.contextPath}/static/css/test.css">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
        h2{
            color:#985f0d;
        }
        #connection{
            position: absolute;
            right:0;
        }
    </style>
</head>
<body>
    <div align="center">
        <h2>银行金融承兑报表展示</h2>
    </div>
    <br />
    <div class="bs-example" data-example-id="striped-table">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th score="row"></th>
                <th>票据编号</th>
                <th>承兑银行</th>
                <th>贴现率</th>
                <th>票面金额</th>
                <th>到账时间</th>
            </tr>
            </thead>
            <tbody>
                <#list bills as bill>
                <tr>
                    <th>${bill.id}</th>
                    <th>${bill.billNo}</th>
                    <th>${bill.bank}</th>
                    <th>${bill.wightedAverageYield}</th>
                    <th>${bill.faceBillAmt}</th>
                    <th>${bill.repairDate}</th>
                </tr>
                </#list>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="3">
                    <a href="/export/doExport" class="btn btn-success">导出报表</a>
                </td>
                <td colspan="3">
                    <a id="connection" href="https://www.lixingblog.com" class="btn btn-success">联系我们</a>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</body>
</html>