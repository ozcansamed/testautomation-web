# UI Test Automation

This repository contains automated UI tests against a static web application.

## Technologies Used:
 - Java
 - maven
 - Selenium
 - Cucumber BDD
 - JUnit
 - Docker

## How to run locally

Before using IDE's run configurations, please add variables inside [.env](.env) file as Environment Variables.

For IntelliJ IDEA, if you add those variables into Cucumber Java configuration template, you can easily run test within IDE without worrying about env variables.

You can also use docker compose to run test against static web apps and/or deployed version.

### Prerequisites

It will be nice to have;
- [Java 11](https://openjdk.org/install/),
- [maven](https://maven.apache.org/install.html)
- google-chrome browser

installed at your local env to run these tests with your desired IDEs.

> **NOTE:** [.env](.env) normally should be git ignored, but it is left in the git history to make local testing easier.
> In normal cases, values inside `.env` file should only be placed in CI/CD environments.

### With docker

To run docker commands you need to have docker and docker-compose installed at your local env.
To install docker you can refer to [here](https://docs.docker.com/get-docker/).

You need to install first a http server app to serve the static content.
Apache httpd can be installed from [here](https://httpd.apache.org/docs/2.4/install.html)

If you want to target static files inside [web-app](./web-app) rather than the netlify deployed one, you just need to change references for `web.abnamro.ci.URL` to `web.abnamro.initial.URL` in [step_definitions](src/test/java/com/abnamro/step_definitions).

To run the tests;
```shell
docker-compose up -d --build
```

With that docker compose command, you will be starting 3 services;
- tester (testing framework service),
- web-app (static app to test, used as is without any changes),
- web-app-improved (static app to test, with small additions to failures in the UI).

After some time tests will run and generate reports. You can see the created services list by using this command;
```shell
docker ps
```

If you want to follow the logs of the testing framework, identify the container id of the `tester` service and run the following command;
```shell
docker logs --follow <container_id>
```

When you are done with the services and the testing, you can stop the containers by running;
```shell
docker-compose down
```

## Test Framework Structure

There are 2 main layers for BDD approach (which is used in the current implementation);
- Business layer,
- Implementation layer.

### Business Layer

This layer contains test scenarios written Gherkin language which is so close to plain English.
This layer helps non-tech people to understand the automated test scenarios.

There are 3 main features that can be found in the following paths;
- [Login](src/test/resources/features/login.feature)
- [Home page](src/test/resources/features/homePage.feature)
- [Sign out](src/test/resources/features/signout.feature)

### Implementation Layer

All the test scenarios are triggered by the [RoadRunner](src/test/java/com/abnamro/runners/RoadRunner.java) class.

All the code implementations can be found in [step_definitions](src/test/java/com/abnamro/step_definitions).

Web elements are located in [pages](src/test/java/com/abnamro/pages).