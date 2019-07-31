<?php
define('TITLE', 'Quotes');
include('templates/header.php');
print '<h2>Quotes</h2>';

$queryQuotes = mysqli_query($dbc, "SELECT * FROM quotes ORDER BY date_entered ASC;");

if(isset($_SESSION['username']))
{
print"<a href=add_quote.php><h4 style='color:blue;'>Add A Quote!</h4></a>";
}

if($queryQuotes)
{
  while ($row = mysqli_fetch_array($queryQuotes))
  {
    if($row['favorite'] == 'Y')
    {$favorite = "<ins style='color:red;'>Favorite!</ins>";
    }else {$favorite=null;}

    print "<p>{$row['text']}<br>
    -{$row['author']} $favorite<br>";
    if(isset($_SESSION['username']))
    {
    print "<a style='font-size:14px;' href=update_quote.php?id={$row['id']}>Edit</a>
    <a style='font-size:14px;' href=delete_quote.php?id={$row['id']}>Delete</a></p>";
    }
  }
}
else {
  print"didnt work";
}




include('templates/footer.php'); // Need the footer.
?>
