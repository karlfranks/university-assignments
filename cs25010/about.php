<?php include("session.php"); ?>
<!DOCTYPE html>
<html>
<head>
  <title>karlTunes - About</title>
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
    <p>For this assignment I have implemented various php files which conform to the
      assigned tasks (eg printing out all of the results).</p>
    <p>Some of this code has been broken down to separate files (for example a separate
      file for printing all of the elements in the table) however this is perhaps
      not the most efficient way to have done this, especially since I have two
      files for different use cases (when showing items greater than an entered
      price and less than, which should probably be in one file with some form
      of switch perhaps to select greater or less than in the SQL query).</p>
    <p>Also in terms of inefficent code, my full table is displayed on one page,
      with a sorted table on another. I would have preferred these to display
      on the same page, but due to time limitations I have simply implemented
      them on separate pages.</p>
    <p>Probably the part of the assignment which took the most time was the JavaScript
      validation of the user's email address and credit card number. It took me
      quite a long time to get this to work, having spent a long time studying code
      people have shared online on sites like StackOverflow and example code
      provided by Annie Shaw. In the end, I was able to get this code to work, after
      testing it for along time in a rather rudimentary way of adding "alert()" calls
      that would print things like the user's inputted email address to check it
      was getting the email address in the function correctly , for example.</p>
    <p>Speaking of testing, I have validated all of my pages with the W3C's validator
      however this required opening the page myself, viewing the source and copying this
      source code into the validator (as it takes HTML input). I still have a few errors,
      when printing the table it prints an & instead of the HTML escape code, because it
      is stored as & in the table and I decided I did not have enough time to attempt to
      rectify this, especially as it displays correctly.</p>
    <p>I have also tested this website in multiple browsers (Safari, Chrome and Firefox)
      and all display it as intended, and it is interesting to notice where each browser
      interprets different tags differently.</p>
    <p>In terms of problems, the most obvious is special characters not being displayed
      correctly in the table, and I am not entirely sure how to fix it since my table
      code was adapted from <a href='http://users.aber.ac.uk/ais/examples/phpdb/managemusic.txt'>
      this example</a> and my line where it prints the data has not been changed, but their
      code displays the special characters properly.</p>
    <p>Also due to time limitations of focusing on getting the code to work, before
      focusing on beautifying my site, my site is very barebones and simple in design
      (but I do not see this as a bad thing, it may be simple but it is not ugly,
      and arguably I would prefer a site to be functional and simple looking
      than very aesthetically pleasing, but limited functionally)</p>
    <p>Due to some problems with getting the View Source button to work as intended,
      I have provided .txt versions of all of my PHP files, links of which can be found
      <a href='source.php'>here</a></p>
    <p>Overall, I feel I have done quite well on this assignment, given my focus
      on making it functional over aesthetically pleasing and as far as I can tell
      my website fully meets the requirements. There are of course areas I can see
      which, if this was a project of my own, I would add or improve (for example,
      I would add JavaScript validation to other text fields on my site, not just
      on the checkout page - to check the user has entered a valid number in the
      sorting field and also not attempted to inject rogue SQL). If I had a lot
      more time, I would focus on making the site more dynamic, so it could work
      on much smaller screens such as phones.</p>
    <p>Declaration of originality: I declare that the contents of this site are entirely my own.</p>
    <p>Disclaimer: The information provided on this and other pages by me, Karl Franks
       (<a href="mailto:kpf@aber.ac.uk">kpf@aber.ac.uk</a>), is
      under my own personal responsibility and not that of Aberystwyth University. Similarly,
      any opinions expressed are my own and are in no way to be taken as those of A.U.</p>

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
