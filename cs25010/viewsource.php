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
