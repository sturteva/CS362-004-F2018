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
               remodel, smithy, village, baron, great_hall};

        struct gameState G;
	struct gameState copyG;

	printf("-------------Begin Smithy Card TEST----------\n");
	tester = initializeGame(2,k,1,&G);
	printf("intializeGame(2,k,1,&G) = %d\n", tester);
	if(tester != 0) printf("initialization failed\n");
	else printf("initialization success\n");

	//Ensure smithy is in hand
	G.hand[0][0] = smithy;

	copyG = G;
	tester = cardEffect(smithy,0,0,0,&copyG,0,&bonus);
	
	int discardPileDif = copyG.discardCount[0] - G.discardCount[0];
	printf("discard Pile Difference = %d\n", discardPileDif);
	if(discardPileDif != 1) printf("TEST FAILED -- LOOK FOR 1\n");
	else printf("TEST SUCCESS\n");

	int handsize = copyG.handCount[0] - G.handCount[0];
	printf("hand Size difference = %d\n", handsize);
	if(handsize != 2) printf("TEST FAILED -- LOOK FOR 2\n");
	else printf("TEST SUCCESS\n");


	int oldPlayed = 0;
        int newPlayed = 0;
        int i;

        for(i = 0; i < G.playedCardCount; i++){

                if (G.playedCards[i] == smithy)
                        oldPlayed++;
        }

        for(i = 0; i < copyG.playedCardCount; i++){

                if(copyG.playedCards[i] == smithy)
                        newPlayed++;
        }

        printf("Old Played = %d\n",oldPlayed);
        printf("New Played = %d\n",newPlayed);
        if(newPlayed != oldPlayed+1)
                printf("TEST FAILED\n");
        else
                printf("TEST SUCCESS\n");




	if(copyG.handCount[1] != G.handCount[1] && copyG.discardCount[1] != G.discardCount[1])
		printf("OTHER PLAYER STATE CHANGED!\n");

	return 0;
}
	
	

