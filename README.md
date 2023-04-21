# Southern Art Soap

A full-stack e-commerce web application built with Java Spring MVC with basic CRUD capabilities. In order to better mimic a "real world" application we based our project off an [Etsy site](https://www.etsy.com/shop/SouthernArtSoap), and all images/product information were used with the seller's permission.

The functionality can be tested on the demo site [here](https://southernartsoap.herokuapp.com/).

## Features
- Online product catalog with separate product detail pages
- Users who sign up can have products saved to shopping cart
- Users can increase/decrease the quantity of items in cart
- Users can delete items from cart
- Filter products by category
- Search functionality that returns products based on relevant titles
- Checkout powered by Stripe

## Technologies
- Front-end: HTML5/CSS3, Bootstrap 4, Thymeleaf
- Back-end: Java, Spring Boot, Spring JPA, Spring Security, Lombok, H2 Database

## Future Improvements
- Have an anonymous cart that persists across browser restarts so users are able to add items to the cart without logging in
- Store order information in the database and have a customer order history
- Have a protected admin route for managing product listings and orders
- Additional product filter/sort options (e.g., by price)

## Lessons Learned
- Utilizing MVC architecture to effectively separate application concerns
- Practicing Git workflows (working off branches, creating/merging pull requests, code reviews)
- Becoming comfortable working with SQL databases
- Using Trello kanban board to manage/track our project (view our Trello board live [here](https://trello.com/b/rIOWWEi0/southernartsoap))
- Don’t overplan and end up having to aggressively cut features to complete the project within the allotted timeframe
- More efficiently solving problems/troubleshooting
- Knowing when to ask for help
- Good communication is invaluable

## Database Schema
![db schema](https://user-images.githubusercontent.com/26291536/102041635-f7b62000-3d9d-11eb-975d-8d6281094e6c.png)

### Schema Justification
The speciality soaps are made on demand. There is no inventory to track.

## Build
Clone the repository:
``` bash
git clone https://github.com/julia-kim/SouthernArtSoap.git
cd SouthernArtSoap
```

Set the following environment variables in application.properties:
| Variable            | Where                                                  |
|:--------------------|:-------------------------------------------------------|
| `STRIPE_PUBLIC_KEY` | Find [here](https://dashboard.stripe.com/test/apikeys) |
| `STRIPE_SECRET_KEY` | Find [here](https://dashboard.stripe.com/test/apikeys) |

Run the application:
```
mvnw clean install
mvnw spring-boot:run
```

Now run the service in the browser (in a separate terminal window):
```
curl localhost:8080
```

## Authors
Julia Kim<br/>
Jared Langson - [LinkedIn](https://www.linkedin.com/in/jared-l-4bb4a657/)<br/>
Olga Ozolina<br/>
