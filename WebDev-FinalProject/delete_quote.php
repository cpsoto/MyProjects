<?php
define('TITLE', 'Delete Quotes');
include('templates/header.php');


if(isset($_GET['id']) && is_numeric($_GET['id']))
{
  $queryDelete = mysqli_query($dbc, "SELECT text, author, id FROM quotes WHERE id = '$_GET[id]';");
  if($queryDelete)
  {
    while ($row = mysqli_fetch_array($queryDelete))
    {
      print"<p>Are you sure you still want to delete this quote?</p>";
      print "<form action='delete_quote.php' method='post'><p>{$row['text']}<br>
      -{$row['author']}<br>
      <input type='hidden' name='id' value='$_GET[id]'>
      <input style = 'font-size:14px;padding:auto;margin-top:5px;' class = 'button--pill' type='submit' name='submit' value='Delete this quote!'></form>";

    }
  }
  else {
    print"<p class=text--error>Error!</p>";
  }
}

elseif(isset($_POST['id']) && is_numeric($_POST['id']))
{
  $query = "DELETE FROM quotes WHERE id = {$_POST['id']} LIMIT 1;";
  $del = mysqli_query($dbc, $query);
  if(mysqli_affected_rows($dbc) == 1)
  {
    print "<p>Quote has been deleted.<br>
    <a style = 'font-size:12px;' href=quotes.php>Return to Quotes.</a></p>";
  }
  else
  {
    print "<p class=text-error>Unable to delete quote.</p>";
  }
}
else
{
  print"<p class=text--error>Unable to find id.</p>";
}

include('templates/footer.php'); // Need the footer.
?>
