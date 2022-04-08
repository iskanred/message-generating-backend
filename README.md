# message-generating-backend

Simple server application that accepts POST request, generates random message and its sha256 hashcode and saves it to
DB. Then, generated data can be obtained by GET request.

---
## How to run
To run the application create PostgreSQL database with following parameters:

    name = simple
    username = admin
    password = admin
    url = postgresql://localhost:5432/simple

After that run the following command at the project directory level:

    java -jar server.jar

The server is ready to user.

## How to use

* To create a random message of 32-chars length send POST request to:
    
        https://{your_ip}:8080/message

* To obtain this message and its sha256 hashcode send GET request to the same address:

        https://{your_ip}:8080/message