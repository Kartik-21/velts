<?php

require_once 'dbconfig.php';

$conn = mysql_connect("$host","$username","$password");
mysql_select_db("$dbname");

$email =$_POST['v1'];

if($email!="")
{

		$sql = "SELECT * FROM tbl_register WHERE Email='$email'";

		$result = mysql_query($sql);

		if (mysql_num_rows($result) > 0) 
		{
							$row = mysql_fetch_array($result, MYSQLI_ASSOC);
							$pass=$row['Password'];	
							$name=$row['Name'];	
							
		}
		else
		{
			$sql = "SELECT * FROM tbl_employee WHERE Email='$email'";

			$result = mysql_query($sql);

			if (mysql_num_rows($result) > 0) 
			{
							$row = mysql_fetch_array($result, MYSQLI_ASSOC);
							$pass=$row['Password'];	
							$name=$row['Name'];	
							
			}
			else
			{
				echo "There is no account with this Email";
				$pass="";
			}
		}
		
		if($pass!="")
		{
//echo $pass
			require 'PHPMailerAutoload.php';

$request ="yes";
$email12 =$email;
$pass12 =$pass;
$name12 =$name;


$mail = new PHPMailer;

//Send mail using gmail

    $mail->IsSMTP(); // telling the class to use SMTP
    $mail->Host = "smtp.gmail.com"; // sets GMAIL as the SMTP server
    $mail->SMTPAuth = true; // enable SMTP authentication
    $mail->Username = "spectraskinfosoft@gmail.com"; // GMAIL username
    $mail->Password = "xxxxxxx"; // GMAIL password
	$mail->SMTPSecure = "tls"; // sets the prefix to the servier
    $mail->Port  = 587 ; // set the SMTP port for the GMAIL server
    

//Typical mail data
$mail->SetFrom("youremail@gmail.com","Admin");
$mail->AddAddress($email12, "User");

$mail->Subject = "Your Login Password";

if($request=='yes')
	{

		//$mail->Subject = "Approved";
		$mail->Body = " Hello, $name12 \n Your your password is: $pass12  \n Thank you.";
	}
	

try
	{
		$mail->Send();
		echo "Sent";

//	header('location:View_Request.php');
	
	} 
	catch(Exception $e)
	{
		//Something went bad
		echo "Fail";
//	header('location:View_Request.php');
	
	}

		}

		}


?>

