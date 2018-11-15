#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "rngs.h"

#define DEBUG 0
#define NOISY_TEST 1

/*This is designed to test the Smith card*/
int main(){

	time_t t; 
	int seed = (int) time(&t);
	srand((unsigned) seed);
	int n;
        int tester;
        int bonus = 0;
        int* k = kingdomCards(adventurer, council_room, feast, gardens, mine,outpost, smithy, village, baron, great_hall);

        struct gameState G;
        struct gameState copyG;

	printf("------------------Begin Random Adventurer Card Test-----------\n");
	for(n = 0; n< 2000; n++){
	int players = 2 ;

	tester = initializeGame(players,k,seed,&G);
	printf("intializeGame(%d,k,%d,&G) = %d\n",players,seed, tester);
	if(tester != 0) printf("intiliazation failed\n");
	else printf("initialization success\n");

	//Randomly select player
	int currentPlayer = (int)rand()%players;
	G.whoseTurn = (int)currentPlayer;

	//Randomly adjust the deckCount, discardCount & hardCount of player.
	G.deckCount[currentPlayer] = rand() % MAX_DECK;
	G.discardCount[currentPlayer] = rand() % MAX_DECK;
	G.handCount[currentPlayer] = rand() % MAX_HAND + 1; //Guarantees at least 1 card

	//Fill Player's Hand
	int hand = 0;
	for(hand = 0; hand < G.handCount[currentPlayer]; hand++){
		drawCard(currentPlayer,&G);
	}

	//Ensure there is a card that is Adventurer
	int cardPos = rand() % G.handCount[currentPlayer];
	G.hand[currentPlayer][cardPos] = adventurer;
	
	copyG = G;
	int i;
	int oldTreasureCount = 0;
	int newTreasureCount = 0;
	int bonus = 0;
	tester = cardEffect(adventurer,0,0,0,&copyG,cardPos,&bonus);

	//Counts # of treasure cards in the hand to start
	for(i = 0; i < G.handCount[currentPlayer]; i++){
	
		if(G.hand[currentPlayer][i] == copper || G.hand[currentPlayer][i] == silver || 
			G.hand[currentPlayer][i] == gold){
			oldTreasureCount++;
		}
	}
	
	//Counts # of treasure cards in hand after played
	for(i = 0; i < copyG.handCount[currentPlayer]; i++){
		
		if(copyG.hand[currentPlayer][i] == copper || copyG.hand[currentPlayer][i] == silver || 
			copyG.hand[currentPlayer][i] == gold){
	
			newTreasureCount++;
		}
	}

	printf("Old Treasure Count = %d\n", oldTreasureCount);
	printf("New Treasure Count = %d\n", newTreasureCount);

	if(newTreasureCount != oldTreasureCount+2)
		printf("TEST FAILED\n");
	else 
		printf("TEST SUCCESS\n");
	
	int oldDiscardCount = G.discardCount[currentPlayer];
	int newDiscardCount = copyG.discardCount[currentPlayer];

	printf("Old Discard Count = %d\n",oldDiscardCount);
	printf("New Discard Count = %d\n",newDiscardCount);
	if(newDiscardCount != oldDiscardCount+2) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");

	//Count the adventurer played #
	int oldPlayed = 0;
	int newPlayed = 0;

	for(i =0; i < G.playedCardCount; i++){

		if(G.playedCards[i] == adventurer)
			oldPlayed++;

	} 

	for(i = 0; i< copyG.playedCardCount; i++){

		if(copyG.playedCards[i] == adventurer)
			newPlayed++;
	}

	printf("Old Played = %d\n",oldPlayed);
	printf("New Played = %d\n",newPlayed);

	if(newPlayed != oldPlayed+1)
		printf("TEST FAILED\n");
	else
		printf("TEST SUCCESS\n");

	int otherPlayer;

	if(currentPlayer == 0) otherPlayer = 1;
	else otherPlayer = 0;

	if(copyG.handCount[otherPlayer] != G.handCount[otherPlayer])
		printf("OTHER PLAYER CHANGED, TEST FAILED\n");
	else
		printf("OTHER PLAYER UNCHANGED, TEST SUCCESS\n");

	}
	return 0;
}
