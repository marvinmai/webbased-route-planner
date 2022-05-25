mvn clean install -DskipTests
chmod +x service/target/service-1.0-SNAPSHOT.jar
## replace path with your correct path to the file "germany.fmi"
java -jar service/target/service-1.0-SNAPSHOT.jar /home/marvin/cloud/Data/Studium/SoSe22/Programmierprojekt/datasets/germany.fmi