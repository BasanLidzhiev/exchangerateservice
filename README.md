# Exchange Rate Service
Сервис, который обращается к сервису курсов валют, и отдает gif в ответ

# Инструкция

Соберите проект из исходного кода 

После сборки и запуска проекта в адресной строке браузера введите: 

http://localhost:8080/?exc_app_id=YOUR_EXC_APP_ID&gif_app_id=YOUR_GIF_APP_ID&currency=USD

Например: 

http://localhost:8080/?exc_app_id=b81b7ba8b8da4d2fbbbe062831ca171e&gif_app_id=rblojEMNbDdBuJhwoqqM64OevRPAyuUo&currency=USD

exc_app_id - https://openexchangerates.org/account/app-ids API id

gif_app_id - https://developers.giphy.com/dashboard/ API id 

currency - валюта с которой сравниваем

P.S. 
С 00:00 до 09:00 сервис работать не будет
