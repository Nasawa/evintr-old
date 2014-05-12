<?php
$con = mysql_connect("###################","###################","###################");
if (!con)
{
die('Could not connect: ' . mysql_error());
}

if(count($_POST[Verification_Number]) < 1 && count($_POST[Description]) < 1)
{
mysql_close($con);
}
else
{
$to = "evintr@evintr.com, " . $_POST["Email_Address"];
$subject = "Event added";
$table = "event";
if($_POST["Public"] == "Private")
	$table = "private";
if($_POST["Price_Info"] == 'Cash')
	$_POST["Price_Info"] == 'GCash';
mysql_select_db("evintr", $con);
mysql_query("INSERT INTO $table (Verification_Number, Event_Name, Event_ZIP_Code, Event_Address, Price_Info, Price, Ticket_Link, Email_Address, Phone, Description, Start_Date, End_Date, Regular, Rate, eType, Subtype, ImageURL1, ImageURL2, ImageURL3, ImageURL4, ImageURL5, ImageURL6)
VALUES ('$_POST[Verification_Number]', '$_POST[Event_Name]', '$_POST[Event_ZIP_Code]', '$_POST[Event_Address]', '$_POST[Price_Info]', '$_POST[Price]', '$_POST[Ticket_Link]', '$_POST[Email_Address]', '$_POST[Phone]', '$_POST[Description]', '$_POST[Start_Date]', '$_POST[End_Date]', '$_POST[Regular]', '$_POST[Rate]', '$_POST[eType]', '$_POST[Subtype]', '$_POST[ImageURL1]', '$_POST[ImageURL2]', '$_POST[ImageURL3]', '$_POST[ImageURL4]', '$_POST[ImageURL5]', '$_POST[ImageURL6]')");
$ID = mysql_insert_id();

$body = "Thank you for adding an event on evintr. It's people like you that allow us to continue serving the community. To view or share your event, just navigate to or copy: http://evintr.com/page.html?ID=" . $ID . " .\nIf you need to make changes, you can do that here:  http://evintr.com/edit.html?ID=" . $ID ."&Verification_Number=". $_POST["Verification_Number"] . "\n\nThanks for your support!\n    -evintr Team";

if ($_POST["Public"] == "Private")
	$body = "Thank you for adding an event on evintr. It's people like you that allow us to continue serving the community. Since this event is private, only those you give the link to can view it. To view or share your event, just navigate to or copy: http://evintr.com/page.html?private=1&ID=" . $ID ."&Verification_Number=". $_POST["Verification_Number"]  . " .\nIf you need to make changes, you can do that here:  http://evintr.com/edit.html?private=1&ID=" . $ID . "\n\nThanks for your support!\n    -evintr Team";
	
//mail($to, $subject, $body);

mysql_close($con);
}

header("Location: http://evintr.com/results.html?type=All&search=" . $_POST[Event_ZIP_Code] . "&go=Go%21");
exit;
?>