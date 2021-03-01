# Title
QBO Invoice Scheduler

# Description
As part of QBO services we allow a paying user
to schedule his invoice payments in order to pay his debt on time.

# Technologies
This system is a REST API using maven and Java Spring Boot.
The Database engine is MySQL.

# Launch
In order to run the system:
Open Terminal.
navigate to the project folder.
exec command "mvn clean install" to build the project.
exec command "mvn spring-boot:run" to run.

When running locally the url is "http://localhost:8080/qbo-scheduler-app"

# REST API
Create User:
POST /users
Request fields: firstName,lastName,email.
Response: User details (JSON).

Get User:
GET /users/{userId)
Response: User details (JSON).

Create Invoice:
POST /invoices
Request fields: amount,description,dueDate,companyName,customerEmail.
Response: Invoice details (JSON).

Get Invoice:
GET /invoices/{invoiceId)
Response: Invoice details (JSON).

Get all Invoices:
GET /invoices
Response: List of all Invoices details (JSON).

Get Invoice Status:
GET /invoices/status/{invoiceId)
Response: Invoice Status details (JSON).

Create Invoice Schedule:
POST /schedule
Request fields: invoiceId,payDate.
Response: Invoice Schedule details (JSON).

Get Invoice Schedule:
GET /schedule/{invoiceScheduleId)
Response: Invoice Schedule details (JSON).

Cancel Invoice Schedule:
DELETE /schedule/{invoiceScheduleId)
Response: Operation Details.
