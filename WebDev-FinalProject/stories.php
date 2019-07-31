<?php
define('TITLE', 'Stories');
include('templates/header.php');
?>
<h1> Stories Uploaded</h1>
<table>
  <tr>
    <th>Name</th>
    <th>Size</th>
    <th>Last Modfied</th>
  </tr>
<?php
$dir = '../uploads';
$stories = scandir($dir);
$username = $_SESSION['username'];

foreach($stories as $item)
{
  if((is_dir($dir.'/'.$item)) AND (substr($item, 0, 1) !='.') AND ($item == $username))
  {
      $subdir = "$dir/$item";
      $subdircontents = scandir($subdir);
      foreach($subdircontents as $sbfiles)
      {
        if( (is_file($subdir.'/'.$sbfiles)) AND (substr($sbfiles, 0, 1) !='.'))
        {
        $fsize = (filesize($subdir.'/'.$sbfiles))/1000;
        $lmod = date('F j Y H:i:s', filemtime($subdir.'/'.$sbfiles));
        print"<tr>
              <td>$sbfiles</td>
              <td>$fsize kb</td>
              <td>$lmod</td>" ;
        }
      }
  }
}
print"</table>";
?>

<?php include('templates/footer.php'); ?>
