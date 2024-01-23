# School Management Application

## Overview

The School Management Application is a Java-based web application designed to manage school-related information. It provides functionalities for handling staff data, including names, IDs, and addresses.

## Contributors

This project is developed and maintained by the following contributors:

1. [Omoma Ighawosa](https://github.com/Ig-Matrix)
2. [Contributor 2](link-to-github-profile)
3. [Contributor 3](link-to-github-profile)
4. [Contributor 4](link-to-github-profile)
5. [Contributor 5](link-to-github-profile)
6. [Contributor 6](link-to-github-profile)

## Features

- Staff Management: Create, read, update, and delete staff information.
- Database Integration: Utilizes a MySQL database to persistently store staff data.
- Web Interface: Web-based user interface for easy interaction with the application.
- Thymeleaf Templating: Integration with Thymeleaf for server-side rendering of HTML templates.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Thymeleaf
- MySQL

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- MySQL Database

### Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/Ig-Matrix/Group-Two-School-Management-App.git
    cd school-management-app
    ```

2. Configure the database in `application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3. Build and run the application:

    ```bash
    ./mvnw spring-boot:run
    ```

4. Access the application at [http://localhost:8080](http://localhost:8080).

## Contributing

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/new-feature`.
3. Make your changes and commit them: `git commit -m 'Add new feature'`.
4. Push to the branch: `git push origin feature/new-feature`.
5. Submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](/MIT.md) file for details.
