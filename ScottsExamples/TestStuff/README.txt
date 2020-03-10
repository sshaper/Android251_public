BasicActivity36 is modified to reflect (from memory) the old 3.5 basic activity to create it I deleted the following

navigation folder and all its content

from the res/layout folder
fragment_first.xml
fragment_second.xml

I also changed the content main to just have a text view instead of calling the nav_graph fragment

from the first folder in java
FirstFragment.java
SecondFragment.java

BasicActivity36NavGraph
Here I used the 3.6 basic activity which includes the navgraph and some starting fragments.  
I added the safeargs and passed a string.  Very easy.

RecycleViewFragment
This is an example of running an recycle view in a fragment

RecycleViewFragmentCardView
This is the same as above example except with the card view addded.  INTERESTING NOTE: I had to use the recycleview and cardview implementations in the gradle file for this project but I did not for the AndroidStudioDevelopmentEssentials3.5 book when doing it for 3.6 


