# cs0320 Term Project 2021

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
* Actively searching for better solutions

Weakness:

* Do not have a lot of prior software engineering experience and sometimes get frustrated by weird bugs  
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


## Project Ideas

## College Visit Planner

### Description

A web app to help plan college tours.
It suggests an optimal route to visit a list of user selected colleges--It will divide the list into clusters, recommend flights for long distance travels between clusters, and recommend road trip routes for travelling between schools within an area. Users will be able to drag the stops around, make their modifications, and share their trip plan.


### Features

* Add colleges
  - When adding college (e.g. UCLA), system recommends nearby colleges (e.g. USC, UCSD)
* Pathfinding
  - Map View
  - List View: a list of trip stops in order, with distance and travel time between each consecutive stops

* Interactive Map
  - Zoom in/zoom out
  - Show colleges on Map
    
* Get shareable link/print



### Algorithm

* Break the schools into clusters
     - KD Tree Radius Search/ Other clustering algorithm
* Construct a MST for each cluster
     - Construct a complete graph with the schools in the cluster, path weights = distance given by external Map API
     - Since users’ goal is to visit all the schools, there can be repeated paths when driving.
* Solve the TSP with the large clusters (e.g. home to east coast to west coast)
     - The Algorithm of Christofides and Serdyukov
     - TSP is reasonable, because there won’t be that many clusters
     


**Mentor TA:** Livia Zhu


## Meetings
_On your first meeting with your mentor TA, you should plan dates for at least the following meetings:_

**Specs, Mockup, and Design Meeting:** _(Schedule for on or before March 15)_

**4-Way Checkpoint:** _(Schedule for on or before April 5)_

**Adversary Checkpoint:** _(Schedule for on or before April 12 once you are assigned an adversary TA)_

## How to Build and Run
_A necessary part of any README!_
