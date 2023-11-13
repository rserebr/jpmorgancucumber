# {JSON} Placeholder


[JSONPlaceholder](https://jsonplaceholder.typicode.com/) is free fake API for testing and prototyping.

### Resources

/posts

/comments

/users

### How to execute test scenarios on local?

Clone the project  and execute the comnnad

```
mvn clean test
```

Find generated report locally under

```
target/reports/cucumber
```

or publish it to [cucumber portal](https://reports.cucumber.io/) by adding flag to CucumberOptions  [RunCucumberTest.java](src/test/java/RunCucumberTest.java)

```
publish = true
```
