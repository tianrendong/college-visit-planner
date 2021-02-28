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
* Always willing to openly communicate about problems
* Explorative and actively look for better solutions

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

### Idea 1: Resource Trading

#### Description:

Students usually have a lot of spare items. We purchase textbooks but many of them become useless after a few semesters, but others who have not yet taken the courses may find them useful. Under COVID some students may have purchased extra protective supplies such as masks, while some may run out of them. For graduating students, it is inconvenient for them to bring their mattress, washbasin, and electronic appliances like kettles and ovens home. Yet throwing them away is a waste of both money and resources. Incoming freshmen are the people who need these items and may find it useful if they can buy these reusable supplies at a low cost. Hence we would like to create a school-centered platform for the trading and sharing of resources.

#### Target Users:

Students who have spare yet reusable items & students who would like to purchase second-hand items.

#### Features:

1. Log in using student ID
  * System requires student credentials for login, such that students are confident about the security of the platform and would feel safe about making posts/trading things. This would also prevent users from messing around with the system, since their actions are tied to their credentials.

2. Making posts about spare items
  * Posts contain information such as pictures, category of item, year purchased, seller’s preference (whether if they would like to sell it, exchange for other resources, or give it for free)
  * Comprehensive information would allow users to better

3. Making edits to previous posts
  * Users might want to make changes or add information to their previous posts (e.g. a graduating student may be leaving soon and would like to give away his/her oven at a much lower price). It would be user unfriendly if one has to delete a post and make another entry.

4. Searching for goods
  * Students can search for goods based on name or good category.

5. Chatting
  * Since the resources traded are second-hand, users may have questions about the status of the goods or would like to negotiate about prices. Users may also want to chat to discuss a suitable place to meet up and make the exchange. 

6. Make/Change confirmation 
  * There is no direct money payment involved via the platform. Rather, students can set up a time/location to meet up and make the exchange. 
  * A confirmation is necessary so that the platform would be able to track it if a user constantly stands other people up. This also serves as a warning, preventing people from doing so.
  * Although the platform is school-centered, students would still feel insecure about having to make online transactions to an unknown person on a small system. Users meet up and make the transaction after they decide that it is safe to do so.

7. Profile page
  * Can contain a rating based on others’ feedback for the goods sold and credibility. If a user frequently changes or cancels confirmation within a short period of time, or doesn’t show up, this will affect his/her rating, which will serve as a warning to other users.
  * Can look up previous exchanges made with other users.

#### Algorithms:

1. Storing in database/Querying/Searching
  * Efficient querying is necessary since there will be potentially a large amount of goods and information. When a user searches for a good based on either the name or the category, the feedback from the system needs to be efficient.

2. Recommendation
  * Which goods show up on the front page? 
    * E.g. Allow students to input the types of goods that they need. 
    * E.g. Click-based and search-based. If a user frequently searches and taps into a specific type of good, then the system knows it.
  * Recommendation based on user rating.

### Idea 2

### Idea 3

**Mentor TA:** _Put your mentor TA's name and email here once you're assigned one!_

## Meetings
_On your first meeting with your mentor TA, you should plan dates for at least the following meetings:_

**Specs, Mockup, and Design Meeting:** _(Schedule for on or before March 15)_

**4-Way Checkpoint:** _(Schedule for on or before April 5)_

**Adversary Checkpoint:** _(Schedule for on or before April 12 once you are assigned an adversary TA)_

## How to Build and Run
_A necessary part of any README!_
