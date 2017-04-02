<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

  <head>
    <meta charset="utf-8">
    <title>
      Tutorials Point
    </title>
    <script src="//code.jquery.com/jquery-1.11.3.min.js">
    </script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js">
    </script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js">
    </script>
    <!--         <link href="css/ripples.min.css" rel="stylesheet"> -->
    <!--         <link href="css/roboto.min.css" rel="stylesheet"> -->
    <!--         <link href="css/material-fullpalette.min.css" rel="stylesheet"> -->
    <!--         <script src="js/material.min.js" type="text/javascript"> -->
    <!--         </script> -->
    <!--         <script src="js/ripples.min.js" type="text/javascript"> -->
    <!--         </script> -->
    <style>






      body { background-color: #fff; } #menu { float: right; } .container { margin: 0 auto; width: 100%; }
      #submitPaperLabel { font-size: 14px; line-height: 14px; font-weight: bold; } @import "bourbon"; body { background:
      #eee !important; } .wrapper { margin-top: 80px; margin-bottom: 80px; } .form-signin { max-width: 380px; padding:
      15px 35px 45px; margin: 0 auto; background-color: #fff; border: 1px solid rgba(0, 0, 0, 0.1);
      .form-signin-heading, .checkbox { margin-bottom: 30px; } .checkbox { font-weight: normal; } .form-control {
      position: relative; font-size: 16px; height: auto; padding: 10px; @include box-sizing(border-box); &:focus {
      z-index: 2; } } input[type="text"] { margin-bottom: -1px; border-bottom-left-radius: 0;
      border-bottom-right-radius: 0; } input[type="password"] { margin-bottom: 20px; border-top-left-radius: 0;
      border-top-right-radius: 0; } }
    </style>
  </head>

  <body>
    <div class="container">
      <div id="menu">
        <a href="submit-paper.html" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-plus">
          </span>
          <label id="submitPaperLabel">
            Submit Paper
          </label>
        </a>
      </div>
      <div class="wrapper">
        <form class="form-signin" action="login" method="POST">
          <h2 class="form-signin-heading">
            Please login
          </h2>
          <input type="text" class="form-control" name="email" placeholder="Email Address" required autofocus />
          <input type="password" class="form-control" name="password" placeholder="Password" required/>
          <label class="checkbox">
            <input type="checkbox" value="remember-me" id="rememberMe" name="rememberMe">
            Remember me
          </label>
          <button class="btn btn-lg btn-primary btn-block" type="submit">
            Login
          </button>
        </form>
      </div>
    </div>
  </body>

</html>
