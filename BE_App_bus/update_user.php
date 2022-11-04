<?php

include 'koneksi.php';



    $phone = $_POST['phone'];    
    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = $_POST['password'];
  


    $sql = "UPDATE user SET name='$name', email='$email',password='$password'  WHERE phone=$phone";
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