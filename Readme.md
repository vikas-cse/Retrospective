# Retrospective Application

Description
---------------

This is a Spring boot application which allows users to create Retrospective for a scrum
 and user wise feedback can be added to each retrospective.
 
 Exposed endpoints
 ----------------------------
|Method| Endpoint| Remarks  |
|------|---------|----------|
|POST  |/retrospectives  | create retrospectives
|GET   |/retrospectives?pageSize={pageSize}&currentPage={currentPage} | Get Retrospective list. Default page size is 10.
|GET   |/retrospectives/{retrospectiveName} | get retrospective by name
|POST  |/retrospectives/{retrospectiveName}/Feedback | Add feedback to given retrospective
|PATCH |/retrospectives/{retrospectiveName}/Feedback/{feedbackId} | Update Feedback based on Id provided in response at creation time.
|GET   |/retrospectives/search?queryDate={date} | Search retrospectives by date. E.g. ?queryDate=2024-01-14 

 
 Technical specifications
 -------------------------
 
 This is a Spring Boot application that exposes different endpoints for user to manage retrospectives.  
 
 I have used Spring tool suite to develop this project. I used tomcat webserver to deploy this app which is integrated with the STS and
 very light weight for running these small apps.  
 
 For Bean validation hibernate-validator is used which provides necessary annotations to validate a POJO bean with properties like
 @NotNull and @NotEmpty. 
 
 For adding support for both XML and JSON as request and response type, jackson-dataformat-xml library is used that operates based on
 Content-Type header in request for request payload and Accept header to define response payload type.
 
 Logback is used for Logging, it is known for its high performance.
 
 Mockito is used for adding test cases.
 
 Examples
 -------------------------
  **POST /retrospectives**
  
  **Payload:**
 ``{
    "summary": "Scrum ended on 14th Jan. ",
    "date": "2024-01-14",
    "users": ["UserA", "UserB"],
	"name":"product_release_scrum_1"
   }``

**POST /retrospectives/{retrospectiveName}/Feedback**

**Payload:**
``{
    "userName": "UserA",
    "body": "All the tasks were delivered on time.",
    "type": "positive"
  }``

**PATCH /retrospectives/{retrospectiveName}/Feedback/{FeedbackId}**

**Payload:**
``{
    "body": "All the tasks were delivered on time.",
    "type": "positive"
  }``

 Some Additional Changes
 ------------------------------
* record or separate DTO class should be used instead of same Entity object to avoid exposing any internal info to the user.    
* More Junits to be added.  
* Instead of one generic Exception, more exceptions can be added based on error type and with proper http status code.   