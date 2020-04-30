<#-- Layout -->
<#macro layout title>
    <!DOCTYPE html>
    <html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>${title?default('默认标题')}</title>

        <link rel="stylesheet" href="${base}/res/layui/css/layui.css">
        <link rel="stylesheet" href="${base}/res/css/global.css">

        <script src="${base}/layui/layui/layui.js"></script>
    </head>
    <body>
    <#--公共头-->
    <#include "./header.ftl"/>
    <#nested>
    <#--公共尾-->
    <#include "./footer.ftl"/>
    </body>
    </html>
</#macro>
