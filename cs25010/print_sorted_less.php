<?php
//PRINTS TABLE ITEMS CHEAPER THAN THE USER ENTERED VALUE
//Set up connection
    $con=pg_connect("host=db.dcs.aber.ac.uk port=5432
    dbname=teaching user=csguest password=r3p41r3d");
    if (!con)
    {
      die('Could not connect: ' . pg_error());
    }


    $res = pg_query ($con, "select count(ref) from music");
    $a=pg_fetch_row($res);
    $input = $_POST['sort_price'];
    echo "<table class='index'>\n<thead>\n<tr>\n";
    echo "<th>Title</th><th>Artist</th><th>Album</th><th>Price</th><th></th>\n";
    echo "</tr>\n</thead>\n<tbody>\n";
    $res=pg_query($con, "SELECT title, artist, album, price, ref, composer,
      genre, label, description from music WHERE (price < ('$input')) ORDER BY ref");
    //Very long, so I get everything except enteredby and also have control
    //over order of data

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

      echo '<td><input type="checkbox" name="basketlist[]" value="'.$reference.'"></td>';
      echo "</tr>\n";
    }
    echo "</tbody>\n</table>";
?>
<?php include("viewsource.php"); ?>
