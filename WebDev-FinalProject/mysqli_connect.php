<?php
// Connects to the database

$dbc = mysqli_connect('192.168.64.2', 'web_user', 'webpassword', 'fanclub');

if(!$dbc)
{
   trigger_error ('Could not connect to MySQL: ' . mysqli_connect_error() );
}
else
{
   mysqli_set_charset($dbc, 'utf8');
}
?>
