# Example Java daemon
An example of the Apache Commons Daemon in action. I originally wanted to create an end-to-end working example using SystemD / SystemCtl, until I realised that this is not possible in a containerised environment.

This example steps through building, deploying and starting a Java project as a daemon, before sending it a kill signal, to allow the user to observe it shutting down gracefully.

### Prerequisites
* Docker
* Git

### Setup
1. Clone the repos:
   * `git clone https://www.github.com/edwinek/example-daemon`
2. Change into repos dir and make the build script executable:
   * `cd ExampleDaemon`
   * `chmod +x build.sh`
3. Run the script:
    * `./build.sh`
    * This will create the Docker image, start a container with an interactive bash terminal.
    
### Test
1. From the bash terminal on the Docker container, run the `start-daemon.sh` script.
    * The service will start in the background.
2. Tail the service's log, to see that it's running:
    * `tail -f /var/log/example-daemon/output.log`
3. From a second terminal window, connect to the same container:
    * `docker exec -ti daemon_container bash`
4. Find the PIds of the `jsvc` process:
    * `ps -aux`
5. Stop the `jsvc` process using the kill command and the PId:
    * eg. for PId 12 `kill 12`
6. In the original window, observe the service logging that it has received the shut down message / has shut down.
    
###  Tidyup
1. You can exit the `tail` session by using `Control-C`
2. Then you can close the connection to each of the Docker bash sessions by using `Control-D`
3. The Docker container can then be stopped with:
   * `docker stop daemon_container`
   * The container will delete itself upon being stopped
4. The image snapshots can be deleted with:
   * `docker rmi example_jsvc:latest`
    
### Project breakdown
#### Docker configuration
* The container runs the minimal image of Ubuntu 18.04, with the OpenJDK Java 8 JDK / runtime as well as the Apache Commons Daemon `jsvc` binary installed, as well. This binary maps OS control signals to a Java interface implemented by the `uk.edwinek.ExampleSystemDService` class.
#### Gradle
* The Gradle `jar` task was modified in three ways:
   1. The output jar path is set to `/opt/example-daemon` on the container
   2. The output build path is set to `/tmp` on the container
       * This is to prevent creating dirs on the host machine owned by the container
   3. All dependencies are bundled in the jar
* The Apache Commons Daemon dependency is included (to supply the interface mentioned above), as well as Spring and the SLF4J logging facade for Log4J2.
#### Log4j2
* The service logs to `/var/log/example-daemon/output.log`
#### Scripts
* `build.sh`
    * Creates the image based on the `Dockerfile`
    * Spins up a detached instance of the container running bash
    * Builds the jar using the `gradlew` wrapper
    * Attaches a bash session to the container
* `start-daemon.sh`
    * Runs jsvc, specifying the Java home path, the classpath to the Apache Commons Jar, the classpath to the example daemon jar itself, and the class that implements the Apache-supplied Daemon interface.
    
### Further considerations
* In a non-containerised environment the start script could be replaced by a service management mechanism such as `system`, `upstart` or `sysvinit`.
* In a production environment may be worth creating a restricted user, specifically for running Java daemons, rather than using root. 