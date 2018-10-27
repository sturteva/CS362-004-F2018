#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"

#define DEBUG 0
#define NOISY_TEST 1

/*This is designed to test the Smith card*/
int main(){

        int tester;
        int bonus = 0;
        int k[10] = {adventurer, council_room, feast, gardens, mine,
               outpost, smithy, village, baron, great_hall};

        struct gameState G;
        struct gameState copyG;

	printf("-------------------Begin Great Hall Card Test------\n");

	tester = initializeGame(2,k,1,&G);
	printf("initializeGame(2,k,1,&G) = %d\n", tester);
	if(tester != 0) printf("initilization failed\n");
	else printf("initialization success\n");

	//Ensure that the great_hall is in the hand
	G.hand[0][0] = great_hall;

	printf("%d\n",G.hand[0][0]);

	copyG = G;	
	printf("%d\n",copyG.hand[0][0]);
	tester = cardEffect(great_hall,0,0,0,&copyG,0,&bonus);
	printf("%d\n",copyG.hand[0][0]);
	//Ensure that a Card was Drawn
	printf("Old Hand Size = %d\n",G.handCount[0]);
	printf("New Hand Size = %d\n",copyG.handCount[0]);
	if(copyG.handCount[0] != G.handCount[0])
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

	
	//Check that the other player state didn't change
	if(copyG.handCount[1] != G.handCount[1])
		printf("OTHER PLAYER STATE CHANGED, TEST FAILED\n");
	else
		printf("OTHER PLAYER STATE UNCHANGED, TEST SUCCESS\n");

return 0;
}

	
