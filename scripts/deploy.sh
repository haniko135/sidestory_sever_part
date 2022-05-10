#!/usr/bin/env bash

docker-compose stop

echo "Copy docker-compose.yml"
scp   C:/Users/Nastya/Desktop/practica_git/Sidestory_server_part_copy/docker-compose.yml  \
      haniko135@51.250.98.54:/home/haniko135/
echo "Success."

echo "Copy data.sql"
scp   C:/Users/Nastya/Desktop/practica_git/Sidestory_server_part_copy/mysql/data.sql \
      haniko135@51.250.98.54:/home/haniko135/mysql
echo "Success."

echo "Copy .jar file"
scp   C:/Users/Nastya/Desktop/practica_git/Sidestory_server_part_copy/target/Sidestory_server_part-0.0.1-SNAPSHOT.jar \
      haniko135@51.250.98.54:/home/haniko135/
echo "Success."

echo "Start server."

ssh haniko135@51.250.98.54 << EOF
    sudo docker-compose up -d
EOF
