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
	
	int seed = (int)time(NULL);
	srand((unsigned)seed);
	int n;

        int tester;
        int bonus = 0;
        int k[10] = {adventurer, council_room, feast, gardens, mine,
               outpost, smithy, village, baron, great_hall};

        
        struct gameState copyG;

	printf("-------------------Begin Random Great Hall Card Test------\n");

	for(n = 0; n < 2000; n++){
		struct gameState G;
		int players = 2;
		tester = initializeGame(players,k,seed,&G);
		printf("initializeGame(%d,k,%d,&G) = %d\n", players,seed,tester);
	if(tester != 0) printf("initilization failed\n");
	else printf("initialization success\n");

	//Randomly select player
	int currentPlayer = (int)rand()% players;
	G.whoseTurn = (int)currentPlayer;


	//Randomly adjust deckCount,discardCout & handCount of player
	G.handCount[currentPlayer] = (rand() % MAX_HAND) + 1;
	G.deckCount[currentPlayer] = rand() % MAX_DECK - G.handCount[currentPlayer] + 1;
	G.discardCount[currentPlayer] = rand() % (MAX_DECK - G.deckCount[currentPlayer]);


	//Fill Player's Hand
	int hand = 0;
	for(hand = 0; hand < G.handCount[currentPlayer]; hand++){
		drawCard(currentPlayer,&G);
	}

	//Ensure there is a card that is Great Hall
	int cardPos = rand() % G.handCount[currentPlayer];
	G.hand[currentPlayer][cardPos] = great_hall;
	

	copyG = G;	

	tester = cardEffect(great_hall,0,0,0,&copyG,cardPos,&bonus);

	//Ensure that a Card was Drawn
	printf("Old Hand Size = %d\n",G.handCount[currentPlayer]);
	printf("New Hand Size = %d\n",copyG.handCount[currentPlayer]);
	if(copyG.handCount[currentPlayer] != G.handCount[currentPlayer])
		printf("TEST FAILED\n");
	else
		printf("TEST SUCCESS\n");

	//Count Great Hall Played
	//Ensure the Great Hall card was played 

	int oldPlayed = 0;
	int newPlayed = 0;
	int i;

	for(i = 0; i < G.playedCardCount; i++){

		if (G.playedCards[i] == great_hall)
			oldPlayed++;
	}

	for(i = 0; i < copyG.playedCardCount; i++){

		if(copyG.playedCards[i] == great_hall)
			newPlayed++;
	}

	printf("Old Played = %d\n",oldPlayed);
	printf("New Played = %d\n",newPlayed);
	if(newPlayed != oldPlayed+1)
		printf("TEST FAILED\n");
	else
		printf("TEST SUCCESS\n");
	

	//Ensure # of Actions increased
	printf("Old Actions = %d\n",G.numActions);
	printf("New Actions = %d\n",copyG.numActions);
	if(copyG.numActions != G.numActions+1)
		printf("TEST FAILED\n");
	else
		printf("TEST SUCCESS\n");

	int otherPlayer;

	if(currentPlayer == 0) otherPlayer = 1;
	else otherPlayer = 0;
	//Check that the other player state didn't change
	if(copyG.handCount[otherPlayer] != G.handCount[otherPlayer])
		printf("OTHER PLAYER STATE CHANGED, TEST FAILED\n");
	else
		printf("OTHER PLAYER STATE UNCHANGED, TEST SUCCESS\n");
	}
return 0;
}

	
