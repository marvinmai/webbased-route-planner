Use the script **build.sh** to build the web-application. This builds the springboot backend as well as the 
angular frontend and creates a single jar in webapp/target. 
If required execute **'chmod +x build.sh'** first to make the script executable.

Execute the script **start.sh** to start the webapp, including backend and frontend. Before executing the script, 
replace the path in the last line with the correct path to the "germany.fmi" file on your machine. Wait for the applciation
to log "Data loaded successfully. Service is ready at http://localhost:8083." Then you can access the site at http://localhost:8083.

You can also start the website with the following command:
java -jar webapp/target/webapp-1.0.0.jar [path-to-germany.fmi]