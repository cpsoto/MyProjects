<?php
define('TITLE', 'Registration');
include('templates/header.php');
// Print some introductory text:
print '<h2>Registration Form</h2>
	<p>Register now to get access to extra features!</p>';

// Check if the form has been submitted:
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	$problem = false; // No problems so far.

	// Check for each value...
	if (empty($_POST['first_name'])) {
		$problem = true;
		print '<p class="text--error">Please enter your first name!</p>';
	}

	if (empty($_POST['last_name'])) {
		$problem = true;
		print '<p class="text--error">Please enter your last name!</p>';
	}
	if (empty($_POST['email']) || (substr_count($_POST['email'], '@') != 1) ) {
		$problem = true;
		print '<p class="text--error">Please enter your email address!</p>';
	}
	if (empty($_POST['username'])) {
		$problem = true;
		print '<p class="text--error">Please enter a username!</p>';
	}

	if (empty($_POST['password1'])) {
		$problem = true;
		print '<p class="text--error">Please enter a password!</p>';
	}
	if ($_POST['password1'] != $_POST['password2']) {
		$problem = true;
		print '<p class="text--error">Your password did not match your confirmed password!</p>';
	}

	//SQL query to check if username is already in database.
	$userN = mysqli_real_escape_string($dbc, trim(strip_tags($_POST['username'])));
	$queryUserName = mysqli_query($dbc, "SELECT username FROM users WHERE username = '$userN';");
	if(mysqli_num_rows($queryUserName) > 0)
	{
	$problem = true;
	print '<p class="text--error">Username already in use! Choose a different username.</p>';
	}

	if (!$problem)
	{
		mkdir('../uploads/'.$_POST['username']);



		//implement mysqli_real_escape_string()
		$passW = mysqli_real_escape_string($dbc, trim(strip_tags($_POST['password1'])));
		$hashed_passwrd = password_hash($passW, PASSWORD_DEFAULT);
		$queryInsert = "INSERT INTO users (username, password, user_dir, status, admin) VALUES ('$userN', '$hashed_passwrd', '$userN', 'OPEN', 'N');";
		mysqli_query($dbc, $queryInsert);

		$body = "Thank you, {$_POST['first_name']}, for registering with the The Greatest Page!.";
		print "<p class='text--success'>$body</p>";
		$_POST = [];

	} else {

		print '<p class="text--error">Please try again!</p>';

	}
}
?>
<form action="register.php" method="post" class="form--inline">

	<p><label for="first_name">First Name:</label><input type="text" name="first_name" size="20" value="<?php if (isset($_POST['first_name'])) { print htmlspecialchars($_POST['first_name']); } ?>"></p>

	<p><label for="last_name">Last Name:</label><input type="text" name="last_name" size="20" value="<?php if (isset($_POST['last_name'])) { print htmlspecialchars($_POST['last_name']); } ?>"></p>

	<p><label for="email">Email Address:</label><input type="email" name="email" size="20" value="<?php if (isset($_POST['email'])) { print htmlspecialchars($_POST['email']); } ?>"></p>
	<p><label for="username">Username:</label><input type="text" name="username" size="20" value="<?php if (isset($_POST['username'])) { print htmlspecialchars($_POST['username']); } ?>"></p>
	<p><label for="password1">Password:</label><input type="password" name="password1" size="20" value="<?php if (isset($_POST['password1'])) { print htmlspecialchars($_POST['password1']); } ?>"></p>
	<p><label for="password2">Confirm Password:</label><input type="password" name="password2" size="20" value="<?php if (isset($_POST['password2'])) { print htmlspecialchars($_POST['password2']); } ?>"></p>

	<p><input type="submit" name="submit" value="Register!" class="button1"></p>

</form>

<?php include('templates/footer.php');?>
