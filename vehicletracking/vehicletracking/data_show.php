<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> Vehicle Enhance Location Tracking System </title>
</head>

<table style="width:100%" border="1">

				<tr>

					<td colspan="5" valign="middle" width="100%" align="center">
					<h3>Vehicle Enhance Location Tracking System </h3></td>
					
				</tr>

			</table>

			

</html>


<?php
$con = mysqli_connect('localhost','skinfoso','e#EY1C9ip)c1C3','skinfoso_velts');
$sth = mysqli_query($con, "SELECT * FROM plcc");


?>



<table width="100%">
	<tr>
	<td>Lat</td>
	<td>Lon</td>
	<td>PID</td>
	<td>Date & Time</td>
	</tr>

<?php

while($row = mysqli_fetch_assoc($sth)) {
	?>
	<tr>
		<td><?php echo $row['lat'];?></td>
		<td><?php echo $row['lon'];?></td>
		<td><?php echo $row['pid'];?></td>
		<td><?php echo $row['current'];?></td>
	</tr>
	<?php
}
?>
</table>