<?php include("session.php"); ?>
<!DOCTYPE html>
<html>
<head>
  <title>karlTunes - Source</title>
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
  }
  ?>
  <div id="content">
    List of source files:
    <ul>
      <li><a href='about.txt'>About</a></li>
      <li><a href='basket.txt'>Basket</a></li>
      <li><a href='checkout.txt'>Checkout</a></li>
      <li><a href='confirmation.txt'>Confirmation</a></li>
      <li><a href='index.txt'>Index</a></li>
      <li><a href='login.txt'>Login</a></li>
      <li><a href='menu.txt'>Menu</a></li>
      <li><a href='print_all.txt'>Print_All</a></li>
      <li><a href='print_sorted_greater.txt'>Print_Sorted_Greater</a></li>
      <li><a href='print_sorted_less.txt'>Print_Sorted_Less</a></li>
      <li><a href='session.txt'>Session</a></li>
      <li><a href='sorted.txt'>Sorted</a></li>
      <li><a href='source.txt'>Source (ie this page)</a></li>
      <li><a href='viewsource'>Viewsource</a></li>
    </ul>
    <?php
    //Code to enable a view source button between certain dates
    $viewmonth=date("n");
    if (($viewmonth==12)||($viewmonth<7))
    {
      if (isset($_POST["viewsource"])) {echo"<hr />";highlight_file(__FILE__);}
      else echo('<form action="' . $_SERVER["PHP_SELF"] . '" method="post">
      <p><input type="submit" name="viewsource" value="View source"/></p></form>');
    }
    ?>
  </div>
</body>
</html>
