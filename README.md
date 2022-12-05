# Web service

The project is a web service that can authenticate and authorize users. Can handle GET and POST request. Processes no
more than one POST request. Creates dynamic content and gives it to the user. Gives static content.
___
Args for run Main:
DB url, DB name, DB username, DB password

For example:
jdbc:postgresql://localhost/ jetty postgres postgres
___
http://localhost:3466/info URL provides help describing how to work with the web server.
___
The product structure has been created, in which there is the name of the product, the manufacturer and its quantity.
Implemented storage of these structures in the database

Two roles have been created for LoginService

- Manager - is able to add goods and view all added goods
- Guest - is only able to view all added goods.

#### POST method

http://localhost:3466/products/add?name={name}&organization={organization}&amount={amount}

should transmit to the web server the name of the product, the company and its quantity. The POST request handler adds a
record to the storage with the name of the product, the company and its quantity. If there is no company, then it is
created. The company name is unique.

#### GET method

http://localhost:3466/products/all

requests all products that have been added by users. The GET request handler reads all records from the storage and
forms a response for the client with them.

---
The processing of client requests to the REST API has been rewritten. The list of products is given in the form of JSON.
The product structure class is annotated with Jackson annotations for serialization\deserialization of the object.

#### Another POST method

http://localhost:3466/products/delete?name={name}

has been added, in which the user passes the name of the product. The handler of this POST request
finds a product with the same name, if there is a product, it deletes it and sends OK in response, if not, it responds
with a 404 error to the user.

#### Another GET method

http://localhost:3466/products/byorganization?organization={organization}

has been added, in which the user passes the manufacturer of the product. The handler of this GET
request finds all products with such a manufacturer and sends them to the user.
