### CS-360 Portfolio Submission

Russ Savela
russell.savela@snhu.edu

#### Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?

This app was required to allow a user to login or create an account with credentials stored locally on
the device, allow a user to add, edit, view and delete tasks stored in a database on the device, and 
obtain permissions to use SMS.


#### What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?

The screens necessary were to gather login and registration data, and to display, edit and delete tasks.  The UI was kept consistent and intuitive to be easy for users.  The designs are thought to have met these goals.


#### How did you approach the process of coding your app? What techniques or strategies did you use? How could those techniques or strategies be applied in the future?

The strategy for coding the app was to break development into smaller features, and ensure that each filter would compile and run successfully before moving on to the next feature.  Unfortunately, not all features were completed in the time allotted.

#### How did you test to ensure your code was functional? Why is this process important, and what did it reveal?

The code was tested by interracting with it in the simulator to the extent possilble. This often revealed that thecode did not function as expected.   For code not controlling UI elements, it would have been preferable to develop unit tests, but these were not completed.

#### Consider the full app design and development process from initial planning to finalization. Where did you have to innovate to overcome a challenge?

Some features present only in modern versions of Java were used in this code.  This was accomodated by modifying the Gradle configuration to use Java 17, which allowed for this.

#### In what specific component of your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?

The database access code was implemented in a better fashion than other components.

