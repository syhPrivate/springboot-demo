<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>login</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="js/bootstrap.min.js">
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <style type="text/css">
	  body
		{ 
		background: url(/pic/bgp.jpg) no-repeat fixed center; 
		}
		#loginForm
		{
			margin-top: 200px;
			margin-left: 300px;
		}
  </style>
  <script type="text/javascript">
  	
  	function doLogin(){
  		$.ajax({
  			type : "post",
  			url : "login/doLogin",
  			data : $("#loginForm").serialize(),
  			success:function(data){
  				alert(data);
  			}
  		})
  		
  	}
  </script>
</head>
<body>
	<h3 align="center">用户登录</h3>
	<form id="loginForm" class="form-horizontal" role="form">
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">username</label>
    <div class="col-sm-3">
      <input type="text" class="form-control" name="username" placeholder="请输入账号">
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-2 control-label">password</label>
    <div class="col-sm-3">
      <input type="text" class="form-control" name="password" placeholder="请输入密码">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox">记住密码
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="button" class="btn btn-default" onclick="doLogin()">登录</button>
    </div>
  </div>
</form>
</body>
</html>
