Payment Chain Project
-Descripción General
Este repositorio contiene un proyecto desarrollado con Spring Boot utilizando Java 17. El proyecto está diseñado bajo una arquitectura de microservicios, donde varios servicios independientes trabajan en conjunto para gestionar la cadena de pagos. Cada microservicio maneja una parte específica del dominio de negocio, permitiendo escalabilidad, mantenimiento y despliegue independiente.

-Estructura del Proyecto
El proyecto está dividido en varias carpetas y módulos:

paymentchainparent: Es el módulo padre del proyecto y contiene la configuración común y dependencias compartidas por todos los microservicios.

infraestructuradomain: Este módulo está planeado para manejar la infraestructura y aspectos comunes entre los microservicios, como configuración de bases de datos, seguridad, o cualquier lógica compartida. Actualmente, este módulo está vacío y se implementará en una fase posterior del proyecto.

businessdomain: Este módulo contiene la lógica de negocio y se divide en tres microservicios principales:

Customer: Gestiona la información de los clientes. Proporciona endpoints CRUD para crear, leer, actualizar y eliminar clientes.
Product: Maneja los productos asociados a los clientes. También incluye operaciones CRUD para administrar los productos.
Transaction: Controla las transacciones realizadas por los clientes. Ofrece operaciones CRUD para registrar y gestionar las transacciones.

-Comunicación entre Microservicios
La comunicación entre los microservicios se realiza utilizando WebClient en el microservicio Customer. Este servicio se encarga de interactuar con los microservicios Product y Transaction para obtener la información relacionada con los productos asociados a los clientes y las transacciones realizadas por estos.

-Próximos Pasos
Implementar el módulo infraestructuradomain
Migrar de H2 a una base de datos relacional
Monitorización de los microservicios
Implementar Spring Security
Dockerización
