<?php
date_default_timezone_set("Asia/Kolkata");
$con = mysqli_connect('localhost','skinfoso','e#EY1C9ip)c1C3','skinfoso_velts');
session_start();
$user=$_SESSION["user"];
if($user==""){
header("location:login.php");
}
$sql = "SELECT * FROM mav ORDER BY createdat DESC";
$result=mysqli_query($con,$sql);
$row=mysqli_fetch_assoc($result);
?>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="refresh" content="10" />
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/canvasjs.min.js"></script>

<title> Vehicle Enhance Location Tracking System </title>
</head>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<br>
<a class="btn btn-primary btn-block" href="logout.php">Logout</a>
<br />

<a class="btn btn-primary btn-block" href="index.php">Monitor vehicle</a>
<br />

<a class="btn btn-primary btn-block" href="vehicle_map.php">Track vehicle Location</a>
<br />

</td>
</tr>
</table>


<table style="width:100%" border="1">

				<tr>

					<td colspan="5" valign="middle" width="100%" align="center">
					<h3> Vehicle Enhance Location Tracking System</h3></td>
					
				</tr>

			</table>

			

</html>




<?php
$con = mysqli_connect('localhost','skinfoso','e#EY1C9ip)c1C3','skinfoso_velts');


$sth = mysqli_query($con, "SELECT * FROM mav ORDER BY createdat DESC ");

?>


<table width="100%" border="1">

	<tr>

	<th><h3>  MEMS  </h3></th>

	<th><h3>ALCOHOL</h3></th>

	<th><h3>  VIBRATION  </h3></th>
	
	

	<th><h3>Date </h3></th>
		<th><h3>TIME </h3></th>


	</tr>


<?php while($row = mysqli_fetch_assoc($sth))
	{
?>
<tr>
<th><?php echo $row['acc']; ?></th>
<th><?php echo $row['acl']; ?></th>
<th><?php echo $row['vib']; ?></th>

<th><?php echo $row['createdat']; ?></th>
<th><?php echo $row['currtime']; ?></th>


</tr>
<?php 
} 
?>

</table>
	
