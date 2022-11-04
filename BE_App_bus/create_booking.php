<?php

include "koneksi.php";

$name = $_POST['name'];
$phone = $_POST['phone'];

$destination = $_POST['destination'];


$date = $_POST['date'];
$time = $_POST['time'];

$bus = $_POST['bus'];
$price = $_POST['price'];




$sql = " INSERT INTO booking (name,phone,destination,date,time,bus,price)  VALUES 
 ('$name', '$phone','$destination','$date','$time','$bus','$price')";


if ($database){
    
        $simpan = mysqli_query($database, $sql);

        if($simpan){
            echo json_encode(array(
                'status'=>'oke'
            ));
        }

        else
        {
            echo json_encode(array(
                'status'=>'gagal'
            ));
        }
    }else{
        echo "KONEKSI GAGAL";
    }

    ?>

