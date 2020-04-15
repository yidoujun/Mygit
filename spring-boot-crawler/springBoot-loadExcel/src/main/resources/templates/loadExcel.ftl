<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>用户信息</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <button type="button" class="btn btn-success waves-effect" onclick="importExcel()" id="uploadExcel"><span>批量导入</span></button>

    <#--弹出层-->
    <div class="modal fade" id="showUserDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document" style="margin-top:2%;width:800px;">
            <div id="modal-content" class="modal-content">
                <div class="card">
                    <div class="modal-header">
                        <button type="button" class="close" onclick="closeModal()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title title">人员批量导入</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <input type="file" name="file" id="fileUpload">
                            </div>
                            <button type="button" id="upload" class="btn btn-success" >上传</button>
                        </div>
                        <div style="height:20px;line-height:20px">
                            <div class="progress-bar" id="progress" role="progressbar" aria-valuenow="40" aroa-valuemin="0" ariva-valuemax="100" style="width:0%;margin:20px 0px 20px 0px">
                                <font style="vertical-align:inherit;">
                                    <font style="vertical-align:inherit;" id="fontSize" height:20px;>0%</font>
                                </font>
                            </div>
                        </div>
                        <div>
                            <span><b id="uploadState" style="display: none"></b></span>
                            <div>
                                    <textarea id="console" style="width:750px;height:400px;resize:none">
                                    </textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${request.contextPath}/static/js/loadExcel.js"></script>
</body>
</html>