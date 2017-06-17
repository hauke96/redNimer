# redNimer
redNimer is a simple reminder app. You can specify when, how often and in which frequency you want to get notifications.
Examples:
* Doing Sport every Saturday at 2pm but you want to get a notifications two hours before so you don't get to the gym when it's already crowded.
* You have a meeting at work every week at 10am. Create two notifications, so you drive early from home and don't miss the meeting when you're already at work.
* Sometimes you simply forget things. Get every three hours a notification so you don't forget that Saturday night is laundry night.

# Why not using Wunderlist or such?
Using another service owned by a big software company in the United States which gives a fuck about privacy? Great.

redNimer is a self-hosted service. Just ask your university, your company or a friend for a little vServer (or use your personal computer at home) and you're ready to go. You can also rent a vServer for a dollar per month.

# Start App using Android Studio
Make sure the following things apply on you:
* You have Android Studio 3 installed
* You are using SDK 25
* You have Java 8 installed
* You have a working Android simulator/phone with a minimum API version of 19

Just open the project (the `./client/` folder is the actual Android Studio project) and everything should be ready to go.

# Features
Crossed out (~~this is crossed out~~) task are already done. For more detailed information, also have a look at the [current open issues](https://github.com/hauke96/redNimer/issues?utf8=✓&q=-label%3Adone%20-label%3Awontfix).
## For the first release
These features ensure a basic usage of redNimer. Nice things like security or sharing are pretty delicate to implement and therefore part of later releases.

* ~~Create reminder~~
* Save reminder in local database
* Save reminder on remote server
* Specify the kind of notification
	 * ~~One time (notifies one time before due date)~~
	 * ~~Multiple times (notifies at a given frequency before due date)~~
	 * Specify night times. During these times there'll be no notification
* ~~Locally create a notification~~
* Get a push call from the server and then create a notification
	 * Use a local service if there's no connection to the server
* Get optional notifications via E-Mail

## Future music/Ideas
* Encrypt everything with manual or automatic created GPG keys
* Use SSL to communicate with server
* Create account on server
* Share reminder with other people
	 * First create a sharing-token (Number, unique name, QR-Code, whatever)
	 * Later (this is the real goal) search user and add them as friends
* Create desktop application
