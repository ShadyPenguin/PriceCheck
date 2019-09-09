# PriceCheck
REST API used to determine pricing of parking spots

## Local Deployment
### Requirements
- Docker

##### Build
- Docker `docker run --rm -it -v $(pwd):/project -w /project maven mvn package`
- Maven `mvn clean package`

##### Deploy
- Docker `docker run -it -p 8080:8080  -v $(pwd)/target/PriceCheck.war:/usr/local/tomcat/webapps/ROOT.war -v $(pwd)/target/PriceCheck:/usr/local/tomcat/webapps/ROOT tomcat`
- Docker-Compose `docker-compose up`

## Pseudo Integration Testing
You will find a bash script `tester.sh` in the main directory. This script will read `test_data.csv` and issue a `curl`
request to the application for each line and verify the output.