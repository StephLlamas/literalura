<h1>📚 LiterAlura</h1>
<p align="center">
<img src="https://img.shields.io/badge/Java-21%2B-%23ED8B00?color=%23ED8B00">
<img src="https://img.shields.io/badge/Spring Boot-4.0-%2388e339?&color=%2388e339">
<img src="https://img.shields.io/badge/License-MIT-0173b4">
</p>

<h3>📝 Descripción</h3>
<p>Aplicación con interfaz de consola interactiva desarrollada en Java + Spring Boot que permite buscar libros mediante el uso de <a href="https://gutendex.com">API Gutendex</a> y gestionar la biblioteca 
  procesando flujos de datos e integrándolos en una base de datos relacional.
<ul>
  <li>Programación Orientada a Objetos.</li>
  <li>Consumo de APIs REST.</li>
  <li>Persistencia de datos JPA/Hibernate.</li>
  <li>Manejo de excepciones.</li>
</ul>
</p>

<h3>⚙️ Funcionalidades</h3>
<ul>
  <li>Conexión en tiempo real al grande catálogo literario de API Gutendex.</li>
  <li>Bucle de navegación.</li>
  <li>Validación de datos.</li>
  <li>Manejo de errores.</li>
  <li>Menú interactivo:
    <ul>
      <li>Buscar libro por título.</li>
      <li>Listar libros registrados.</li>
      <li>Buscar libros registrados por autor.</li>
      <li>Listar autores registrados.</li>
      <li>Listar autores vivos registrados en un determinado año.</li>
      <li>Listar libros registrados por idioma.</li>
    </ul>
  </li>
</ul>

<h3>🏗️ Arquitectura</h3>
<ul>
    <li>model → Modelado de datos y transferencia.</li>
    <li>dto → Récords para la API.</li>
    <li>service → Lógica de negocio y procesamiento.</li>
    <li>repository → Acceso de datos JPA.</li>
    <li>main → manejo del flujo principal de la aplicación e interacción con el usuario.</li>
  </ul>

### 🛡️ Configuración y Seguridad
Para proteger la infraestructura y seguir las mejores prácticas de desarrollo, el acceso a PostgreSQL se gestiona en el archivo <code>application.properties</code>, utilizando variables de entorno:
```properties
# Configuración segura de BD
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${LITERALURA_DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 🚀 Instalación y Uso
1. Clona el repositorio:
```bash
 git clone https://github.com/StephLlamas/literalura.git
```
2. Asegúrate de tener Java 21 o superior.
3. Se requiere Maven 3.5.x
4. Base de datos PostgreSQL.
5. Abre el proyecto en IntelliJ IDEA.
6. Ejecuta el proyecto en <code>LiteraluraApplication.java</code> → <code>Run 'LiteraluraApplication.main()'</code>.
7. Selecciona una opción del menú:<br>
Ejemplo - Buscar libro por título<br>
<img width="608" height="365" alt="image" src="https://github.com/user-attachments/assets/b2bb17ea-dcaa-4c66-9b9e-9cbb4974bdb2" /><br>
<img width="388" height="173" alt="image" src="https://github.com/user-attachments/assets/b949b459-0cd1-4641-8b07-3f155fefc7d5" /><br>
Ejemplo - Listar libros registrados<br>
<img width="919" height="773" alt="image" src="https://github.com/user-attachments/assets/25997fce-514a-40c7-994f-12b1249c7a28" /><br>
Ejemplo - Buscar libros registrados por autor<br>
<img width="780" height="65" alt="image" src="https://github.com/user-attachments/assets/d720e7c6-7f1d-4ee7-bfd5-9a11e9d116e9" /><br>
<img width="562" height="426" alt="image" src="https://github.com/user-attachments/assets/4f1fcf64-aae9-4470-ad6b-7199049e4938" /><br>
Ejemplo - Listar autores registrados<br>
<img width="526" height="619" alt="image" src="https://github.com/user-attachments/assets/00138470-d5ea-4d68-b3d3-8b1fe1935258" /><br>
Ejemplo - Listar autores vivos registrados en un determinado año<br>
<img width="806" height="85" alt="image" src="https://github.com/user-attachments/assets/b25a478a-5672-45f7-b8a5-5852a22973be" /><br>
<img width="612" height="373" alt="image" src="https://github.com/user-attachments/assets/e56fcfbe-f34e-42ba-be29-ea11d6e82d20" /><br>
Ejemplo - Listar libros registrados por idioma<br>
<img width="430" height="249" alt="image" src="https://github.com/user-attachments/assets/78a61d08-5ad1-4c00-b31f-668f7ee305f2" /><br>
<img width="520" height="205" alt="image" src="https://github.com/user-attachments/assets/db9a1ed2-d564-4a94-8bee-f1abab1d5731" /><br>
8. Selecciona la opción salir del menú para cerrar la aplicación.<br>
<img width="600" height="336" alt="image" src="https://github.com/user-attachments/assets/9b8c6ece-506f-4097-95cc-0fd46071f417" /><br>

<hr/>
<em>Desarrollado por <a href="https://github.com/StephLlamas">Estefanía Llamas</a> como parte del programa <strong>Oracle Next Education</strong> impartido por <a href="https://www.aluracursos.com">Alura Latam</a>.</em><br>
© 2025 - Estefanía Llamas
