<?php
//Code to handle sessions and basket

//Handles sessions
session_save_path("/aber/kpf/tmp");
session_start();

//Username input
if (isset($_POST['username']))
{
  $_SESSION['username']=$_POST['username'];
  header('Location: http://users.aber.ac.uk/kpf/cs25010');
}
$gotusername=(($_SESSION['username']));

//Logout
if (isset($_POST['logout']))
{
  session_destroy();
  header('Location: http://users.aber.ac.uk/kpf/cs25010'); // Force page reload after logout
}

//BASKET FUNCTIONS

//Delete Items From Basket
if (isset($_POST['deleteitems']))
{
  $delete_list = $_POST['deletelist'];
  $input = $_SESSION['basketlist'];

  foreach($delete_list as $value){
    //$delete_val = $value;
    $key = array_search($value, $input);
    if (false !== $key) {
      unset($input[$key]);
    }
  }

  $_SESSION['basketlist'] = $input;

}

//Add Items To Basket
function set_basketlist($basket){
  if (!empty($basket)){
    if (empty($_SESSION['basketlist'])){
      $_SESSION['basketlist'] = $basket;
    } else {
      $_SESSION['basketlist'] = array_merge($basket, $_SESSION['basketlist']);
    }
  }
}

//Check If Basket Empty
function get_basketlist_status(){
  return isset($_SESSION['basketlist']);
}

?>
<?php include("viewsource.php"); ?>
