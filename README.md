# E-commerce application for restaurants built with Spring framework
## Instructions for running the app

- Import the dumps in MySQL/MariaDB from Dumps folder
    -
- Insert the following code in Tomcat server.xml
    -
    ```
    <Resource auth="Container"
    driverClassName="com.mysql.jdbc.Driver" global="jdbc/maindb" maxIdleTime="30000" maxPoolSize="20" minPoolSize="5" 
    name="jdbc/maindb" password="admin" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/on_call?useSSL=false" username="on_call_admin"/>

    <Resource auth="Container" 
    driverClassName="com.mysql.jdbc.Driver" global="jdbc/ordersdb" maxIdleTime="30000" maxPoolSize="20" minPoolSize="5" name="jdbc/ordersdb" password="admin" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/users_orders?useSSL=false" username="on_call_admin"/>
    ```
- Insert the following code in Tomcat context.xml
    -
    ```
    <ResourceLink name="jdbc/maindb" 
              global="jdbc/maindb"
              auth="Container"
              type="javax.sql.DataSource" />
    <ResourceLink name="jdbc/ordersdb" 
              global="jdbc/ordersdb"
              auth="Container"
              type="javax.sql.DataSource" />
    ```

- Edit the bean with the id  **mailSender** from spring-mvc-crud-demo-servlet.xml and uncomment the **someService.sendMail** method from `CookController`
    -