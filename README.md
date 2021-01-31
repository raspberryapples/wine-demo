# wine-demo

How to run:

install yarn
install react

open wine-demo-react folder
yarn start

open Exercise1 folder
install maven dependencys from pom
open src\main\java\com\spring\rest folder
run Application.java

if the localhost port used is different to 8080 and 3000
reactHost in the WineController.java file in Exercise1 and the apihost in configurations.js in wine-demo-react will need to be changed.

Improvements:
- A more scalable way of storing and sorting the data could be used in Exercises1. A simple ArrayList was used to store the data for simplicity.
- Lazy loading could be implemented in the querys for queries with large amounts of data. 
- For a larger project, the react components should be split into smaller components such as the table within the product page.
- See more could be made more advanced to implement lazy loading, a simple counter was used in the creation of the current one.

![Image1](Image1.PNG)
![Image2](Image2.PNG)
