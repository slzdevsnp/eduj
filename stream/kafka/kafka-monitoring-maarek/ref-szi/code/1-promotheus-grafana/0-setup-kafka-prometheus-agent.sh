########
#### KAFKA MACHINE, Zookeeper machine
########
# Download agent & config
cd /home/ec2-user
mkdir prometheus
cd prometheus

wget https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.3.1/jmx_prometheus_javaagent-0.3.1.jar
##newer version
wget https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.14.0/jmx_prometheus_javaagent-0.14.0.jar

wget https://raw.githubusercontent.com/prometheus/jmx_exporter/master/example_configs/kafka-0-8-2.yml
## newer version for kafka 2
wget https://raw.githubusercontent.com/prometheus/jmx_exporter/master/example_configs/kafka-2_0_0.yml



##the yaml file contains rules to export kafka metrics to prometheus

## add this line in systemd/kafka.service
#Environment="KAFKA_OPTS=-javaagent:/home/ec2-user/prometheus/jmx_prometheus_javaagent-0.3.1.jar=8080:/home/ec2-user/prometheus/kafka-0-8-2.yml"
# add new version  Environment="KAFKA_OPTS=-javaagent:/home/ec2-user/prometheus/jmx_prometheus_javaagent-0.14.0.jar=8080:/home/ec2-user/prometheus/kafka-2-0-0.yml"  

sudo vi /etc/systemd/system/kafka.service
# Restart Kafka
sudo systemctl daemon-reload
sudo systemctl restart kafka

#to test kafka metrics 
curl localhost:8080

## on zookeeper machine
cd prometheus
wget https://raw.githubusercontent.com/prometheus/jmx_exporter/master/example_configs/zookeeper.yaml
sudo vi /etc/systemd/system/zookeeper.service
#Environment="EXTRA_ARGS=-javaagent:/home/ec2-user/prometheus/jmx_prometheus_javaagent-0.3.1.jar=8080:/home/ec2-user/prometheus/zookeeper.yaml"
sudo systemctl daemon-reload
sudo systemctl restart zookeeper
curl localhost:8080

########
#### ADMIN MACHINE
########
# Download and install prometheus
cd
wget https://github.com/prometheus/prometheus/releases/download/v2.3.2/prometheus-2.3.2.linux-amd64.tar.gz
wget https://github.com/prometheus/prometheus/releases/download/v2.23.0/prometheus-2.23.0.linux-amd64.tar.gz

tar -xzf prometheus-*.tar.gz
##mv prometheus-2.3.2.linux-amd64 prometheus
ln -sf prometheus-2.3.2.linux-amd64 prometheus

##rm prometheus-*.tar.gz
mkdir arch  &&  mv prometheus-*.tar.gz arch 

cd prometheus
vim prometheus.yml
# default scrape interval 15 secs

./prometheus
# Setup Prometheus SystemD file
sudo nano /etc/systemd/system/prometheus.service