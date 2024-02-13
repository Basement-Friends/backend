# Introduction
This repository is part of the Basement Friends App, originally created for a university project. It includes the backend, which is a REST API built with Java Spring Boot and uses MongoDB as its database.

# Main project objectives
The goal of the project was to create a web application that serves as a platform for displaying the profiles of computer gamers, allowing them to find people with similar game play history, essentially serving as a kind of LinkeIn for gamers. Users enter their personal information upon registration, and the system checks it for accuracy. Personal information includes first name(s), last name(s), email address, gender, a list of nicknames on gaming platforms and online games, date of birth, phone number, and a photo. Users can also provide a description and a list of games they are willing to play. The minimum age for users is set at 16 years old, and age verification is done by the user uploading a photo. Initially, a neural network checks if there is a human in the attached photo, and if so, a second network determines their age.

When a player wants to find a playmate, they can set filters in the search panel. The system then selects candidates based on the filters, and their profiles are displayed. Users can choose to either reject a candidate or send them a message to arrange playing together. If the second option is selected, the rest of the candidates are automatically rejected. Users have access to their message history, and authorization is facilitated through JSON WebToken.

The frontend is developed using Angular, while the backend is implemented in Java using the SpringBoot framework. To enhance image analysis capabilities, the project incorporates the YOLO neural network model, whose code is accessible on the GitHub platform at https://github.com/ultralytics/yolov5. The YOLO model is trained using the free dataset available at https://www.kaggle.com/datasets/sbaghbidi/human-faces-object-detection/. This dataset aids in detecting the presence of humans in images.

If the YOLO model identifies a human in the photo, an additional model is employed to determine the age of the individual, serving as a verification step for the age provided by the user. The second model, a Convolutional Neural Network (CNN), is trained using the free dataset found on the Kaggle platform at https://www.kaggle.com/datasets/frabbisw/facial-age.

For efficient management of dependencies and isolation of the application environment, the entire project is published and deployed using Docker containers. This approach ensures streamlined deployment and facilitates the reproducibility of the application environment.

# Key functionalities of the project
* Login and creating user account
* Ability to send friend requests
* Sending messages to firends
* Viewing profiles from other gamers


# Database Schema
The following picture shows the project database schema:
![image](https://github.com/Basement-Friends/backend/assets/72508414/d57d1d57-0e35-41f1-8e28-2e39a2891fe5)

# Class Diagram

![image](https://github.com/Basement-Friends/backend/assets/72508414/0ba891ee-fa56-47a4-b20a-bffc9f40390e)

## Authentication API

Explore the authentication API using the interactive Swagger documentation.

### Login

#### Endpoint: `/api/auth/login`

- **Method**: POST
- **Request Body**:

  ```json
  {
    "username": "jtest",
    "password": "123"
  }
Response: Successful login
<details>
  <summary>Click to expand</summary>
Try it out


</details>
Register
Endpoint: /api/auth/register
Method: POST

Request Body:

json
Copy code
{
  "email": "alex.smith@email.com",
  "firstName": "Alex",
  "lastName": "Smith",
  "username": "asmith",
  "password": "securePass789"
}
Response: Successful registration

<details>
  <summary>Click to expand</summary>
Try it out


</details>
```




# Technologies used to create the Backend:
* Java 21
* MongoDB
* Spring Boot
* Spring Security
* Spring Data
* Lombok
* JWT Token
* Docker


# Helpfull tools for developing and testing:
* Postmann
* Mongo Express
* IntelliJ Idea
* Data Grip
* Visual Studio Code
* Docker Desktop
