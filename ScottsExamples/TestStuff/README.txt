BlankActivity36 is modified to reflect (from memory) the old 3.5 blank activity to create it I deleted the following

navigation folder and all its content

from the res/layout folder
fragment_first.xml
fragment_second.xml

I also changed the content main to just have a text view instead of calling the nav_graph fragment

from the first folder in java
FirstFragment.java
SecondFragment.java

BlankActivity36NavGraph

Here I used the 3.6 blank activity which includes the navgraph and some starting fragments.  
I added the safeargs and passed a string.  Very easy.


