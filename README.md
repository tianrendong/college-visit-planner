# cs0320 Term Project 2021

## College Visit Planner

A web app to help students plan college tours.
It suggests an optimal route to visit a list of user selected colleges by
* dividing the schools into clusters based on their geolocations
* recommending nearby airports 
* Solving the Travelling Salesman Problem to create road trip routes within each cluster


## How to Build and Run

1. Run in production mode using Docker

Execute the following commands in the project root directory:

* *docker build collegevisitplanner .* to build the docker image

* *docker run -p 4567:4567 collegevisitplanner* to start the containers

Open [http://localhost:4567](http://localhost:4567) to view it in the browser.

_______

2. Run in development mode

* *cd frontend* to enter frontend directory
* *npm install* to install dependencies
* *npm start* to start the frontend 

* *cd ../backend* to enter backend directory
* *mvn clean package* to install dependencies
* *./run --gui* to run backend

Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

## Team

**Team Members:**

Ashley Oelrich, Kaki Su, Jenny Yu, Tianren Dong

**Team Strengths and Weaknesses:**

### Ashley Oelrich

Strengths:

* Attention to detail, persistence in solving bugs, willingness to work long/late hours
* Good at thinking through user needs/putting myself if their perspective

Weakness: 

* Don’t have a lot of software engineering experience in general 
* No experience with frontend development

### Kaki Su

Strength: 

* Relatively organized and don’t procrastinate much especially in group projects. 
* Has some experience managing and working with large-scale projects
* Has some front-end/back-end experiences

Weakness:

* Not great at writing “clean” code
* Not great at thinking about runtime complexity beyond the basics

### Jenny Yu

Strength:

* Have experience in design, attentive to details and user needs, can contribute to improving user interface and usability of the system
* Pay attention to overall system design and code clarity
* Willing to openly communicate about problems
* Willing to devote time
* Actively searching for better solutions

Weakness:

* Do not have a lot of prior software engineering experience 
* Sleeping and working schedule may not be the most organized 

### Tianren Dong

Strength:

* Have experience with UI design (Figma).
* Know Python programming.
* Persistent with fixing bugs.
* Eager to learn from others.

Weakness:

* No front end experience before CS32.
* Lack of experience working on large/collaborative projects.
* Can get too focused on writing code instead of thinking conceptually.


**Mentor TA:** Livia Zhu

