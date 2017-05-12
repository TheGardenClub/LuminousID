## Luminous ID - Android

The Luminous ID Android App Repo



## Developers 
The Garden Club


Ryan O'Connell

D'Vreaux Fontaine

Chase Springer

Jennifer Baumann

Brian Larson

Kevin Rau



## Sponsors
Nathalie Chardon
Jane Smith

## Uses 3rd party libraries 

Parcel: https://github.com/johncarl81/parceler

Glide: https://github.com/bumptech/glide

Calligraphy: https://github.com/chrisjenx/Calligraphy


## Organization


This repo attempts to follow standard android organization.
 
Assets, such as data and fonts, are located in the assets folder.
 
All layout files are in the res > layout folder.

Custom UI and logos are located in the res > drawable folder.

 

## In-Depth Description
The following is for a closer look at the organization of this repo -



## Apply Font to all Classes
- 
	FontHelper.java
    
	- Defines default font for the app using Calligraphy. Code must be placed in all activities that need a ## font change.
    
	- Can be changed on a case-by-case basis using fontPath="fonts/fontfile.ttf" in a TextView.
    
	- More info can be found in this class and in the Calligraphy library.



## All Global Arrays:

	- PlantArrayManager.java
    
	- Stores all global arrays: User account info, Field Guide, Glossary, and User Observations



## Open screen (For Login and Signup)
	
- open_screenActivity.java
    
	- Nothing much here but leading to three different activities: Login, Signup, and HomeScreen

	- activity_open_screen.xml


## 
Login Screen
	
- LoginActivity.java
    
	- This also leads over to the ResetPasswordActivity.java if you forgot your password.

	- activity_login.xml

Signup Screen
- SignUpActivity.java
    
	- This will save email, password, and username to our database.
	
- activity_sign_up.xml



## Home Screen
- Home_screenActivity.java
    
	- Defaults here if already logged in.
    
	- Creates a global array (PlantArrayManager.java) when opened for all Field Guide entries, Glossary entries, the
 user's information, and an empty array for any observations.
    
	- The data for the Field Guide is being read from the Firebase server and placed into an ArrayList of whatever plant type
 it is. Example: A forb will create a forbsDetails.java and added to the GlobalForbsArray.


	- activity_home_screen.xml



## Field Guide Intros
	
- Intro_FieldGuideActivity.java
    
	- Uses activity_intro_field_guide.xml
 

## Graminoids Field Guide Intro
	- Graminoids_FieldGuideActivity.java
    
	- Uses activity_graminoids_field_guide.xml


## Woody Field Guide Intro
	- Woody_FieldGuideActivity.java
    
	- Uses activity_woody_field_guide.xml



## Field Guide Lists (Type of plant is 'type')

	- 'Type'_FieldGuideActivity.java
    
	- Uses activity_'type'_field_guide.xml
    
	- Goal: Populate a list view with all 'type' from Firebase
    
	- Pulls in global array to filter and display all desired entries.
 Data held in ArrayList<'type'Details>
. Displayed using the 'type'FilterableAdapter.java



## Field Guide Details (Type of plant is 'type')
	
- plantDetailActivity.java
    
	- Uses activity_'type'_detail.xml, depending on the listview activity user is coming from.
    
	- Goal: Display all Firebase information in an easy to read way. Give user option to take observation.
    
	- Gets information from a 'type'Details class passed in from listview using Parcel library.
 It gets all necessary information and sets all the textviews in this activity to correct information.
 Plant picture is taken from assets > plantphotos using our plantcode value and the Glide library.



## Field Guide Filtering (Type of plant is 'type')
	
- PlantFilterActivity.java
    
	- Uses activity_'type'_filter.xml, depending on the listview activity user is coming from.
    
	- Goal: Display all filterable categories. Have user select what they want, then apply filters to listview.
    
	- Drop down menus displayed are dependent on the last listview activity and type of plant user is coming from.
 User selects their option from the menu, then presses the Apply button. These options are sent back to the listview so that activity can perform all the filtering. Menu selection is handled by the filterOnItemSelectedListener.java



## Glossary Intro Activity

	- Glossary_IntroActivity.java
    
	- Uses activity_glossary_intro.xml



## Glossary List

	- Glossary_ListActivity.java
    
	- Uses activity_glossaryforbs_list.xml or activity_glossarygraminoids_list.xml depending on option selected at intro page.
    
	- Goal: Display all glossary entries. If user clicks an entry, open page with bigger picture.
    
	- Use our global array from PlantArrayManager.java and the glossaryForbsAdapter.java / glossaryGraminoidsAdapter.java
 to populate our listview. If clicked, sends user to glossaryDetailActivity.java and passes in the glossary info. Glossary info passed in using glossaryDetails.java and Parcel library.



## Glossary Detail

	- glossaryDetailActivity.java
    
	- Uses activity_glossary_detail.xml
    
	- Goal: Display a bigger picture of the selected glossary entry.
    
	- Uses passed in glossaryDetails.java class to set the name text and image. Image pulled from assets > glossaryphotos using the Glide library.



## Add Observations
	
- AddObsActivity.java
    
	- Uses activity_add_obs.xml
    
	- Goal: Allow user to take a picture. The GPS location and accuracy are saved. The user can enter comments.
 The user submits to the My Observations page.
    
	- Uses camera intent to take a picture. The GPS location / accuracy / timestamp are handled by Google Location Services.
 User enters their own comment. The observation is saved to a global array in PlantArrayManager to be pulled later.



## My Observations

	- MyObservationsActivity.java
    
	- Uses activity_my_observations.xml
    
	- Goal: Show the observations that the user has taken. Allow user to send to the server.
    
	- We populate the list using the global observations array. The send to website button sends it to Firebase.
 Firebase takes care of if it can't communicate w the server. Will send when next connected.


