<?php
header('Content-Type: application/json');
include('db.php');

// Consulta SQL
$sql = "select nom from Programadors where pais like 'Estats Units'"; // Ajusta a la teva taula i columnes
$result = $conn->query($sql);

$usuaris = array();

if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $usuaris[] = $row;
    }
} else {
    echo "0 resultats";
}

// Retornar les dades en format JSON
echo json_encode($usuaris);

// Tancar la connexió
$conn->close();
?>
