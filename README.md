Assumptions taken:
- A simple application to provide twitter like functionalities
- Users are considered to be authenticated and authorised
- All the data is stored in-memory
- User can create an account by providing username
- usernames are case sensitive
- User metadata like email, mobile, name etc is not considered. Although they could be easily part of user model
- User cannot follow itself. A user can only follow other users.
- Driver class provides APIs to create user, follow other users, post a tweet or generate timeline for a user
- Exceptions are caught in the Driver class

There are different components of this project
Entities are two - User and Tweet
A user can have multiple tweets
A user can have multiple followees (followees = users they're following)
A tweet has an author and a timestamp automatically generated by the system

Exceptions
- BadRequestException: signify any invalid input from the api request data
- UserAlreadyExistsException: trying to create an account with username which is already taken
- UserNotFoundException: trying to access a user which does not exist

There are different service interfaces for different responsibilities
User service takes care of user account related actions: creating a user, following another user etc
Tweet service takes care of posting a tweet
TimelineGenerator service takes are of generating a timeline

All these services are made interfaces so that they're bound by a contract but are not limited by the implementation
e.g. RecentTimelineGenerator is the implementation of TimelineGenerator, in which tweets are sorted with most recent tweet at the top. In future, other strategy like most related tweets first or rule based feed or AI generated feed can be easily extended as a separate implementation of TimelineGenerator interface keeping all the clients code intact


Repository is storing users list
RepositoryService is made an interface to support various forms of storage.
e.g. in this case, i have implemented in memory implementation. In a case where we want to implement database supported storage, we could do that by creating another class with database connection and providing this dependency in driver class itself. Again, keeping all the client code for repository intact.


HOW TO TEST THIS CODE
Go to Driver.java class

// add your cases as follows
use userService.createUser("mark"); to create a user named "mark"
use userService.followUser("mark", "elon"); where user elon folllows mark;
use tweetService.postTweet("mark", "Hello there, I'm Mark Zuckerberg, I run Meta"); where mark posts a tweet with a msg
timelineGenerator.generateTimeline("mark"); to generate timeline for mark


run the file


