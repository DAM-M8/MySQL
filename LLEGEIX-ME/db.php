<?php
$servername = "192.168.121.3"; // o l'adreça IP del teu servidor
$username = "usuari"; // el teu nom d'usuari de la base de dades
$password = "123456"; // la teva contrasenya
$dbname = "bdprova"; // el nom de la teva base de dades

// Crear la connexió
$conn = new mysqli($servername, $username, $password, $dbname);

// Comprovar la connexió
if ($conn->connect_error) {
    die("Connexió fallida: " . $conn->connect_error);
}
?>