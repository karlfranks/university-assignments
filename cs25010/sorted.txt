<?php include("session.php"); ?>
<!--Prints the table sorted by user preference-->
<html>
  <head>
    <title>karlTunes</title>
    <link href="cs25010.css" type="text/css" rel="stylesheet"/>
    <link rel="icon" type="image/png" href="favicon.png">
  </head>
  <body>

    <form action="basket.php" method="post">
    <?php
      if (!$gotusername)
      {
        include("login.php");
      }
      else {
        include("menu.php");

        echo"<div id='content'><p><button type='submit' name='additems'>Add Items To Basket</button></p>";
        echo"<p><a href='index.php'>Go home to view all tracks</a></p>";

        $comparison = $_POST['compare'];

        if($comparison=='greater'){
          echo "<p><em>Showing tracks greater than &#163;" . $_POST['sort_price']. "</em></p>";
          include("print_sorted_greater.php");
        }
        else{
          echo "<p><em>Showing tracks less than &#163;" . $_POST['sort_price']. "</em></p>";
          include("print_sorted_less.php");
        }
        include("viewsource.php");
        echo "</div></form>";


      }
    ?>
  </body>
</html>
