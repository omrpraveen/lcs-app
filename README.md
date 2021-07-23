# LCS Application

In this app, I provided rest api for identifying longest common substring given a list of strings.


## Requirements

For building and running the application you need:

- [JDK 1.8]
- [Maven 3]

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. 
	One way is to execute the `main` method in the `com.lcs.LcsApplication` class from your IDE.
	Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
##Testing on application
Use post man or any other client to validate the request

here I am posting curl example.

```
curl --location --request POST 'http://localhost:8080/lcs' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoY3BAcG9ydGFsLmNvbSIsInNjb3BlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9IQ1BfQURNSU4ifV0sImlzcyI6InBhdGluZXQgcG9ydGFsIiwiaWF0IjoxNjIzMzQ5NTU5LCJleHAiOjE2MjMzNjc1NTl9.iqNPZ10zM8quDc1iXWgHTxhdP4muKBbVIIRtVNXYvPE' \
--header 'Content-Type: application/json' \
--data-raw '{
    "setOfStrings": [
        {
            "value": "comcast"
        },
        {
            "value": "comcastic"
        },
        {
            "value": "broadcaster"
        }
    ]
}'
```
