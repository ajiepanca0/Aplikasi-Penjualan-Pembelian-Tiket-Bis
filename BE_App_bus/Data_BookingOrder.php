<?php

require_once('koneksi.php');

$query = "SELECT * FROM booking ORDER BY id_booking DESC";


$sql = mysqli_query($database, $query);

if($sql){
$result = array();
    while($row = mysqli_fetch_array($sql)){
        array_push($result, array(
                'id_booking' => $row['id_booking'],
                'name' => $row['name'],
                'phone' => $row['phone'],
                'destination' => $row['destination'],
                'date' => $row['date'],
                'time' => $row['time'],
                'bus' => $row['bus'],
                'price' => $row['price']

        ));
    }

    echo json_encode(array('BookingOrder'=>$result));
}


?>