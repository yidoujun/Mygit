function closeDialog() {
    $('#showUserDialog').modal('hide');
}

function importExcel(){
    $('#showUserDialog').modal({
        show : true,
        backdrop : 'static'
    });
}

function closeModal(){
    $('#showUserDialog').modal('hide');
    window.location.reload();
}

/*上传xcel文件*/
$(function() {

    $('#fileUpload').click(function(){
        var name =$("#fileUpload")[0].files[0];
        if(!name){
            $("#uploadExcel").attr("disabled",false);
        }
    })

    $("#upload").on('click', function(){
        var formData = new FormData();
        var name = $("#fileUpload").val();
        console.log(name)
        var fileName=$("#fileUpload")[0].files[0];
        console.log(fileName)
        if(fileName==undefined){
            $("#console").html("请先选择正确的导入文件");
        }
        else{
            var point=fileName.name.lastIndexOf(".");
            var type=fileName.name.substr(point);
            if(type==".xls"||type=="xlsx"){
                $("#console").html("");
                $("#showUserDialog").attr("disabled","disabled");
                formData.append("file",$("#fileUpload")[0].files[0]);
                formData.append("name",name);//这个地方可以传递多个参数
                $.ajax({
                    url : "/emp/uploadExcel",
                    type : 'POST',
                    dataType: "json",
                    async : false,
                    data : formData,
                    processData : false,
                    contentType : false,
                    success : function(result) {
                        $("#uploadState").prepend(result.count);
                        $("#console").prepend(result.message+"\n");
                    }
                });

                /*显示进度条*/
                var timer = setInterval(function() {
                    $.ajax({
                        url : "/emp/flag",
                        dataType: "json",
                        success : function(result) {
                            $("#progress").width(result.flag+"%");
                            $("#fontSize").html(result.flag+"%");
                            if (result.code ==1) {
                                clearInterval(timer);
                            }
                            if(result.flag==100){
                                $("#uploadState").show();
                            }
                        }
                    });
                }, 200);
            }
            else{
                $("#console").html("请先选择正确的导入文件");
            }
        }

    });
});