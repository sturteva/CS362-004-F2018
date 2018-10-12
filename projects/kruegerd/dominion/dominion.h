#ifndef _DOMINION_H
#define _DOMINION_H

// Added Support for Compiling on Windows Environment
#ifdef __unix__  // or #ifdef linux
#endif
#if defined(_WIN32)  // or #ifdef _WIN32
	#define _CRT_SECURE_NO_WARNINGS   // dominion.h is not the first file in the other files so you may have to duplicate this define
#endif

// Code from various sources, baseline from Kristen Bartosz

#define MAX_HAND 500
#define MAX_DECK 500

#define MAX_PLAYERS 4
#define NUM_KINGDOM_CARDS 5  // the number of cards the players can draw from and put in their hands. (AKA Supply Pile Kindom Cards)

#define DEBUG 0

/* http://dominion.diehrstraits.com has card texts */
/* http://dominion.isotropic.org has other stuff */

/* hand# means index of a card in current active player's hand */


/* Every Card in the Dominion Game --- (not all cards are chosen to play in any instance of a game) */
enum CARD
  {curse = 0,
   estate,
   duchy,
   province,

   copper,
   silver,
   gold,
	/* NOTE:  the cards from adventure to treasure_map are all action cards.  By putting them in oder here then they can be accessed by their numerical (enumerated) value. */
   adventurer, /* Adventure is an action card, "Reveal cards from your deck until you reveal 2 treasure cards.  Put those Treasure cards into your hadn and discard the other revealed cards."*/
   /* If no/only 1 treasure found, stop when full deck seen */
   council_room,
   feast, /* choice1 is supply # of card gained) */
   gardens,
   mine, /* choice1 is hand# of money to trash, choice2 is supply# of
	    money to put in hand */
   remodel, /* choice1 is hand# of card to remodel, choice2 is supply# */
   smithy, /* Smithy is an Action card, +3 Cards*/
   village,

   baron, /* choice1: boolean for discard of estate */
   /* Discard is always of first (lowest index) estate */
   great_hall, /*Action-Victory, +1 Card, +1 Action (and is worth 1 Victory Point" */
   minion, /* choice1:  1 = +2 coin, 2 = redraw */
   steward, /* choice1: 1 = +2 card, 2 = +2 coin, 3 = trash 2 (choice2,3) */
   tribute, /*Tribute, Action - The player to your left reveals then discars the top 2 cards of his deck. For each differently named card revealed, if it san Action + 2 Actions, Treasure Card + 2 Gold, Victory Card + 2 Cards."*/

   ambassador, /* choice1 = hand#, choice2 = number to return to supply */
   cutpurse, /* Cutpurse, Action-Attack, + 2 Gold, Each other player discards a Copper ( or reveals a hand with no Copper)."*/
   embargo, /* choice1 = supply# */ /*Embargo, action, +2 gold, "Trash this. Add an Embargo token to a Supply pile (For the rest of the game, when a player buys a card from that pile, they gain a Curse."*/
   outpost,
   salvager, /* choice1 = hand# to trash */
   sea_hag,
   treasure_map
  };

struct gameState {
  int numPlayers; //number of players
  int supplyCount[treasure_map+1];  //this is the amount of a specific type of card given a specific number.
  int embargoTokens[treasure_map+1]; // interesting uses the last enumeration item as an index value.
  int outpostPlayed;
  int outpostTurn;
  int whoseTurn;
  int phase;
  int numActions; /* Starts at 1 each turn */
  int coins; /* Use as you see fit! */
  int numBuys; /* Starts at 1 each turn */
  int hand[MAX_PLAYERS][MAX_HAND];
  int handCount[MAX_PLAYERS];
  int deck[MAX_PLAYERS][MAX_DECK];
  int deckCount[MAX_PLAYERS]; // the number of cards in a player's personal deck
  int discard[MAX_PLAYERS][MAX_DECK];
  int discardCount[MAX_PLAYERS];
  int playedCards[MAX_DECK];
  int playedCardCount;
};

/* All functions return -1 on failure, and DO NOT CHANGE GAME STATE;
   unless specified for other return, return 0 on success */

struct gameState* newGame();

int* kingdomCards(int k1, int k2, int k3, int k4, int k5, int k6, int k7,
		  int k8, int k9, int k10);

int initializeGame(int numPlayers, int kingdomCards[10], int randomSeed,
		   struct gameState *state);
/* Responsible for initializing all supplies, and shuffling deck and
   drawing starting hands for all players.  Check that 10 cards selected
   are in fact (different) kingdom cards, and that numPlayers is valid. 

Cards not in game should initialize supply position to -1 */

int shuffle(int player, struct gameState *state);
/* Assumes all cards are now in deck array (or hand/played):  discard is
 empty */

int playCard(int handPos, int choice1, int choice2, int choice3,
	     struct gameState *state);
/* Play card with index handPos from current player's hand */

int buyCard(int supplyPos, struct gameState *state);
/* Buy card with supply index supplyPos */

int numHandCards(struct gameState *state);
/* How many cards current player has in hand */

int handCard(int handNum, struct gameState *state);
/* enum value of indexed card in player's hand */

int supplyCount(int card, struct gameState *state);
/* How many of given card are left in supply */

int fullDeckCount(int player, int card, struct gameState *state);
/* Here deck = hand + discard + deck */

int whoseTurn(struct gameState *state);

int endTurn(struct gameState *state);
/* Must do phase C and advance to next player; do not advance whose turn
   if game is over */

int isGameOver(struct gameState *state);

int scoreFor(int player, struct gameState *state);
/* Negative here does not mean invalid; scores may be negative,
   -9999 means invalid input */

int getWinners(int players[MAX_PLAYERS], struct gameState *state);
/* Set array position of each player who won (remember ties!) to
   1, others to 0 */

/* Card Effects are the impact playing a card has on the game.  When a card is played it's effect is called and the gameState is altered to 
reflect the effect on the game.  

cardEffect is the original function and left as the default for other cards that are no included in the 5 functions explicetly called. 
*/
int cardEffectSmithy(int card, int choice1, int choice2, int choice3, struct gameState *state, int handPos, int *bonus);
int cardEffectAdventurer(int card, int choice1, int choice2, int choice3, struct gameState *state, int handPos, int *bonus);
int cardEffectGardens(int card, int choice1, int choice2, int choice3, struct gameState *state, int handPos, int *bonus);
int cardEffectVillage(int card, int choice1, int choice2, int choice3, struct gameState *state, int handPos, int *bonus);
int cardEffectCutPurse(int card, int choice1, int choice2, int choice3, struct gameState *state, int handPos, int *bonus);
int cardEffect(int card, int choice1, int choice2, int choice3, struct gameState *state, int handPos, int *bonus);
#endif
