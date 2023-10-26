# Lagalt Backend
This document provides a comprehensive guide on setting up and running the backend for the Lagalt project.

## Table of Contents
- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Setup & Installation](#setup--installation)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Security & Authentication](#security--authentication)
- [Logging & Auditing](#logging--auditing)
- [Deployment](#deployment)
- [Contributors](#contributors)
- [Acknowledgments](#acknowledgments)

## Overview
The backend of Lagalt is designed to support the seamless, secure, and user-centric system for freelance professionals and employers. It handles data management, authentication, and provides RESTful APIs for frontend integration.

## Technologies Used
- **Framework**: Java 
- **Database**: PostgreSQL
- **Identity Management**: Keycloak
- **Deployment**: Azure Web App, Docker

## Setup & Installation
No need to instalation to test our application, you can just visit
https://www.lagalt.xyz/

If you want to run our application localy, here is an explanation of how to run the backend:
### Prerequisites:
- Install Java JDK
- Install database management system 
- Install Docker (for containerization)

## Clone the Repository

git clone <repository-url>

## Setup the Database
- Create a new database instance named `lagalt_db`.
- Import the provided database schema.

## Environment Variables
Ensure you have set up necessary environment variables such as `DATABASE_URL`, `SECRET_KEY`, etc.

## Running the Application
./gradlew bootRun

## API Endpoints/ API documentation
A detailed list of available API endpoints, their required parameters, and responses can be found there
https://lagalt-case-1.azurewebsites.net/swagger-ui/.

## Database Schema
The database is structured to efficiently store user profiles, projects, comments, and related data. 

## Security & Authentication
- **Secure Communication**: All API requests are transmitted over HTTPS/TLS protocols.
- **User Authentication**: Keycloak was not completed in time.
- **Authorization & Access Control**: Role-Based Access Control (RBAC) was almost implemented.

## Logging & Auditing
Logging mechanisms are in place to record important security-related events, errors, and user activities.

## Deployment
The backend is designed for deployment on Azure Web App using Docker containers. CI/CD pipelines are made with docker, dockerfile, 
github actions and Azure.

## Contributors
- [Bastien Testaniere, Hamza Iqbal, Michal Pajeskta, Jostein Gjertsen]

