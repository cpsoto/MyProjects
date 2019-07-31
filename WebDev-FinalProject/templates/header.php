<?php
session_start();
ob_start();
date_default_timezone_set('etc/GMT+5');
include ('mysqli_connect.php');
?><!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="HandheldFriendly" content="True">
  <title>
    <?php
	if (defined('TITLE'))
  {
		print TITLE;
	} else
  {
		print 'The Greatest Page! by Christian Soto';
	}
	?></title>
  <link rel="stylesheet" type="text/css" media="screen" href="css/concise.min.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="css/masthead.css" />
</head>
<body>

<header container class="siteHeader">
  <div row>
    <h1 column=4 class="logo"><a href="index.php">The Greatest Page!</a></h1>
    <nav column="8" class="nav">
      <ul>
        <li><a href="books.php">Books</a></li>
        <li><a href="quotes.php">Quotes</a></li>
        <li><a href="email.php">Contact</a></li>
        <?php
        if(!isset($_SESSION['username']))
        {print'<li><a href="register.php">Register</a></li><li><a href="login.php">Login</a></li>';}
        else
        {print'<li><a href="upload.php">Upload</a></li>
          <li><a href="stories.php">Stories</a></li>
          <li><a href="logout.php">Logout</a></li>';}
        ?>
     </ul>
    </nav>
  </div>
</header>

  <main container class="siteContent">
  <!-- BEGIN CHANGEABLE CONTENT. -->
