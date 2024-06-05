#!/bin/sh

# Đợi MySQL khởi động (tùy chỉnh thời gian nếu cần thiết)
sleep 30

# Nạp file SQL vào MySQL
mysql -u root -p root apartments </docker-entrypoint-initdb.d/apartment.sql

#* mysql -u root -p apartments
#* mysql -u root -p apartments </docker-entrypoint-initdb.d/apartment.sql
#* mysql -u root -p -e "DROP DATABASE IF EXISTS apartments;" &&  mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS apartments;"
#* mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS apartments;"




