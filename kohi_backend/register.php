<?php

    if ($_SERVER['REQUEST_METHOD'] =='POST'){
        $namaLengkap = $_POST['fullname'];
        $usrname = $_POST['username'];
        $mEmail = $_POST['email'];
        $passwrd = $_POST['password'];

        $passwrd = password_hash($passwrd, PASSWORD_DEFAULT);

        require_once 'connect.php';

        $sql = "INSERT INTO user (namaLengkap, usrname, mEmail, passwrd) VALUES ('$namaLengkap', '$usrname', '$mEmail', '$passwrd')";

        if(mysqli_query($conn, $sql) ){
            $result["success"] = "1";
            $result["message"] = "success";

            echo json_encode($result);
            mysqli_close($conn);

        } else{

            $result["success"] = "0";
            $result["message"] = "error";

            echo json_encode($result);
            mysqli_close($conn);
        }
    }
?>