docker network create emp-net

docker run -d --name devopsdb --network emp-net \
  -e MYSQL_ROOT_PASSWORD=root123 \
  -e MYSQL_DATABASE=employee_dynamic_db \
  -e MYSQL_USER=emp_user \
  -e MYSQL_PASSWORD=emp456 \
  mysql:8.0

docker run -d --name emp --network emp-net -p 1122:8080 \
  -e SPRING_DATASOURCE_URL="jdbc:mysql://devopsdb:3306/employee_dynamic_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true" \
  -e SPRING_DATASOURCE_USERNAME=emp_user \
  -e SPRING_DATASOURCE_PASSWORD=emp456 \
  -e SPRING_JPA_HIBERNATE_DDL_AUTO=update \
  emp
