<?php

$id_departure=$_GET['id_departure'];

include 'koneksi.php';
$query = mysqli_query($database,"SELECT * FROM departure WHERE id_departure='".$id_departure."'");

$hasil = mysqli_fetch_array($query);

echo json_encode(array(
    'id_departure'=>$hasil['id_departure'],
    'destination'=>$hasil['destination'],
    'name_bus'=>$hasil['name_bus'],
    'date'=>$hasil['date'],
    'time'=>$hasil['time'],
    'price'=>$hasil['price']

));