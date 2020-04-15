<?php
$con = mysqli_connect('localhost','skinfoso','e#EY1C9ip)c1C3','skinfoso_velts');
if(mysqli_query($con, "TRUNCATE abc"))// TRUNCATE binnn
	echo 'Data Cleared Sucessfully. <a href="index.php">View Map</a>';
else
	echo 'Try Again. <a href="index.php">View Map</a>';
?>