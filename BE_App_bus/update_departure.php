<?php

include 'koneksi.php';



    $id_departure = $_POST['id_departure'];    
    $destination = $_POST['destination'];
    $time = $_POST['time'];
    $date = $_POST['date'];
    $name_bus = $_POST['name_bus'];
    $price = $_POST['price'];


    $sql = "UPDATE departure SET destination='$destination', time='$time',date='$date', name_bus='$name_bus' , price='$price'  WHERE id_departure=$id_departure";
    $query = mysqli_query($database, $sql);


    if($query){
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


?>