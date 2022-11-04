<?php

$id_departure=$_POST['id_departure'];

include 'koneksi.php';
$query = mysqli_query($database,"DELETE FROM departure WHERE id_departure='".$id_departure."'");

if($query){
    echo json_encode(array(
        'status'=>'data_terhapus'
    ));
}
else{
    echo json_encode(array(
        'status'=>'data_gagal_terhapus'
    ));
}