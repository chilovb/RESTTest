# API spec
## Product API (v1)

This document outlines the API specification for the `/products` endpoint of a product management service.

### Technologies Used

* Spring Boot
* Spring MVC

### Base URL

`/products`

### Authentication

**Not specified** in this code snippet. Implement appropriate authentication mechanisms (e.g., JWT, OAuth) if needed.

### Error Handling

All API endpoints return a status code indicating the success or failure of the operation. Specific error messages are provided in the response body for failed requests.

* **200 OK:** Successful operation
* **400 Bad Request:** Invalid request syntax or parameters
* **404 Not Found:** Resource not found (e.g., product with specific ID)
* **409 Conflict:** Resource already exists (e.g., creating product with duplicate name)
* **500 Internal Server Error:** Unexpected server error

### Data Model

The API utilizes a `Product` model with the following properties:

* **name (String):**  Name of the product (likely a country code)
* **postalCodeFmt (String):**  Format of the postal code for the product's region
* **regex (String):**  Regular expression for validating postal codes for the product's region


### Endpoints

#### 1. Get Country Information by Code (GET /products/load/{countryCode})

* **Description:** This endpoint retrieves information about a country using an external API and stores the code in the `productRepository`.
* **Path Variable:**
    * `countryCode (String)`: The two-letter country code to retrieve information for.
* **Request:** No request body required.
* **Response:**
    * **Success (200 OK):** Returns a `Product` object with the retrieved information (placeholders used in the example code).
    * **Error (400 Bad Request):**  Invalid country code format (should be 2 characters).
    * **Error (500 Internal Server Error):**  Error while fetching data from the external API.

**Example Request:**

```
GET /products/load/US
```

**Example Response (Success):**

```json
{
  "name": "xx",
  "postalCodeFmt": "fmt",
  "regex": "rg"
}
```

#### 2. Find Products by Country Code (GET /products/code/{code})

* **Description:** This endpoint retrieves a list of products (likely countries) matching the provided country code.
* **Path Variable:**
    * `code (String)`: The country code to search for.
* **Request:** No request body required.
* **Response:**
    * **Success (200 OK):** Returns a list of `Product` objects matching the code.
    * **Success (200 OK, empty list):** No products found for the code.
    * **Error (400 Bad Request):** Invalid code format (implementation not shown).

**Example Request:**

```
GET /products/code/NL
```

**Example Response (Success - Single Product):**

```json
[
  {
    "name": "xx",
    "postalCodeFmt": "fmt",
    "regex": "rg"
  }
]
```


#### 3. Get All Products (GET /products/all)

* **Description:** This endpoint retrieves a list of all products currently stored.
* **Request:** No request body required.
* **Response:**
    * **Success (200 OK):** Returns a list of all `Product` objects.
    * **Success (200 OK, empty list):** No products currently stored.

**Example Request:**

```
GET /products/all
```

**Example Response (Success - Multiple Products):**

```json
[
  {
    "name": "xx",
    "postalCodeFmt": "fmt",
    "regex": "rg"
  },
  {
    "name": "yy",
    "postalCodeFmt": "fmt2",
    "regex": "rg2"
  }
]
```


#### 4. Get Product by ID (GET /products/{id})

* **Description:** This endpoint retrieves a specific product by its unique identifier (ID).
* **Path Variable:**
    * `id (Long)`: The unique ID of the product to retrieve.
* **Request:** No request body required.
* **Response:**
    * **Success (200 OK):** Returns a single `Product` object with the matching ID.
    * **Error (404 Not Found):** Product with the specified ID not found.

**Example Request:**