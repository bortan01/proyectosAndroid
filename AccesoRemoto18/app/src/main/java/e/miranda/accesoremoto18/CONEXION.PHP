<?php

$conexion = new mysqli('comues.com', 'comuesco_ma14049', 'MsA1!C%_kb(*', 'comuesco_ma14049');

if ($conexion->connect_errno) {
    echo "Error de conexion";
}

$opcion = $_REQUEST["opcion"];
if($opcion == "consultar"){
     $criterio = $_REQUEST["criterio"];

    $result = $conexion->query("select * from datos where nombre like '%".$criterio."%'");
    if($result){
        while ($fila = $result->fetch_object()){
            $datos[]= $fila;

        }
        echo json_encode($datos);
    }

}

if($opcion == "guardar"){
    $nombre = $_REQUEST["nombre"];
    $telefono = $_REQUEST["telefono"];

    $consulta  = "insert into datos values (null, '".$nombre."', '".$telefono."')";
    $resultado = $conexion->query($consulta);
}


if ($opcion == "eliminar") {
    $iddatos = $_REQUEST["criterio"];

    $consulta = "delete from datos where iddatos = " .$iddatos;
    $resultadoX = $conexion->query("select * from datos");

    if ($resultadoX) {
        while ($filaX = $resultadoX->fetch_object()) {
            $datos[] = $filaX;
        }
        echo json_decode($datos);
    }
}

?>