
## Package and class diagram (UML)
![UML Diagram](https://i.imgur.com/l9a8u4R.png)

## LibGdx interaction diagram
![libgdxdiagram](https://i.imgur.com/50WADxK.png)

## Design Patterns Used

  * ### Model–view–controller (MVC)
    ![MVC Pattern](https://upload.wikimedia.org/wikipedia/commons/a/a0/MVC-Process.svg)
    
    The pattern presents three main packages:
    * **Model**: Responsible for the logic.
    * **View**: Controlls the graphical aspect of the project.
    * **Controller**: Catches input from the user and sends it to the model.

  * ### Observer
    ![Observer Pattern](https://upload.wikimedia.org/wikipedia/commons/a/a8/Observer_w_update.svg)
  
    Participating classes:
    * Observers: MouseHandler (implements InputListener).
    * Subjects: Graphics.
    
  * ### State
    ![State pattern](https://upload.wikimedia.org/wikipedia/commons/e/e8/State_Design_Pattern_UML_Class_Diagram.svg)

    State is a pattern used to help organize different states in a computer program. In chess it can help implement game status such as a as playing, losing, winning or draw.
    Implemeted mostly on the GameState class.
  
  * ### GameLoop
    ![GameLoop Pattern](https://web.fe.up.pt/~arestivo/presentation/assets/gamepatterns/loop3.svg)
    
    Implemented using LibGDX's GameScreen class.
    
    
## GUI Design and Mock-Ups

  * ### Main Menu
    ![MainMenu](https://i.imgur.com/3j6Nsud.png)

  
  * ### Multiplayer Menu
    ![MultiplayerMenu](https://i.imgur.com/mfucblq.png)
    
    
  * ### Networking Menu
    ![HostMenu](https://i.imgur.com/ppjSRAL.png)


  * ### Connect Menu
    ![ConnectMenu](https://i.imgur.com/dGAyHIs.pngg)


  * ### Host Menu
    ![HostMenu](https://i.imgur.com/2ddSzBX.png)

  
  * ### Game
    ![ConnectMenu](https://i.imgur.com/YKiS2S4.png)
  
  
 
## List of Tests

 *Character Moves
 *Unsuccessful check
 *Unsucessful checkmate
 *Sucessful check
 *Sucessful checkmate
 *Game Over
 *AI moves
