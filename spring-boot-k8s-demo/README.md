# GeeksClub - A social platform for geeks

## How to run?

### To run application locally:

`./mvnw spring-boot:run`

### To deploy on Pivotal Cloud Foundry:

Login into PCF: `cf login -a https://api.run.pivotal.io`

To check available services: `cf marketplace`

create service(s) (postgres database) required for application: 

`cf create-service <SERVICE> <PLAN> <YOUR-SERVICE-NAME>`

`cf create-service elephantsql turtle geeksclubdb`

configure application properties in manifest.yml file.

```
    ---
    applications:
      - name: geeksclub
        buildpack: java_buildpack
        path: target/geeksclub-1.0.0-SNAPSHOT-exec.jar
        services:
          - geeksclubdb
```

Push the application to PCF: `cf push`


## Monitoring

Assuming you run the application using `docker-compose up`, the geeksclub app is running on http://localhost:18080, Prometheus is running on http://localhost:9090 and Grafana is running on http://localhost:3000.

Now go to Grafana Home page at http://localhost:3000 and login with admin/admin.

Create a datasource with Name=prometheus and Type=Prometheus, URL=http://localhost:9090, Access=direct and click on "Save & Test".

Import existing Dashboard configurations (https://grafana.com/dashboards/4701 and https://grafana.com/dashboards/5373).

Now you can view various aspects of your application.

## JVM Metrics 

![JVM Metrics By Prometheus and Grafana](/docs/images/JVM-Micrometer-grafana.png)

## Application Metrics

![Application Metrics By Prometheus and Grafana](/docs/images/App-metrics-grafana.png)

## Key features of GeeksClub:

* Profile Management
* Job Board
* Tech Feed
* Book Reviews
* Announcements

### Profile Management
Users can register and create their technical profile with the following info:
* Experience with technologies
* Current Working Company (Optional)
* Blog/Website
* GitHub Profile
* Twitter/LinkedIn/StackOverflow Handles
* Looking for Job Opportunities
* Authored Books
* Conference Talks Given
* Interests

### Job Board
Displays list of job openings posted by community members.
* Registered users can post job openings in their/other companies.
* Moderators should approve these job posting before they appear to everyone.
* Can set start time and end time for job openings

### Tech Feed
Displays a list of links to blog articles submitted by community members.
* Community members can submit links to blog articles.
* Members can up vote or down vote a link
* Hide links if they cross down-vote threshold

### Book Reviews
Members can write reviews of the books.
* Moderators should approve these book reviews before they appear to everyone.
* Members can add comments to reviews

### Announcements
Members can submit any tech related announcements like
* New version release of a framework
* New Book published
* Meetup/Conference schedules
