<?php
define('TITLE', 'Upload file.');
include('templates/header.php');
?>
<h2 class="welcome">Welcome to The Greatest Page!</h2>
<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST')
{
  $allowed_ext = array('txt','docx','doc','pdf');
  $user = $_SESSION[username];
  $uploadfile = $_FILES['the_file']['name'];
  $file_ext = end(explode('.', $uploadfile));
  if(in_array($file_ext, $allowed_ext))
  {
    if (move_uploaded_file ($_FILES['the_file']['tmp_name'], "../uploads/{$_FILES['the_file']['name']}"))
    {
      print '<p style="color:green;"> File has been uploaded.</p>';
    }
    else
      {
        print'<p style= "color:red;"> Unable to upload file because...';
        switch($_FILES['the_file']['error'])
        {
          case 1:
            print'Upload exceeds upload_max_file_size in php.ini';
            break;
          case 2:
            print'Upload exceeds MAX_FILE_SIZE in HTML form.';
            break;
          case 3:
            print'File was partially uploaded.';
            break;
          case 4:
            print'No file was uploaded';
            break;
          case 6:
            print'No temporary folder found.';
            break;
          default:
            print'Something happened :(';

        }
        print'</p>';
      }
   }
  else
  {
    print "<p style='color:red;'> Unable to upload .$file_ext files. Try again.</p>";
  }
}
?>
<form action="upload.php" enctype="multipart/form-data" method="POST">
<p>Upload file here:</p>
<input type="hidden" name="MAX_FILE_SIZE" value="50000000">
<p><input type="file" name="the_file"></p>
<p><input type="submit" name="submit" class="button1" value="Upload"></p>
</form>
<?php
include('templates/footer.php');
?>
