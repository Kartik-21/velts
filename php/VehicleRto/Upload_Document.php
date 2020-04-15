<?php
require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");


    // Get image string posted from Android App
    $base=$_REQUEST['image'];
    // Get file name posted from Android App
    $filename = $_REQUEST['filename'];
  
	$email = $_REQUEST['uid'];
//	echo $uid;
	$utype = $_REQUEST['utype'];
	$title = $_REQUEST['title'];
	
	// Decode Image
    $binary=base64_decode($base);
    header('Content-Type: bitmap; charset=utf-8');

	if($email!="")
	{
			$query = "SELECT * FROM tbl_register WHERE Email='$email'";	
			$result = mysql_query($query);	

			if (mysql_num_rows($result) > 0) 
			{
				$row = mysql_fetch_array($result, MYSQLI_ASSOC);
				$uid=$row['User_ID'];		
			}
			else
			{	
			//	echo"error1";
				$uid="";
			}	
	}
	
	if($uid!="")
	{
			$sql = "INSERT INTO tbl_uploads(User_ID,UType,Title,Image) VALUES ('$uid','$utype','$title','uploads/Documents/$filename')";
			if(mysql_query($sql))
			{
					echo "Added..!!";
			}
			else
			{
					echo"ERROR ";
			}
	}
								
	
	
	// Images will be saved under 'www/imgupload/uplodedimages' folder
    $file = fopen('uploads/Documents/'.$filename, 'wb');
    
	// Create File
	
    fwrite($file, $binary);
    fclose($file);
	
	
		
	
?>