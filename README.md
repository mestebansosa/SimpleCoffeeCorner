### Simple CoffeeCorner application

* Technologies used:  
  * java 11 
  * maven project created with Intellij  
  * junit 4 for unit tests.

* According to the requirements the following data model has been created
  * model:
    * Item: products of the Coffee Corner, with four ITEM_TYPE: BEBERAGE, SNACK, EXTRA and BONUS and sevaral ITEM_NAME
    * Receipt: The receipt with the total to pay (including the bonus or discounts if any)
  * dao (CRUD operations to do on the model:
    * ItemDao
  * service (ReceiptService to generate a receipt. Contains the business logic to apply the bonus or discounts in the receipt)

* Testing:
  * Junit 4 unit tests
  * TDD. I tried to follow the TDD approach (add test, run all tests, write code, run all tests, repeat)

* No logging, no application.properties, no exception handling included.
  
* Software design
  * For the bonus/discount consider the minor price of the items in the offer.
  * The customer order is coming in a text file with the following format:
    * format of each line: ITEM_NAME, quantity
    * ITEM_NAME must be one of the following:
      * _COFFEE_SMALL, COFFEE_MEDIUM, COFFEE_LARGE, ORANGE_JUICE_ -> type __BEBERAGE__
      * _BACON_ROLL_ -> type __SNACK__
      * _EXTRA_MILK, FOAMED_MILK, SPECIAL_ROAST_COFFE_ -> type __EXTRA__
    * quantity is the number of the specific item in the order

* Installation
  * maven clean install (will execute the unit tests and generate the jar file)
  * maven clean install -DskipTests (will generate the jar file)
  * copy from target directory the SimpleCoffeeCorner-1.0-SNAPSHOT.jar file to your directory
  * copy the examples files: order5beverage.txt, order1beverage1snack2extra.txt and order5beverage1snack2extra.txt to your directory in order to test the application

* Execution (with java 11)
  * java -jar SimpleCoffeeCorner-1.0-SNAPSHOT.jar <fileName>
  * where <fileName> is the file that contains the customer order (like in the examples files)
  
* Some Examples:
./order1beverage1snack2extra.txt  


    Date: 2021-03-17 09:15:10
    COFFEE_SMALL        : 2,50
    BACON_ROLL          : 4,50
    SPECIAL_ROAST_COFFE : 0,90
    EXTRA_MILK          : 0,30
    EXTRA_FREE          : -0,30   <-- applied the minor price of the 2 extras
    Total               : 7,90


./order5beverage.txt


    Date: 2021-03-17 09:15:30
    COFFEE_SMALL        : 2,50
    COFFEE_MEDIUM       : 3,00
    COFFEE_LARGE        : 3,50
    COFFEE_LARGE        : 3,50
    ORANGE_JUICE        : 3,95
    BEVERAGE_FREE       : -2,50    <-- applied the minor price of the 5 beverages
    Total               : 13,95


order5beverage1snack2extra.txt


    Date: 2021-03-17 09:16:32
    COFFEE_SMALL        : 2,50
    COFFEE_MEDIUM       : 3,00
    COFFEE_LARGE        : 3,50
    COFFEE_LARGE        : 3,50
    ORANGE_JUICE        : 3,95
    BACON_ROLL          : 4,50
    SPECIAL_ROAST_COFFEE: 0,90
    EXTRA_MILK          : 0,30
    BEVERAGE_FREE       : -2,50     <-- applied the minor price of the 5 beverages
    EXTRA_FREE          : -0,30     <-- applied the minor price of the 2 extras
    Total               : 19,35
