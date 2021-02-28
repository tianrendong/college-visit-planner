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

#### Challenges:

* Mobile app would be preferable, but may be more difficult to implement
* Can work on a simple version first?

#### User Opinions:

> Generate statistics for spare items (or items that have large demand) and report to university. More efficient allocation of resources through collaboration with university and local community. For example, quiet period food wasted.


### Shared Diary

#### Description:

It’s often hard to be completely honest in interpersonal relationships, especially when we’re interacting with each other face to face. Or, sometimes, it’s nice to read about how someone else is feeling especially when you share similar interests, or even struggles. This shared diary is designed to be a safe space for people to jot down their thoughts about the group, relationship, or anything that they are feeling. This not only applies to couples, who might be the most stereotypical target, but also for people who share similar mental health struggles to document and help with each other’s progress, or even project teams to keep track of everyone’s work progress.

#### Target Users:

* Couples
* Project teams
* Friend groups
* Interest groups both in and out of Brown 

#### Features:

1. Login/Logout feature that authenticates that you have access to your own diary columns.

2. Adding tags that you are being able to associate with each post. Tag examples: fight, family, mental health, homework, stress, food, etc.

3. Color coding scheme for each post that reflects current mood/emotions.

4. Comment feature for other people’s posts.

5. Ability to “mention” other people.

6. Query search/filter by diary entries by date, keyword, and tags.

7. Sort by mood, date, tag, etc.

8. Suggestion for similar past entries after completing current entry: Had another fight about the same issue? Or, you are working on a bug that you feel like someone had mentioned before? The algorithm would automatically suggest something that might be relevant to read. This hopefully serves as a motivation to complete the entry each day.

#### Algorithms:

* Using Kd Tree to represent “closeness” of each post to generate suggestions. The k would most likely be the number of parameters(tags).

* Using an efficient sorting algorithm (most likely quick sort) to sort entries by mood, tags, and dates.

#### User Opinion:

* Notification feature would be helpful

* Should be intuitive and accessible; diaries can become “work” so we should lower the barrier as much as possible

* Would be nice to have a mobile app


### Time Management Tool


#### Description:

College students always have trouble managing their time when they get slammed with school work and extracurriculars. This app keeps track of all the school assignments, extracurricular tasks, and personal goals you have to give you a daily work requirement in terms of hours and percent progress in order to meet a set date of completion. This would allow you to consistently allocate ample time on work, spreading it out evenly over the course of multiple days so you don't get yourself in a situation where you have to pull an all-nighter to get something done. The app should include the ability to log work hours and completion progress so that it can recalculate the amount of work needed on future days in case you are ahead of the game and complete extra early or delinquent and need to spend more time in the future. Ideally, it should connect with your existing calendar schedule to determine which time slots throughout the day you have time to work on which tasks. It would be best if it could optimize this to the point that it can identify tasks that are best to be worked on when you have a relatively short amount of time, say 30 mins, vs those that require at least an hour or two to really get going. 

#### Target Users:

* College students who are overwhelmed with assignments and extracurriculars 
      * Constantly sleep deprived
      * Feeling like they are “sliding sideways” to get everything run

#### Features:

1. Daily progress goals for assignments and other personal goals
      * Users want to know what total progress needs to be made over the course of the day to stay on track for completion. 

2. Task list of what specific things needs to be completed in the day (for example: 5 questions completed on pset, 2 hour workout, respond to important emails) 
      * Specific task lists/action items are particularly useful so users can focus on one thing at a time and not get overwhelmed by the larger daily progress goal. 

3. Overall % complete dashboard
      * Users want to visualize how much progress they have made in total completion of a task and how much they have left to go before the due date. 

4. User settings to dictate priority of task, difficulty of task, if task is something that is easy to work on a little bit here and there or if it is something that is best worked on from start to finish all at once 
      * Users want the app to schedule tasks appropriately in terms of time slot length and ordering. If certain tasks are much more important than others, they will want them to be done first. Also, if certain tasks are more difficult or take longer, they will want them to be scheduled for a time in which they can give their undivided attention to the task for a longer period of time. 

5. Needs to sync with google calendar, maybe even import assignments from external apps like canvas
      * Users already have too many platforms to keep track of, so if they add anything else it needs to sync with their preexisting apps so that they don’t have to update things twice or worry that they will put something in one app and forget to add it to another.  

6. Sleep scheduling (maybe?)
      * Users may want to set a daily sleep goal in terms of hours and want the app to adjust their daily work progress and schedule accordingly. 


#### Algorithms:

* Something to maximize time allocation efficiency 

* Can match length of time slots to assignment types so that longer/more challenging assignments are scheduled when you have bigger time slots and shorter assignments are scheduled for smaller time slots 


#### User Opinion:

* Want it to be quick i.e. can’t take too long to setup and use 

* Integrate with canvas or how you already get assignments 

* A lot of assignments are partnered 

* Want to set milestones in terms of completion example: office hours on tuesday, assignment due friday, need to attempt X amount before office hours tuesday

* Ability to sync with google calendar 

* Include more than school assignments...extracurriculars, fitness/physical goals, internship applications, etc.

* Prioritization (light work to do while watching tv/movies or hard stuff during grind session)

* Maximize time slot length and assignment 

* Ranking system in terms of difficulty of assignment

* Can start/stop assignments vs need to sit down and grind all at once

* Want notifications so you don’t have to open the app 

* Widget screen on phone to give a list of what needs to be done 

* Both mobile app and web app together 

* Don’t really want it to tell me to sleep or not 

> “I’m gonna be in the library for 3 hours.. What should I do/in what order?”


**Mentor TA:** _Put your mentor TA's name and email here once you're assigned one!_


## Meetings
_On your first meeting with your mentor TA, you should plan dates for at least the following meetings:_

**Specs, Mockup, and Design Meeting:** _(Schedule for on or before March 15)_

**4-Way Checkpoint:** _(Schedule for on or before April 5)_

**Adversary Checkpoint:** _(Schedule for on or before April 12 once you are assigned an adversary TA)_

## How to Build and Run
_A necessary part of any README!_
