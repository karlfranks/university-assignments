<?php
  //Code shown on every page (when logged in) to display menu at top
  echo"<div id='banner'><h1>karlTunes</h1>";
  echo"<p>Welcome, $gotusername</p>";
  echo "<table class='menu'>";
  echo "<tr>";
  echo "<td><a href='index.php'>Home</a></td>";
  echo "<td><a href='basket.php'>Basket</a></td>";
  echo "<td><a href='about.php'>About</a></td>";
  echo "<td><button type='submit' name='logout'>Logout</button></td>";
  echo "</tr>";
  echo "</table>";
  echo "</div>";
?>
<?php include("viewsource.php"); ?>
