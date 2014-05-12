<?php
$to = "evintr@evintr.com";
$subject = $_POST['Subject'];
$body = $_POST['Name'] . " writes:\n" . $_POST['Message'];
$headers = "From:" . $_POST['Email'];
mail($to, $subject, $body, $headers);
echo "<script type='text/javascript'>window.location = 'http://www.evintr.com/';</script>";
?>