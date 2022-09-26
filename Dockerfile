FROM maven:3-openjdk-11
WORKDIR /app
COPY . .
CMD ["mvn", "test", "site", "surefire-report:report-only"]