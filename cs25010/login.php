<?php
  //Code shown on every page if not logged in
  echo'<form action="index.php" method="post">';
  echo "<table class='menu'><tr>";
  echo '<tr><td><h1 class="center">Welcome to karlTunes, please enter your name to continue:
    </h1></td></tr>';
  echo '<tr><td><input class="center" type="text" name="username" maxlength="20" /></td></tr>';
  echo '<tr><td><input class="center" type="submit" /></td></tr>';
  echo '</form>';
?>

<?php include("viewsource.php"); ?>
