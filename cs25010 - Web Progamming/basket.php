<?php include("session.php"); ?>
<!DOCTYPE html>
<html>
  <head>
      <title>karlTunes - Your Basket</title>
      <link href="cs25010.css" type="text/css" rel="stylesheet"/>
      <link rel="icon" type="image/png" href="favicon.png">
  </head>
  <body>
    <form action="basket.php" method="post">

    <div id="content">

      <?php
      if(!$gotusername){
        include("login.php");
      }
      else{
        //Show the menu at the top
        echo'<form action="index.php" method="post">';
        include("menu.php");
        echo "</form>";

        //Add Items to Basket
        if (isset($_POST['additems'])){
          set_basketlist($_POST['basketlist']);
        }

        //Runs if the basket has items
        if(get_basketlist_status()==1){
          $input = implode("' , '", $_SESSION['basketlist']);

          //Set up connection
          $con=pg_connect("host=db.dcs.aber.ac.uk port=5432
          dbname=teaching user=csguest password=r3p41r3d");
          if (!con)
          {
            die('Could not connect: ' . pg_error());
          }


          $res = pg_query ($con, "select count(ref) from music");
          $a=pg_fetch_row($res);

          echo "<table>\n<thead>\n<tr>\n";
          echo "<th>Title</th><th>Artist</th><th>Album</th><th>Price</th><th></th>\n";
          echo "</tr>\n</thead>\n<tbody>\n";
          $res=pg_query($con, "SELECT title, artist, album, price, ref, composer,
            genre, label, description from music WHERE ref IN ('$input') ORDER BY ref");
          $total_price = 0;
            while ($a = pg_fetch_array ($res))
            {
              //variables
              $temp = pg_num_fields($res) - 5;
              $composer = $a[5];
              $genre = $a[6];
              $label = $a[7];
              $description = $a[8];
              $reference=$a[$temp];
              $song=$a[0];
              $total_price = $total_price + $a[3];

              echo "<tr>";
              //Output first column, with more info on hover
              echo "<td>" . htmlspecialchars($a[0], ENT_QUOTES) . "<a href='#'>
              <em>(Hover For Info)</em><div class='tooltipcontainer'>
              <div class='tooltip'><p>". $description . "</p>
              <p>Genre: ". $genre . "</p>
              <p>Label: " .$label. "</p>
              <p>Composer: " . $composer."</p>
              </div></div></a></td>";
              //Output rest of table data
              for ($j = 1; $j < $temp; $j++) {
                echo "<td>" . htmlspecialchars($a[$j], ENT_QUOTES) . "</td>";
              }

              echo '<td><input type="checkbox" name="deletelist[]" value="'.$reference.'"></td>';
              echo "</tr>\n";
            }
            echo "</tbody>\n</table>";

            //Show options
            echo "<p><input type='submit' name='deleteitems' value='Delete Selected Items' /></p>";
            echo "<p>The total price of your order is: &#163;" . $total_price . "</p>";
            $_SESSION['total_price'] = $total_price;
            echo "<p><a href='checkout.php'>Checkout</a></p>";
        }
        else {
          //Displayed if basket is empty
          echo"<p>Your basket is empty</p>";
          echo"<a href='index.php'>Return home to add some music</a>";
        }
      }
      ?>
    </div>
  </form>
  
    <?php include("viewsource.php"); ?>
  </body>
</html>
