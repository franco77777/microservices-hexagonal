config-service: config server y config client en los microservicios 
sirve para que los datos del aplication.properties de cada proyecto se cargen desde el servidor, para ello hay 
que usar repositorios en git
Es el que dice como configurar las rutas de los servidores, declarando quien se registrara en eureka y de cual
sera la puerta de enlace (gateway)
_descargar spring cloud starter bootstrap y tambien a microservicios(5/8:25)
_agregar la version cloud a los microservicios y el dependency managment

eureka: eureka server y eureka client
es el que puede otorgar ips dinamicas y ademas permite levantar varios servidores

gateway: gateway
es el que se encargar de redirigir las peticiones a diferentes destinos y del balanceo de carga (load balancer)