
# Sky Takeout - Restaurant Management and Ordering System

## Project Link
[GitHub Repository](https://github.com/FKKuser/sky-take-out)

## Project Overview

**Sky Takeout** is a customized management and ordering system designed for restaurant businesses (e.g., restaurants, dining halls) to optimize the management processes and provide an efficient ordering experience for consumers. The system consists of two components:

- **Management Backend**: Designed for internal employees of the restaurant business, it allows the management of categories, dishes, orders, employees, and statistical data. It also features voice notifications for incoming orders, helping staff respond to orders in real-time.
  
- **Mini Program**: Targeted at consumers, this component allows users to browse the menu, add items to the cart, place orders, and make payments, providing a seamless and efficient ordering experience.

## Technical Highlights

### 1. Backend Design and Development
- Responsible for designing and developing the backend architecture to ensure system scalability and stability.

### 2. Database Design
- Handled the design and optimization of the database to ensure efficient data operations and consistency.

### 3. JWT Stateless Authentication
- Implemented **JWT** tokens to achieve stateless single sign-on, preventing session sharing issues in a clustered environment and improving system security and scalability.

### 4. Custom AOP Aspects
- Used custom **AOP** (Aspect-Oriented Programming) to extract common code logic, improving code reusability and maintainability. Also implemented input/output logging and **Metrics** for real-time monitoring.

### 5. Redis Caching
- Employed **Redis** for caching frequently used data such as restaurant lists and menu information, reducing database access and significantly improving query efficiency. Ensured data consistency by synchronizing cache with database updates.

### 6. Swagger Auto-Generated Documentation
- Integrated **Swagger** to automatically generate API documentation, making it easier for developers to test and integrate APIs, thereby improving development efficiency.

### 7. Nginx Load Balancing and Reverse Proxy
- Used **Nginx** as the HTTP server for static resource deployment and to implement **Tomcat** load balancing and reverse proxy, ensuring high concurrency handling.

## Project Architecture
- **Backend Framework**: Spring Boot, Spring MVC, MyBatis
- **Database**: MySQL, Redis
- **Containerization**: Docker
- **Authentication**: JWT
- **Server**: Nginx
- **API Documentation**: Swagger

## How to Use

1. Clone the repository:
   ```bash
   git clone https://github.com/FKKuser/sky-take-out.git
   ```
   
2. Access the API documentation:
   - Open your browser and navigate to `http://localhost:8080/swagger-ui.html` to view the API documentation.

3. Mini Program Experience:
   - Load and run the consumer-facing Mini Program using a Mini Program simulator.

## Contributions

- If you have any questions or suggestions, feel free to submit an issue or pull request.
