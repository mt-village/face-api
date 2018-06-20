# about
wrap implementation for
https://azure.microsoft.com/ja-jp/services/cognitive-services/face/

# setting
edit src/main/resources/application.yml

```
server.port: 8080 <- change port if you want
face-api.key: REPLACE_TO_YOUR_KEY <- your face API subscription key
```

you can get subscription key here
https://azure.microsoft.com/ja-jp/try/cognitive-services/?api=face-api

# execute

```
mvn clean package -DskipTests=true
java -jar target/face-api.jar
```

# try it
if you want detect attributes
```
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{"url":"REPLACE_TO_IMAGE_URL"}' \
'http://localhost:8080/detect'
```

if you want fun-score
```
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{"url":"REPLACE_TO_IMAGE_URL"}' \
'http://localhost:8080/detect/funscore'
```

you need replace the url's value