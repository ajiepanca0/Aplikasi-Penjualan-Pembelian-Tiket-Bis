<?php

$id_booking=$_GET['id_booking'];

include 'koneksi.php';
$query = mysqli_query($database,"SELECT * FROM booking WHERE id_booking='".$id_booking."'");

$hasil = mysqli_fetch_array($query);

echo json_encode(array(
    'id_booking' => $hasil['id_booking'],
    'name' => $hasil['name'],
    'phone' => $hasil['phone'],
    'destination' => $hasil['destination'],
    'date' => $hasil['date'],
    'time' => $hasil['time'],
    'bus' => $hasil['bus'],
    'price' => $hasil['price']

));