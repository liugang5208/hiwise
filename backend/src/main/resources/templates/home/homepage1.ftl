

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>表单元素</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../static/style/admin.css" media="all">
    <style>
        #jsonTextarea {
            float: left;
            margin-right: 20px;
            width: 40%;
            height: 70vh;
            outline: none;
            padding: 5px;
        }

        /* 方法2：自定义高亮样式 */
        #newResult1,#oldResult1 {
            float: left;
            width: 40%;
            height: 70vh;
            outline: 1px solid #ccc;
            padding: 5px;
            overflow: scroll;
        }

        .string {
            color: green;
        }

        .number {
            color: darkorange;
        }

        .boolean {
            color: blue;
        }

        .null {
            color: magenta;
        }

        .key {
            color: red;
        }
    </style>
</head>
<body>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6">
<#--            <div class="layui-card">-->
                <div class="layui-card-header">接口对比测试</div>
                <div class="layui-card-body">
                    <form class="layui-form" action="" lay-filter="component-form-element">
                        <div class="layui-row layui-col-space10 layui-form-item">
                            <div class="layui-col-lg6">
                                <label class="layui-form-label">老接口：</label>
                                <div class="layui-input-block">
                                    <input type="text" name="old_api" value="http://gaea.mobike.io/fe/monthcard/exp/modelList" lay-verify="required" placeholder="" autocomplete="off" class="layui-input" style="width: 800px">
                                </div>
                            </div>
                        </div>
                        <div class="layui-row layui-col-space10 layui-form-item">
                            <div class="layui-col-lg6">
                                <label class="layui-form-label">新接口：</label>
                                <div class="layui-input-block">
                                    <input type="text" name="new_api" value="http://di-monthcard.bike.st.sankuai.com/fe/monthcard/exp/modelList" lay-verify="required" placeholder="" autocomplete="off" class="layui-input" style="width: 800px">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="component-form-element">立即提交</button>
                                <button id="cleanAll" type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </form>
                </div>
<#--            </div>-->
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <div id="compareResult" class="layui-card-header">
            </div>
        </div>
        <div class="layui-input-block">
            <div class="layui-card-header">老接口返回结果：</div>
            <pre id="oldResult" name="oldResult" placeholder="" class="layui-textarea"></pre>
        </div>
        <div class="layui-input-block">
            <div class="layui-card-header">新接口返回结果：</div>
            <pre id="newResult" name="newResult" placeholder="" class="layui-textarea"></pre>
        </div>
    </div>
</div>


<script src="../../static/layui/layui.js"></script>
<script>
    layui.config({
        base: '../static/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form'], function(){
        var $ = layui.$
            ,admin = layui.admin
            ,element = layui.element
            ,form = layui.form
        ;

        form.render(null, 'component-form-element');
        element.render('breadcrumb', 'breadcrumb');

        $('#cleanAll').click('', function (data) {
                $('#compareResult').html("");
                $('#newResult').html("");
                $('#oldResult').html("");
            }
        );

        form.on('submit(component-form-element)', function(data){
            $.ajax({
                url:'/api/compare',
                method:'post',
                data:data.field,
                dataType:'JSON',
                success:function(res){
                    if (res.code === 0) {
                        var ans = "新老接口测试结果:  ";
                        if (!res.data.equals) {
                            ans += '<span style=\"color: red\">' + res.data.equals + '</span>';
                        } else {
                            ans += res.data.equals;
                        }
                        $('#compareResult').html(ans)
                        $('#newResult').html(parse2(res.data.newResult));
                        $('#oldResult').html(parse2(res.data.oldResult));
                    } else {
                        alert(res.message);
                    }
                },
                error:function (data) {

                }
            }) ;
            return false;
        });

        var data = '[{"name":"黑子","sex":"男","Age":25,"hobby":["篮球","跑步","看电影","王者荣耀"],"normal":true},{"name":"张三","sex":"男","Age":25,"hobby":["上天","入地"],"normal":false}]';
        function parse1(str) {
            return JSON.stringify(JSON.parse(str), null, "\t");
        }
        //$('#jsonTextarea').text(parse1(data));

        // 方法2：pre + JSON.stringify
        function parse2(str) {
            // 设置缩进为2个空格
            str = JSON.stringify(JSON.parse(str), null, 2);
            str = str
                .replace(/&/g, '&amp;')
                .replace(/</g, '&lt;')
                .replace(/>/g, '&gt;');
            return str.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
                var cls = 'number';
                if (/^"/.test(match)) {
                    if (/:$/.test(match)) {
                        cls = 'key';
                    } else {
                        cls = 'string';
                    }
                } else if (/true|false/.test(match)) {
                    cls = 'boolean';
                } else if (/null/.test(match)) {
                    cls = 'null';
                }
                return '<span class="' + cls + '">' + match + '</span>';
            });
        }
        //$('#jsonPre').html(parse2(data));
    });

</script>
</body>
</html>