To execute the benchmark of is project execute the following command an replace the values:

java -jar Benchmark-X.X.jar -graph [...] -lon [...] -lat [...] -que [...] -s [...]
    - graph     Path to germany.fmi file
    - lon       Longitude value for calculation of the nearest node
    - lat       Latitude value for calculation of the nearest node
    - que       Path to germany.que file
    - s         Source node ID for one-to-one shortest path dijkstra

E.g:
java -jar Benchmark-1.0.0.jar -graph /home/marvin/cloud/Data/Studium/SoSe22/Programmierprojekt/datasets/germany.fmi -lon 9.098 -lat 48.746 -que /home/marvin/cloud/Data/Studium/SoSe22/Programmierprojekt/datasets/germany.que -s 638394