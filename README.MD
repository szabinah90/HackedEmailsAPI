# Have I Been Pwned API communicating with ElasticSearch

## Spring Boot / Thymeleaf Application

This program is using the Have I Been Pwned API to gather data about e-mail address leaks for analytical purposes.

It communicates with an ElasticSearch cluster:
Checks if a queried address exists under the index, and uploads it (with the associated data) or updates the document, respectively.

Running:

(1) Clone the github repo.

(2) Install and configure your ElasticSearch cluster and edit the resources/application.properties file as necessary.

(3) Open in an idea, set the gradle wrapper and import dependencies.

(4) Navigate to the root of the project and run "./gradlew build && java -jar build/libs/hackedEmails-1.0-SNAPSHOT.jar"

(5) Open localhost:8080/leaked-email in your browser.

(6) Conduct search.
