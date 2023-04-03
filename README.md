# CriptoParser
___
## Description
This app pulls information about cryptocurrency and sorts it every 10 seconds
___
## Features
+ Get min price of any currency
+ Get max price of any currency
+ Create csv file of currency statistic
___
## Structure
+ 3-tier Architecture
+ Controller
+ Service 
+ DAO
___ 
## Quickstart
1. Clone repository
2. Run project (Works only with BTC, ETH, XRP)
3. GET /cryptocurrencies/minprice?name=BTC - You will receive min price for selected currency
4. GET /cryptocurrencies/maxprice?name=BTC - You will receive max price for selected currency
5. GET /cryptocurrencies?name=BTC&page=0&size=10 - You will receive a list of prices parsed with pages
6. GET /cryptocurrencies/csv - Will generate *.csv file with statistic of min and max prices for all 3 currencies
