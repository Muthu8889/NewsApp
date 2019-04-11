# NewsAppBoilerplateJava

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 6.2.5.

## Estimated Efforts to complete this project is 20-25 hours

The below are mandatory

1. Angular as front end
2. Spring boot as backend
3. CI/CD has to be implmented
4. Authentication to be done by JWT
5. End to end test cases [e2e] are compulsory
6. Unit testing for back end is compulsory


## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

## Command used to generate this project
Front-end Project is originally generated using Angular CLI, and backend is built in Spring Boot using Start.spring.io.

## The NEWS API to be used as data source
- To get category wise news use the following end point. [Category News endpoint]
(https://newsapi.org/v2/top-headlines?category=<<news_category>>&apikey=<<api_key>>&page=1)

- To get trending news use the following endpoint. [Top Headlines endpoint]
(https://newsapi.org/v2/top-headlines?country=in&apikey=<<api_key>>&page=1)

- To search for any news based on serach text . [News search endpoint]
(https://newsapi.org/v2/everything?q=<<search_text>>&apiKey=<<api_key>>&language=en&page=1)


## To register for an API key, follow these steps:

You need to signup to [NEWSAPI] (https://newsapi.org/register).

- After registration, API key is generated for you.
- 


- 
## Important points/instructions to be followed

1.	Must have added all sprints in the same repository. Please don’t create any additional repository and keep pushing the changes into the repository reported in very first iteration.
2.	Must add .gitignore file in your repository to avoid uploading any compiled/ third party binaries.
3.	Must define the steps in README file to reproduce the images used for docker.
4.	Must add your Mentor as developer in your repository.
7.	Use port number 8081 for API and 8089 for Authentication layer.
8.	Code must be appropriately/descriptively commented in all layers.
9.	Error/exception handling must be done to make it user friendly. There should not be any console logs in your application.

P.S :- You need to generate the API_KEY for the above endpoints and replace 
`<<api_key>>` with it.
