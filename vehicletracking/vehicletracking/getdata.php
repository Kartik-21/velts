<?php
$con = mysqli_connect('localhost','skinfoso','e#EY1C9ip)c1C3','skinfoso_velts');
$sth = mysqli_query($con,"SELECT * FROM plcc");
$rows = array();
while($r = mysqli_fetch_assoc($sth)) {
    $rows[] = $r;
}
$json_data = json_encode($rows);
echo $json_data;
?>