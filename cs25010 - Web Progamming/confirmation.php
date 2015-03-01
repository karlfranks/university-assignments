<?php include("session.php"); ?>
<!DOCTYPE html>
<html>
<head>
  <title>karlTunes - Order Confirmation</title>
  <link href="cs25010.css" type="text/css" rel="stylesheet"/>
  <link rel="icon" type="image/png" href="favicon.png">
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
      echo "</form>";

      echo "<div id='content'>";
      echo "Your order has been confirmed, you will receive an email shortly with download details";
      echo "</div>";
    }
  ?>

    <?php include("viewsource.php"); ?>
</body>
</html>
