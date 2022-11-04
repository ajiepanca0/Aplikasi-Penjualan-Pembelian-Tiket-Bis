<?php

$phone=$_GET['phone'];

include 'koneksi.php';
$query = mysqli_query($database,"SELECT * FROM admin WHERE phone='".$phone."'");

$hasil = mysqli_fetch_array($query);

echo json_encode(array(
    'name'=>$hasil['name'],
    'email'=>$hasil['email'],
    'phone'=>$hasil['phone'],
    'password'=>$hasil['password']

));