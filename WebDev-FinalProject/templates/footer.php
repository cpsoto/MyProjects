<!-- END CHANGEABLE CONTENT. -->
</main>

<footer container class="siteFooter">
  <p>Design uses <a href="http://concisecss.com/">Concise CSS Framework</a></p>
<p class="float-right"><?php // Print the current date and time...

// Now print the date and time:
print date('g:i a l F j');
?></p>
</footer>

</body>
</html><?php // Script 8.12 - footer.html #3

mysqli_close($dbc);
// Send the buffer to the browser and turn off buffering:
ob_end_flush();
?>
