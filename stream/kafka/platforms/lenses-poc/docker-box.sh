docker run -e ADV_HOST=127.0.0.1  -v /home/zimine/repos/clearn/stream/kafka/lenses-poc/lic/license.json:/license.json --rm -p 3030:3030 -p 9092:9092  --net=host lensesio/box:4.0

#-e EULA="http://dl.lenses.io/d/?id=795b0930-5a89-4b54-bab2-dd1a6be61a2b" 
