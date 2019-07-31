<?php
define('TITLE', 'Login');
include('templates/header.php');
print '<h2>Login Form</h2>
	<p>Please login to accesss special features. Thank you!</p>';

if ($_SERVER['REQUEST_METHOD'] == 'POST')
{
	if ( (!empty($_POST['username'])) && (!empty($_POST['password'])) ) {
		$username = strtolower(trim(strip_tags($_POST['username'])));
		$password = trim(strip_tags($_POST['password']));
		$loginQuery = mysqli_query($dbc, "SELECT username, password FROM users WHERE username = '$username' AND password = '$password';");
		if (mysqli_num_rows($loginQuery) > 0)
		{
			$_SESSION['username'] = $_POST['username'];
			ob_end_clean(); // Destroy the buffer!
			header('Location: welcome.php');
			exit();
		}
		else
		{ // Incorrect!
			print '<p class="text--error">The submitted username and password do not match those on file!<br>Go back and try again.</p>';
		}
	} else
	{ // Forgot a field.
		print '<p class="text--error">Please make sure you enter both username and a password!<br>Go back and try again.</p>';
	}
	$_POST = [];
}
else
{ // Display the form.
	print '<form action="login.php" method="post" class="form--inline">
	<p><label for="username">Username:</label>
	<input name="username" type="text" size="20"></p>
	<p><label for="password">Password:</label>
	<input name="password" type="password" size="20"></p>
	<p><input type="submit" name="submit" value="Sign in." class="button1"></p>
	</form>';
}
include('templates/footer.php'); // Need the footer.
?>
