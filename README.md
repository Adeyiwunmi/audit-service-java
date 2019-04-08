# jpay-audit-service



Getting Started
- Pull the project
- Select an environment(prefferably development) using sprin.profiles.active durring start up.
- Fill in the details needed in the properties file, e.g elastic search cluter nodes, cluster name, rabbit mq username etc
- Visit localhost:9200/swagger-ui.html to view the endpoints documentation


- Create an Audit Action e.g User Activities, Payment Settings etc
- Create an Audit Action type (put in information if it has sensitive information or not to make
the system know if it should mask it or not) e.g Changed User Password, Add new Payment Option etc.s


# For the tests

- Enter the properties for the unittest profile.
- Run mvn:test
