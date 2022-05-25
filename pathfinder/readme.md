First, replace placeholders in build.sh and execute the script to start the maven build process.

To execute the benchmark of this project use the following command and replace the placeholders:

java -jar target/Benchmark-X.X.jar -graph [...] -lon [...] -lat [...] -que [...] -s [...]
    - graph     Path to germany.fmi file
    - lon       Longitude value for calculation of the nearest node
    - lat       Latitude value for calculation of the nearest node
    - que       Path to germany.que file
    - s         Source node ID for one-to-one shortest path dijkstra

E.g:
java -jar target/Benchmark-1.0.0.jar -graph /home/marvin/cloud/Data/Studium/SoSe22/Programmierprojekt/datasets/germany.fmi -lon 9.098 -lat 48.746 -que /home/marvin/cloud/Data/Studium/SoSe22/Programmierprojekt/datasets/germany.que -s 638394