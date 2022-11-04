<?php

include "koneksi.php";

$phone = $_POST['phone'];
$name = $_POST['name'];
$email = $_POST['email'];
$pw = $_POST['password'];



$sql = " INSERT INTO user (phone, name ,email,password)  VALUES 
 ('$phone', '$name', '$email','$pw')";


if ($database){
    
        $simpan = mysqli_query($database, $sql);

        if($simpan){
            echo json_encode(array(
                'status'=>'Regist Successful'
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
