# RPG-JAVA
This project is a text-based RPG developed in Java, applying core Object-Oriented Programming principles such as inheritance, polymorphism, encapsulation, abstraction, language-specific interfaces (equals, hashCode, compareTo, clone), and modeling best practices.
Below is a detailed description of all classes that compose the system.

### Personagem.java
Personagem is an abstract class responsible for defining the fundamental structure of any entity capable of engaging in battles within the game.
It centralizes attributes and behaviors common to all characters, including:
- name
- pontosVida (HP)
- attack
- defense
- level
- inventory (Inventario)

In addition to these attributes, the class provides essential methods:
- tomarDano() – reduces HP according to the damage taken.
- estaVivo() – checks whether the character still has remaining hit points.
- batalhar(Inimigo) – implements the combat system using dice rolls.
- copy constructor – creates a complete duplicate of the character.
- toString(), clone(), and other required methods following the course guidelines.

This class serves as the base for all player-controlled characters as well as enemy entities.

### Guerreiro.java, Mago.java, Arqueiro.java
These classes represent the three playable character types, all extending the Personagem class. Each subclass defines its own initial values for attack, defense, and hit points, representing their distinct characteristics:

* Guerreiro (Warrior) – physically stronger and more resilient.
* Mago (Mage) – specialized in magical attacks and special abilities.
* Arqueiro (Archer) – agile, with ranged attack capabilities.

Each class includes:
* A default constructor
* A copy constructor
* Customization of inherited attributes

Together, these subclasses provide variety during character creation at the start of the game.

### Inimigo.java
The Inimigo class also extends Personagem, representing creatures and adversaries the player may encounter.
Its unique features include:

* Custom values for attack, defense, and hit points.
* An internal system for generating random loot items.
* Use of an inventory to store these items.

When an enemy is defeated, their inventory can be cloned, allowing the player to loot part of the enemy’s items.

### Item.java
The Item class models objects that the player can manipulate, such as potions, power-ups, and equipment.
Each item includes:
- name
- description
- effect
- quantity (allowing multiple units of the same item)

The following methods are implemented as required:
- toString() – displays the item name and quantity.
- equals() – identifies whether items are identical, preventing duplicates in the inventory.
- hashCode() – consistent with equals, ensuring correct behavior in collections.
- compareTo() – allows sorting items, typically alphabetically.
- clone() – supports deep copying of inventories, ensuring independence between objects.

### Inventario.java

The Inventario class manages the items carried by each character. 
Its functionalities include:
- addItem(Item) – increases the quantity if the item already exists.
- removeItem(Item) – decreases the quantity, removing the item completely when it reaches zero.
- listItems() – returns the items in sorted order using compareTo.
- clone() – creates a completely independent (deep) copy of the inventory.

The inventory plays a key role in combat, healing, exploration, and loot dynamics.

### Dado.java

This utility class simulates dice rolls.
The results influence several game events:
* Combat attack outcomes
* Escape attempts
* Random encounters during exploration
* The dice system introduces unpredictability and keeps gameplay dynamic.

### Main.java / Jogo.java

The main class of the project, responsible for:

* Starting the game
* Creating the character chosen by the player
* Controlling the main RPG loop
* Managing exploration, battles, and item usage
* Presenting narrative decisions and branching paths
* Generating and calling enemies based on story events

It is the central point where all game logic integrates, acting as the entry point for the application.
