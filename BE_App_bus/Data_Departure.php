<?php

require_once('koneksi.php');

$query = "SELECT * FROM departure ORDER BY id_departure DESC";


$sql = mysqli_query($database, $query);

if($sql){
$result = array();
    while($row = mysqli_fetch_array($sql)){
        array_push($result, array(
                'id_departure' => $row['id_departure'],
                'destination' => $row['destination'],
                'name_bus' => $row['name_bus'],
                'date' => $row['date'],
                'time' => $row['time'],
                'price' => $row['price']

        ));
    }

    echo json_encode(array('DerpatureBus'=>$result));
}


?>