<?php
    include 'koneksi.php';
    $nomor = $_GET['phone'];

    $query = "SELECT * FROM booking WHERE phone='".$nomor."'";

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
    
        echo json_encode(array('BoMe'=>$result));
    }
?>    