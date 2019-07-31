<?php
define('TITLE', 'Edit Quote');
include('templates/header.php');

mysqli_set_charset($dbc, 'utf8');

if(isset($_GET['id']) && is_numeric($_GET['id']))
{
  $queryUpdate = mysqli_query($dbc, "SELECT text, author, id, favorite FROM quotes WHERE id = '$_GET[id]';");
  if($queryUpdate)
  {

    while ($row = mysqli_fetch_array($queryUpdate))
    {
      if(($row['favorite']) == 'Y')
      {
        $checked = 'checked';
      }
      else{$checked=null;}
      print '<form action="update_quote.php" method="post">
      <label for="quote">Quote: </label>
      <textarea type="text" name="text" cols="3" row="3" size="300" "required">'.htmlentities($row["text"]).'</textarea><br>
      <label for="author">Author: </label>
      <input type="text" name="author" size="20" value="'.htmlentities($row["author"]).'"><br>
      <input type="checkbox" name="fave" value="Y" '.$checked.'> Mark to make favorite</input><br>
      <input type="hidden" name="id" value="'.$_GET["id"].'">
      <input style = "font-size:14px;padding:auto;margin-top:5px;" class = "button--pill" type="submit" name="submit" value="Update"></form>';

    }
  }
  else {
    print"<p class=text--error>Error!</p>";
  }
}

elseif(isset($_POST['id']) && is_numeric($_POST['id']))
{
  if(isset($_POST['fave']))
  {$fave = 'Y';}
  else{$fave = 'N';}

    $author = mysqli_real_escape_string($dbc, trim(strip_tags($_POST['author'])));
    $text = mysqli_real_escape_string($dbc, trim(strip_tags($_POST['text'])));
    $query = "UPDATE quotes SET author='$author', text='$text',favorite='$fave' WHERE id = {$_POST['id']};";
    $update = mysqli_query($dbc, $query);
    if(mysqli_affected_rows($dbc) > 0)
    {
      print "<p>Quote has been updated.<br>
      <a style = 'font-size:12px;' href=quotes.php>Return to Quotes.</a></p>";
    }
    else
    {
      print "<p class=text-error>Unable to update quote.</p>";
    }
 }

else
{
  print"<p class=text--error>Unable to find id.</p>";
}

include('templates/footer.php'); // Need the footer.
?>
