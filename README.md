# Sink-Hole
A simple game

mainForNewGame handles creating jframes, and holds functions that:
Run the game loop
Resent the game
etc

newGame handles drawing the graphics, collision dection and input for the keybord and buttons. 
It will draw the end screen and pass a value to the game loop that signals it to stop.

expStartscreen calls the game loop and collects values from start screen. It draws all element of start screen. 
The methold makePanel must be called and added to the Jframe in main in order for game to ever work. 

There are various additional metholds that manage stuff such as obsticle, player, coin, button etc.
The most important of theses classes is player which stores data relating to color theme and difficulty as well as the player data. 

