# Wildlife Tracker
An application that allows Rangers to track wildlife sightings in an area.
## Author
* **Breldan Muturi** 

### -*Initial works*-
- [GithubSearch](https://github.com/Turi-byte/githubsearch) - This is a user profile search app for GitHub where a user can search for users on GitHub and their repositories
- [Quote](https://github.com/Turi-byte/quote) - An app that enables a user create quotes and delete quotes they don't want
- [Delani]( https://github.com/Turi-byte/delani) - A front end of a studio website
- [Diary](https://github.com/Turi-byte/Diary) - A personal diary application where users can write down their thoughts.
- [Hero](https://github.com/Turi-byte/hero) - This app allows you to add heroes and hero squads allowing the user to place heroes in their desired squads.

## Demo


## Getting Started
Clone this repository to your local machine to get started

Github: https://github.com/Turi-byte/wildlife.git

### Prerequisites

You need the following installed on your machine
- java
- JDK -Java Development Kit
- Maven
- Gradle
-An IDE -IntelliJ

To confirm run the following command on linux
```
$ java --version       //java version
$ mvn --version        //maven version
$ gradle --version     //gradle version
```
## Installing

After cloning to your local machine navigate to the folder you cloned into and open it with intellij.
* Navigate into the ``` src/main/java/App.java ``` and click run in intellij.
* Go to your browser and type ``` localhost:4567 ```

## Running the Tests 

Create a test class for running tests in the application.

This is a sample test that tests if the getter method works

```
@Test
public void newHero_getName_String(){
  hero testHero = Hero.setUpNewHero();
  assertEquals("Arnold", testHero.getName());
}
```
## Built With

* [Java](https://www.java.com/) - The language used
* [Intellij Idea](https://www.jetbrains.com/idea/) - Intergated development
* [Spark](http://sparkjava.com/documentation.html#sessions) - Framework


## Contributing
If you want to put out a pull request you first have to send us the sample code that you want to add to our repository for cross-checking before we allow the pull.

## Versioning

We use [Github](https://github.com/) for versioning. This is the first version of this application

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## Acknowledgments

* Hat tip to  ```Stackoverflow```
* Hat tip to  [Brian Marete](https://medium.com/@bmarete/deploying-a-spark-java-app-with-a-postgresql-database-to-heroku-bf54c2e664b8)

