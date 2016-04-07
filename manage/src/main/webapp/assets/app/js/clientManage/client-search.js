/**
 * Created by candice on 2015/6/18.
 */
$('#clientListTable').bootstrapTable({
});
function clientListTable(value, row, index) {
  return [
    '<a class="like" style="color: blue;" data-toggle="modal" title="修改"><i class="icon-pencil"></i></a> ',
    '<a class="remove mgl5" style="color: blue; " href="javascript:void(0)" title="删除"><i class="icon-remove"></i></a>',
  ].join('');
}
