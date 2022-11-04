<?php

include "koneksi.php";

$id_departure = $_POST['id_departure'];
$destination = $_POST['destination'];
$date = $_POST['date'];
$time = $_POST['time'];
$name_bus = $_POST['name_bus'];
$price = $_POST['price'];




$sql = " INSERT INTO departure (id_departure,destination,name_bus,date,time,price)  VALUES 
 ('$id_departure','$destination', '$name_bus','$date','$time','$price')";


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

