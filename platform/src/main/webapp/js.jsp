<%
String jsPath = request.getContextPath();
String jsBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+jsPath + "/";
%>
<script src="<%=jsBasePath %>app/js/bootstrap.js"></script>
<script src="<%=jsBasePath %>app/js/bootstrap-tagsinput.js"></script>
<script src="<%=jsBasePath %>app/js/jquery.tabs.js"></script>
<script src="<%=jsBasePath %>app/js/jquery.tabs.js"></script>
<script src="<%=jsBasePath %>app/js/waves.min.js"></script>
<script src="<%=jsBasePath %>app/js/common.js"></script>
<script src="<%=jsBasePath %>app/js/superdropdown.js"></script>
<script src="<%=jsBasePath %>js/base.js"></script>
<script src="<%=jsBasePath %>app/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="<%=jsBasePath %>app/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="<%=jsBasePath %>app/js/bootstrap-table.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=jsBasePath%>js/qiniu/plupload.full.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=jsBasePath%>js/qiniu/qiniu.min.js"></script>
<!-- <script type="text/javascript">
    var config = {
        duration: 700,
        delay: 300
    };
    Waves.attach('.waves-effect', ['waves-button', 'waves-light']);
    Waves.init(config);
</script> -->
