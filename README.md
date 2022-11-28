# Web service
The project is a web service that can authenticate and authorize users. Can handle GET and POST request. Processes no more than one POST request. Creates dynamic content and gives it to the user. Gives static content.

The product structure has been created, in which there is the name of the product, the manufacturer and its quantity. Implemented storage of these structures in the database

Two roles have been created for LoginService
- Manager - is able to add goods and view all added goods
- Guest - is only able to view all added goods.

#### POST method 
should transmit to the web server the name of the product, the company and its quantity. The POST request handler adds a record to the storage with the name of the product, the company and its quantity. If there is no company, then it is created. The company name is unique.

#### GET method 
requests all products that have been added by users. The GET request handler reads all records from the storage and forms a response for the client with them.

A specific URL provides help describing how to work with the web server.