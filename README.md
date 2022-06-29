### Getting started
* Install and configure JDK 11
* Install and configure [Apache Maven 3.6.0+](http://maven.apache.org/)
* Download and start the latest [Selenium standalone server](http://www.seleniumhq.org/download/)
* Download the latest version of [Eclipse](http://www.eclipse.org/downloads/) and install [TestNG plugin](http://testng.org/doc/download.html)
* [Read Carina documentation](https://zebrunner.github.io/carina/)

### Import to Eclipse
If generation is successfully complete, you would see a new project folder with a name equal to the artifactId attribute specified during generation, so navigate to that folder (where pom.xml is located) and execute the following Maven task:
```
mvn clean eclipse:eclipse
```
By executing this command, Maven should resolve all dependencies, downloading required libraries to your local repository and generating Eclipse classpath. Before importing new project to Eclipse, you should link your IDE to your Maven repository by executing the following task:
```
mvn -Dworkspace=<path_to_workspace> eclipse:configure-workspace
```
Here you have to specify the absolute path to the Eclipse workspace. After that, restart Eclipse IDE. Now you can import generated projects such as "Existing Java Project" into Eclipse IDE.
Generate Eclipse workspace using command:
```
mvn clean eclipse:eclipse
```

### Run tests
```java -jar -Dwebdriver.chrome.driver="C:\chromedriver.exe" selenium-server-standalone-3.141.59.jar
mvn clean test -Dsuite=



Cases for AQA course


Verify right position all elements on login page
Steps
1. open site
Expected results
Username field above password field
Login btn below password field


Verify product item card
Steps
1. open site
2. login
Expected results
Products page is opened
Card contains:
 - image,
 - product name,
 - product description,
 - price,
 - ADD TO CART btn


Verify drop down filter menu
Steps
1. open site
2. login
Expected results
Products page is opened
Menu contains:
 - Name (A to Z)
 - Name (Z to A)
 - Price (low to high)
 - Price (high to low)


Verify drop down filter menu
Steps
1. open site
2. login -> Products page is opened, default filter is "Name (A to Z)" is selected
3. select another filter -> another filter is selected


* Verify all products sorted by alphabetical
Steps
1. open site
2. login
Expected results
Products page is opened
Default filter is "Name (A to Z)"
All products is sorted by alphabetical

