<?php
define('TITLE', 'Add Quote');
include('templates/header.php');
print '<h3>Add a new quote!</h3>';

if(isset($_POST['text']) && isset($_POST['author']))
{
  if(isset($_POST['fave']))
  {$fave = 'Y';}
  else{$fave = 'N';}
  $author = mysqli_real_escape_string($dbc, trim(strip_tags($_POST['author'])));
  $text = mysqli_real_escape_string($dbc, trim(strip_tags($_POST['text'])));
  $query = "INSERT INTO quotes (text, author, favorite) VALUES ('$text','$author', '$fave')";
  $add = mysqli_query($dbc, $query);
  if(mysqli_affected_rows($dbc) > 0)
  {
    print "<p>Quote has been added.<br>
    <a style = 'font-size:12px;' href=quotes.php>Return to Quotes.</a></p>";
  }
  else
  {
    print "<p class=text--error>Unable to add quote.</p>";
  }
}

?>
  <form action='add_quote.php' method='post'>
  <label for="author">Author:</label>
  <input type="text" name="author" size="20" required>
  <label for="text">Quote:</label>
  <textarea type="text" name="text" cols="3" rows="3" size="300" required></textarea>
  <input type="checkbox" name="fave" value="Y" > Check to add as a favorite. </input><br>
  <input style = 'font-size:14px;padding:auto;margin-top:5px;' class = 'button--pill' type='submit' name='submit' value='Add quote!'></form>

<?php
include('templates/footer.php'); // Need the footer.
?>
