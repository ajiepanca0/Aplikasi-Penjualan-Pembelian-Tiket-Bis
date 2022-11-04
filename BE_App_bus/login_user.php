<?php

include 'koneksi.php';

$phone = $_POST['phone'];
$password = $_POST['password'];

$query = mysqli_query($database," SELECT * from user where phone='$phone' and password='$password'");

$cek = mysqli_num_rows($query);
$hasil = mysqli_fetch_array($query);

if($cek>0){
    echo json_encode(array(
        'status'=>'Sukses'
    ));
}
else{
    echo json_encode(array(
        'status'=>'gagal'
    ));
}