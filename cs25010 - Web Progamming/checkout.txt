<?php include("session.php"); ?>
<!DOCTYPE html>
<html>
<head>
  <title>karlTunes - Checkout</title>
  <link href="cs25010.css" type="text/css" rel="stylesheet"/>
  <link rel="icon" type="image/png" href="favicon.png">

  <script>
  <!--
  function check_input(){
    var emailRegexp=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    var cardRegExp = /[0-9]{16}/;

    if(emailRegexp.test(document.details.email_address.value)
      && cardRegExp.test(document.details.credit.value))
    {
      document.details.setAttribute("method", "post");
      document.details.setAttribute("action", "confirmation.php");
    }
    else if(!emailRegexp.test(document.details.email_address.value)){
      alert("Invalid email address entered");
    }
    else if(!cardRegExp.test(document.details.credit.value)){
      alert("Invalid credit card number entered");
    }

  }
    -->
  </script>


</head>
<body>

    <?php
    if (!$gotusername)
    {
      include("login.php");
    }
    else {
      echo'<form action="index.php" method="post">';
      include("menu.php");
      echo"</form>";

      echo"<div id='content'>";
      echo"<strong>This is not a real store! This has been created for university coursework,
      so please do not enter any real details</strong>";

      echo "<form name='details' onsubmit='check_input()'>";
      $total_price = $_SESSION['total_price'];
      echo "<p>The total price of your order is: &#163;" . $total_price . "</p>";
      echo"<p>Enter your email address: <input type='email' id='email_address' /></p>";
      echo"<p>Enter your credit card number: <input type='text' id='credit' maxlength='16' size='17' /></p>";
      echo "<p><input type='submit' value='Checkout'/></p>";

      echo "</form>";
      echo "</div>";
    }
    ?>

    <?php include("viewsource.php"); ?>
  </body>
  </html>
