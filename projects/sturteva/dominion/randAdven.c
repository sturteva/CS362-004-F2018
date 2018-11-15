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
	int players = rand()%4 + 1;
	tester = initializeGame(players,k,seed,&G);
	printf("intializeGame(%d,k,%d,&G) = %d\n",players,seed, tester);
	if(tester != 0) printf("intiliazation failed\n");
	else printf("initialization success\n");

	//Randomly select player
	int currentPlayer = rand()%players;

	//Randomly adjust the deckCount, discardCount & hardCount of player.
	G.deckCount[currentPlayer] = rand() % MAX_DECK;
	G.discardCount[currentPlayer] = rand() % MAX_DECK;
	G.handCount[currentPlayer] = rand() % MAX_HAND + 1; //Guarantees at least 1 card

	
	//ensure that the adventuerr card is in the hand
	G.hand[currentPlayer][0] = adventurer;
	
	copyG = G;
	int i;
	int oldTreasureCount = 0;
	int newTreasureCount = 0;
	int bonus = 0;
	tester = cardEffect(adventurer,0,0,0,&copyG,0,&bonus);

	//Counts # of treasure cards in the hand to start
	for(i = 0; i < G.handCount[0]; i++){
	
		if(G.hand[0][i] == copper || G.hand[0][i] == silver || 
			G.hand[0][i] == gold){
			oldTreasureCount++;
		}
	}
	
	//Counts # of treasure cards in hand after played
	for(i = 0; i < copyG.handCount[0]; i++){
		
		if(copyG.hand[0][i] == copper || copyG.hand[0][i] == silver || 
			copyG.hand[0][i] == gold){
	
			newTreasureCount++;
		}
	}

	printf("Old Treasure Count = %d\n", oldTreasureCount);
	printf("New Treasure Count = %d\n", newTreasureCount);

	if(newTreasureCount != oldTreasureCount+2)
		printf("TEST FAILED\n");
	else 
		printf("TEST SUCCESS\n");
	
	int oldDiscardCount = G.discardCount[0];
	int newDiscardCount = copyG.discardCount[0];

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


	if(copyG.handCount[1] != G.handCount[1])
		printf("OTHER PLAYER CHANGED, TEST FAILED\n");
	else
		printf("OTHER PLAYER UNCHANGED, TEST SUCCESS\n");

	}
	return 0;
}
