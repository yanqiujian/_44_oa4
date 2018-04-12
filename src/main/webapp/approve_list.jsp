<%--
  Created by IntelliJ IDEA.
  User: yangjw
  Date: 2018/3/30
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String basePath = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>千锋教育OA系统</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">千锋教育OA系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                <li><a href="#">Link</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">系统公告</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">杨景文QF000255 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">修改用户信息</a></li>
                        <li><a href="#">修改密码</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">退出系统</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid">
    <div class="panel-group col-md-2" id="accordion" role="tablist" aria-multiselectable="true">
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingOne">
                <h4 class="panel-title">
                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        OA系统
                    </a>
                </h4>
            </div>
            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                <div class="panel-body">
                    <ul class="list-group">
                        <li class="list-group-item">系统首页</li>
                        <li class="list-group-item">系统公告</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingTwo">
                <h4 class="panel-title">
                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        考勤管理
                    </a>
                </h4>
            </div>
            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                <div class="panel-body">
                    <ul class="list-group">
                        <li class="list-group-item">我的考勤</li>
                        <li class="list-group-item">请假加班</li>
                        <li class="list-group-item">请假汇总</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingThree">
                <h4 class="panel-title">
                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                        审批管理
                    </a>
                </h4>
            </div>
            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                <div class="panel-body">
                    <ul class="list-group">
                        <li class="list-group-item">加班审批</li>
                        <li class="list-group-item">请假审批</li>
                        <li class="list-group-item">补签审批</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-10">

        <table class="table table-bordered">
            <thead class="bg-primary">
            <td>部门</td>
            <td>岗位</td>
            <td>姓名</td>
            <td>请假类型</td>
            <td>时长</td>
            <td>创建时间</td>
            <td>备注</td>
            <td>审批人</td>
            <td>状态</td>
            <td>操作</td>
            </thead>
            ${history_list}
            <c:forEach items="${tasklist}" var="item">
            <tr>
                <td>${item["department"]}</td>
                <td>${item["gangwei"]}</td>
                <td>${item["apply_user"]}</td>
                <td>${item["type"]}</td>
                <td>${item["days"]}</td>
                <td>${item["createtime"]}</td>
                <td>备注</td>
                <td>${item["assignee"]}</td>
                <td><span id="ap_${item["taskId"]}" class="btn-warning">待批复</span> </td>
                <shiro:hasPermission name="审批">
                <td><input class="btn btn-default" type="button" value="审批" onclick="approve(${item["taskId"]},'ap_${item["taskId"]}');"></td>
                </shiro:hasPermission>
            </tr>
            </c:forEach>
            <%--<tr>
                <td>研发部</td>
                <td>Java高级工程师</td>
                <td>张三</td>
                <td>调休</td>
                <td>8h</td>
                <td>2018-03-07 07:51:21</td>
                <td>一天年假</td>
                <td>杨景文</td>
                <td><span class="btn-warning">待批复</span> </td>
                <td>0</td>
            </tr>
            <tr>
                <td>研发部</td>
                <td>Java高级工程师</td>
                <td>张三</td>
                <td>调休</td>
                <td>8h</td>
                <td>2018-03-07 07:51:21</td>
                <td>一天年假</td>
                <td>杨景文</td>
                <td><span class="btn-success">已批准</span> </td>
                <td>0</td>
            </tr>--%>
        </table>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function approve(taskId,tagId) {
        $.get("/user/approve?taskId="+taskId,function (data) {
            alert(data);
            if ("success" == data){
                $("#"+tagId).className="btn-success";
            }
        });
    }
</script>
</body>
</html>
