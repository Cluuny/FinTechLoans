## Autores
Vicente Matallana -> 202212717  
Sebastian Cañon -> 202127352  
Gabriel Cely -> 202213440  

## Estructura
Las clases del proyecto se encuentran dentro de la ruta `src/java/com/fintechloans`, dicha carpeta a su vez está divida en 3 capas (haciendo uso del modelo MVP), la capa de modelo tiene a su vez 3 carpetas para poder organizar mejore el codigo en donde las carpetas `user` y `product` contienen las clases padres y sus clases hijas para cada uno de los objetos necesarios dentro del proyecto, la tercera carpeta `services` tiene la finalidad de servir como un tipo de API con la cual poder interactura con los usuarios y sus productos.
El objetivo de realizar el proyecto en Maven se debe a la necesidad de usar librerias externas a el core de java para poder manejo a algunas features que queremos implementar, mas especificamente el manejo de archivos `.json` dentro de nuestro proyecto

## ToDo's  
- Calcular riesgo asociado al puntaje del usuario
- Generar codigos para tarjetas virtuales
- Clase para prestamos de casino en noche de juego
- Implementar adquisición de productos
- Implementar sistema de cuotas y saldo en productos
- Implementar pago de cuotas para prestamo regular y tarjeta de credito
- Cancelar prestamo (pagar en su totalidad)
- Diferir prestamo en mas cuotas
- Implementar pasarela de productos para mercados aliados y no aliados