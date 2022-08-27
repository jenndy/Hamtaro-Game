# Hamtaro Game

### Objective:
Keep a falling hamster afloat with a pillow as you feed it acorns.

### Structure:
Main class is in Game.java

### Basic Gameplay: 
The purpose of the game is to keep the Hamtaro character afloat by catching it with
the pillow until the timer runs out. The pillow can move left, right, up or down, 
with the respective arrow keys. There are 3 rounds to the game with three lives per 
round. There will be acorns falling the hamster can eat to increase the score. 
To yield the highest score, eat acorns, minimize the amount 
of lives lost, and clear all 3 rounds.

### Countdown: 
The timer is two rectangles, one clear and one filled with green. The width of 
the green rectangle decreases as the round progresses. After each successive round, 
the width of the green timer shrinks at a slower rate, making each successive round 
longer and more challenging. 

### Lives: 
The player gets 3 lives per round. Lives are replenished when a new round begins. 
The game is lost if all lives are lost before the timer is up. The player can keep 
track of the number of remaining lives by looking at the number of potion bottles 
at top right corner of the screen.   

### Score: 
Clearing a round adds 5 points, losing a life costs 2 points, eating an acorn gains 2 points. A life is lost 
if the hamster falls out of the screen. Losing a round causes the game to end and 
no additional points are detracted besides the 2 points lost by falling out of the screen. 

### Paddle: 
The paddle is the pillow that catches the hamster. The user controls this pillow 
with 4 arrow keys: left, right, up, and down. The hamster can bounce off all sides of 
the paddle. 

### Lob: 
The lob is the hamtaro character. It will bounce off 3 sides of the screen as dictated 
by the equations of projectile motion, which take gravity into account, and fall out 
the bottom of the screen. It will also bounce off both the top and bottom of the paddle 
as dictated by the equations of projectile motion. 

### Changing background: 
The background changes from round to round. Round 1 has a general background, round 2 has a 
more close up background, and round 3 has a sun setting in the background, creating a time 
lapse effect. 

### Win or Lose Outcomes: 
Winning or losing the game results in a different image and sound effect. 

### Sound effects: 
Sounds are added via Clip including: 
- “bump”: Hamster hits the top, left, or right sides of the screen or the pillow.  
- “fall”: Hamster falls out from the bottom of the screen. 
- “transition”: Player advances to the next round. 
- “lose”: Player loses the game. 
- “win”: Applause sound effect if player wins the game. 
- “background music”: The music box version of the Tottoko Hamutaro theme song 
plays throughout the duration of the game.
