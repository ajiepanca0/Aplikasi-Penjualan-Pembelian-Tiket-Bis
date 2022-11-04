<?php

 
$databasehost = 'localhost';
$username = 'root';
$pw = '';
$namadb = 'bus_app';
 
$database = mysqli_connect($databasehost, $username, $pw, $namadb);

if(!$database){
    echo " koneksi Gagal";

}
 
?>