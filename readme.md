Use the script **build.sh** to build and serve the website. 
If required execute **'chmod +x build.sh'** first to make the script executable.

Execute the script **start.sh** to start the webapp, including backend and frontend. Before executing the script, 
replace the path in the last line with the correct path to the "germany.fmi" file on your machine.
The website is available at http://localhost:8080.

You can also start the website with the following command:
java -jar webapp/target/webapp-0.0.1-SNAPSHOT.jar [path-to-germany.fmi]