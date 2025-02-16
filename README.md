Este repositorio contiene un proyecto desarrollado con Spring Boot utilizando Java 17. El proyecto sigue una arquitectura de microservicios, permitiendo escalabilidad, mantenimiento y despliegue independiente. Cada microservicio gestiona una parte específica del dominio de negocio, garantizando modularidad y flexibilidad

ESTRUCTURA DEL PROYECTO

El proyecto está dividido en varias carpetas y módulos:

paymentchainparent: Módulo padre del proyecto que contiene la configuración común y las dependencias compartidas por todos los microservicios.

infraestructuradomain: Maneja la infraestructura y aspectos comunes entre los microservicios. Contiene:

-ConfigServer: Implementación de un servidor de configuración centralizado utilizando Spring Cloud Config Server.

-EurekaServer: Registro y descubrimiento de microservicios mediante Netflix Eureka.

-SpringBootAdmin: Microservicio para la monitorización de los demás microservicios utilizando Spring Boot Admin.

Businessdomain: Contiene la lógica de negocio y se divide en los siguientes microservicios principales:

-Customer: Gestiona la información de los clientes con operaciones CRUD. 

-Product: Administra los productos asociados a los clientes con operaciones CRUD.

-Transaction: Controla las transacciones realizadas por los clientes con operaciones CRUD.

config-server-repo: Carpeta que almacena los perfiles de configuración, utilizada por el ConfigServer para proveer configuraciones externas a los microservicios.

COMUNICACIÓN ENTRE MICROSERVICIOS

La comunicación entre los microservicios se realiza utilizando WebClient en el microservicio Customer para interactuar con los microservicios Product y Transaction. 

Con la adición de Eureka, los microservicios ahora pueden registrarse y descubrirse dinámicamente, eliminando la necesidad de configurar manualmente las URL de los servicios.

ConfigServer permite centralizar la configuración de los microservicios, facilitando la gestión de ajustes en diferentes entornos.

SpringBootAdmin facilita la monitorización en tiempo real del estado de los microservicios, proporcionando información sobre su rendimiento y disponibilidad.


DOCUMENTACIÓN

Se ha implementado SpringDoc para generar documentación automatizada de las APIs REST. Esto permite visualizar y probar los endpoints de cada microservicio a través de Swagger UI.

Las entidades del proyecto han sido documentadas para mejorar la comprensión del modelo de datos y facilitar el mantenimiento del sistema.

PRÓXIMOS PASOS

Implementar Spring Security para mejorar la seguridad.

Dockerización de los microservicios para facilitar el despliegue y la escalabilidad.
