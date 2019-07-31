<?php
include 'templates/header.php';
require 'phpmailer/PHPMailerAutoload.php';
if (isset($_SESSION['username']))
{
if ($_SERVER['REQUEST_METHOD'] == 'POST')
{
  $name = $_POST['name'];
  $lname = $_POST['lastname'];
  $senderEmail = $_POST['email'];
  $comments = $_POST['comments'];
  $mail = new PHPMailer;
  $mail->isSMTP();
  $mail->SMTPAuth = true;

  require 'config.php';

  $mail->addAddress('insert email');
  $mail->FromName = $senderEmail;
  $mail->Subject = "Comments from $name $lname";
  $mail->Body = $comments;
  $mail->addReplyto($senderEmail);

  if (!$mail->send())
  {
    print '<h3 style="color: red;"> Unable to send email!</h3>';
  }
  else
  {
    print '<h3 style="color: green;"> Email sent.</h3>';
  }
}
else
{
  print
  '<form method="post">
  <label for="name">Name:</label>
  <input name="name" type="text" size="25" required/> </br>
  <label for="lastname">Last Name:</label>
  <input name="lastname" type="text" size="25" required/> </br>
  <label for="email">Email: </label>
  <input name="email" type="email" size="25" required/> </br>
  <label for="comments">Comments:</label>
  <textarea name="comments" cols="10" rows="5" required></textarea></br>
  <input type="submit" class="button1" name="submit" value="Submit" formaction="email.php"/>
  </form>';
}
}
else
{
  print '<p>Please <a href="login.php">login</a> to access the contact form.</p>';
}
include 'templates/footer.php';
?>
