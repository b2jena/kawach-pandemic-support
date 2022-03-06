# kawach-pandemic-support

A patient, doctor and volunteer networking and management platform for pandemic support.


When we started our program in CGI – India was reeling under the impact of Phase 2 of the Pandemic.
We, like all of you here, in fact, all Indians - went through the pain, trouble, and tribulation that the pandemic brought to us.
As a common citizen, what are the pains we go through during a large-scale pandemic?

The deadly second wave had us racing against time. Frantic calls from attendants of patients with spo2 below 50. Scouting for resources - say ICU beds, medicines, ventilators, injections, oxygen! - and whatnot?
Due to the sudden rise in critical patients, bed availability became scarce. Everybody of us faced this.
It was a common sight of patients running from one hospital to another with the hope of getting beds. More scarce were ICU beds and beds with ventilators.

We also saw the pains of people – running around for Oxygen - as the whole Oxygen supply chain crumbled under huge demand.
And the same for common medicines and medical equipment to treat critical COVID patients.
During such an emergency what would you do?
The immediate thing is to shout for Help.
And we all have noticed that the primary social media platforms, such as Twitter, Facebook, and Instagram are flooded with hundreds and thousands of S-O-S requests.
But sending an S-O-S message doesn’t mean you always get some positive response.
Their responses are unverified or fake and it leads to greater frustration and agony.

Also, taking advantage of the pandemic, there has been a large number of antisocial people trying to scam and dupe people in distress.
So when we - a team of eight - got a chance to address the S-O-S Requests of people hit by the pandemic, we took up the challenge.
Our objective was simple – Provide verified help and assistance to people in distress during a pandemic.
And within 5 weeks of intense hard work, today we are ready to present the first release of our product.
And how do we achieve this?
Well, honestly - with a simple slogan.
In this Pandemic, Let’s be Stronger Together. 

So Everybody here is the first release of the product - Kawach. 
So here is Kawach - a crowdsourcing platform that we can use during any pandemic to help people in distress.
So this is the landing page view of our product. 

The people in need who are looking for sources about the bed, medicine, medical equipment or want to consult a doctor 
 can simply log in to the platform by providing their email id and an OTP will be generated for them. 
This we guess will speed up the process of getting quickly to the resource instead of going through the hassle of registering and then login.

On the other hand, both doctors and volunteers need to register before logging in to our platform.

-The second section of the landing page displays the current status of the pandemic across different places in the country in tabular format. The data for this is fetched from an external API which gets updated regularly


The table below this is for appreciating the volunteers who have contributed authentic information about the availability of resources by displaying their scores and names on this leaderboard

And below are the card for displaying a brief appreciation of what our doctors and volunteers are doing
First and foremost, Government, Health Organizations, and Health NGOs can use Kawach at panchayat, district, state, or even at the national level to provide medical relief during pandemics.

Secondly, any large, midsize, or small business houses and companies can use Kawach within their organizations for their employees.

Lastly, even an individual can use Kawach within his or her network of friends, family members, professional contacts, and relatives.
It’s also important at this point to highlight the stakeholders of Kawach.
The platform my team and I developed, Kawach, has been designed for Patients in distress. So patients are the most critical stakeholder.

Secondly, we have doctors who will be providing consultations to patients.

Last. And the most important of all .. are Volunteers. - these are the key members of our platform
Here in Kawach, a patient can quickly log in with just email and OTP verification [in landing page]
Then The patient will land on the patient dashboard. Now, The patient will have to click on Doctor Consultation - and here is the doctor consultation view.

We designed the system based on the microservices architecture.
Let me explain  the responsibilities of the microservices we designed for this platform

First, we have the User management Service, which is used to register the user as a doctor and volunteer into our platform. The data gets stored in MongoDB and using RabbitMQ, then another set of data(User Credentials) is sent to MySql via Authentication Service.
Secondly, we have User Authentication. Once the Credentials are received from User Management Service via RabbitMQ, the data get stored in MySql for validating the doctor and volunteer.
Next is the Patient OTP service, which will be responsible for authenticating the patients through OTP sent via Email.
Next is, Information Service, which fetches the information from an external API about the COVID cases across the country.
Next is, Resource Request Service, which is the service where the patients can request medical resources from the volunteers in the platform.
Next is, Doctor Consultation Service is the service in which the doctors can provide consultation to the patients.
Next is, Chat service, which is responsible for chatting between the doctors and the patients.
Next is the Volunteer revert service, Which holds the volunteer data and scores the basis of their activity in the platform.
Next is Eureka Service, which helps in providing brief information about the services that are up and running and it will be explained in detail later.
Lastly, we have API Gateway, which helps in handling a single point of contact that will be explained in detail going ahead.
RabbitMQ is a message-broker software that we have used for asynchronous communication between the various microservices in our platform. 

For example, the User Management Service sends the data of newly registered users via RabbitMQ to the User Authentication Service. 
We have used different databases based on the different data storage and retrieval requirements of the services.
As you can see on the slide, we used NoSQL MongoDB, MYSQL RDBMS, In-Memory H2 and Cache Redis databases.
MySQL:
We used MySQL in the user-authentication Service.
The Authentication Service handles sensitive data such as user credentials which we have to store securely. As MySQL has its own authentication to access the database, it provides security to the user data.

MongoDB:
We used MongoDB in user management, doctor consultancy, resource request service and so on. All of these Services require Read Operations mostly. For Example, if a resource has been added, it will be viewed most of the time rather than editing it frequently. MongoDB is better for reading Operations than Writing operations. Also, MongoDB being a NoSql Database, gives a lot of flexibility to store documents with changing structures.

We also used the Redis database to store details of all those doctors who are online and can connect with patients for consultation.

All microservices, web applications and databases are dockerized. So basically Dockerizing is the process of packing, deploying, and running applications using Docker containers.
We have used microservices patterns like the Eureka server and Spring Cloud gateway. Let’s talk about the Spring cloud gateway first.

As all of you can notice, our product is composed of 8 core microservices that are running in parallel and communicating with each other.
The client has to be aware of each microservices location.
Now let’s consider various scenarios like:

The location of one Microservice changes?
Or if a new Microservice is added?
Or if the team decides to update the routes of a running Microservice?

To solve these issues, the API gateway is a good alternative. It makes the development, updating and maintenance of each service very convenient and systematic.
Now clients only need to communicate with a single service – the API gateway. In our system, the API gateway is responsible for routing client requests to appropriate services.

We have used Spring Cloud Gateway to implement the API Gateway pattern for routing requests, as it is a non-blocking API, which ensures that no incoming request ever gets blocked as a thread is always available to process the incoming request.
On a cloud platform, it is obvious that all the servers or containers use dynamic IPs for autoscaling and service may go down at any point in time due to overload. so we can not predict the IP addresses of the container. 
Here Eureka server steps in. The Eureka server is a service discovery pattern implementation.
Every microservice sends a heartbeat to notify the Eureka server that it is available and registers itself. If the Eureka server is not receiving any heartbeats from a service for a quite long time, that means the service is unregistered from the Eureka server automatically and the Eureka server notifies the new state of the registry to all other services. 

That is how the eureka server keeps track of IP and port for each microservice and makes it easier for us to look only in one place for each microservice.
One of the critical reasons that we were able to quickly get the first release of the product on time is because of the Agile methodology. We used the SCRUM Framework and completed the product in 5 Sprints, each Sprint being of one week of duration.

Before each Sprint, we had a Sprint Planning meeting. Where the team decides on how to demonstrate the product for the Sprint and accordingly, User Stories to the Sprint Backlog from the Product Backlog is added.

Each day, we have a SCRUM standup meeting where each of us discussed three things:

First, What we did on the previous day, 
Second, the plan of the current day, 
and finally any Barrier the team is facing.

On completion of the Sprint, we had a Sprint Review Meeting, where we demonstrated a working version of the product. 

Our Mentors gave feedback on any changes that needed to be made in the system. Because we were working in Sprints, we were always ready to accept any changes and get them implemented very quickly.

The Sprint Review Meeting was followed by a Sprint Retrospective meeting where the team would retrospect on what went well and what didn't so that we could take immediate corrective measures in the next Sprint.

Our first sprint went on understanding the Problems, we were trying to deliberate the architecture and design to solve the pain points mentioned before.

The subsequent sprints were spent on building the system to complete the first release.
Since quality is our highest priority and code quality is the most important part of the project, to maintain this we have implemented CI/CD pipeline which improved code quality.

In our product development, we have one master branch and multiple release branches.

Each release branch corresponds to a sprint. 

Whenever we push our code to Gitlab, GitlabCI will be triggered and take care of continuous integration and the code quality is checked.

At the end of each sprint, a pull request is made to the master branch, where the code is reviewed by the mentors and is merged with the master branch.

Code that goes into the master branch is deployed to AWS.
