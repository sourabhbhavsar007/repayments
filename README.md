# repayments
Spring Boot Project to generate Repayment Schedule

# Business Requirements :

In order to inform borrowers about the final repayment schedule, we need to have pre-calculated repayment plans throughout the lifetime of a loan.

To be able to calculate a repayment plan specific input parameters are necessary:

1. Loan amount (principal amount)
2. Duration (number of installments in months)
3. Nominal rate (annual interest rate)
4. Date of Disbursement/Payout ("startDate")


# Project Specifications :

This is a Spring Boot application and we have implemented a web service that has one endpoint to generate a borrower plan via HTTP in JSON.

  **Port**         : By default application runs on port 8080 . Kindly make sure the port is available.
  
  **HTTP Method**  : POST
  
  **Media Type**   : application/json
  
  **Security**     : Currently no security is configured , so no need for any HTTP Basic or Transport layer security .  
  
  **Endpoint : localhost:8080/repaymentschedule (POST)**
  



# Sample Request: 


**Request Payload :** 

```
{
    "loanAmount": "5000",
    "nominalRate": "5.0",
    "duration": 2,
    "startDate": "2022-01-01T00:00:01Z"
}
```

**Response Body :** 
```
[
    {
        "borrowerPaymentAmount": 2515.64,
        "date": "2022-01-01T00:00:01Z",
        "initialOutstandingPrincipal": 5000,
        "interest": 20.83,
        "principal": 2494.81,
        "remainingOutstandingPrincipal": 2505.19
    },
    {
        "borrowerPaymentAmount": 2515.63,
        "date": "2022-02-01T00:00:01Z",
        "initialOutstandingPrincipal": 2505.19,
        "interest": 10.44,
        "principal": 2505.19,
        "remainingOutstandingPrincipal": 0
    }
]
```


# Assumptions : 

There is no need for security as per requirement so any application can access the API.



# Executing the project :

1. To execute, please download the zip of the project or clone the repository.

2. Import the unziped file or cloned repo into IDE of your choice.

3. Run mvn clean install to build and resolve dependencies needed for the application.

4. After build is successful, you can use above endpoints with respective authorization credentials and request payload on Postman.





# Screenshots :




<img width="1470" alt="generate-plan" src="https://user-images.githubusercontent.com/30754286/147372298-43b7e05f-395d-4ab0-9d2c-272a361c17e9.png">




