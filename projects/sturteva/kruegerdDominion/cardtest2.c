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

	printf("--------------------Begin Outpost Card Test--------\n");
	tester = initializeGame(2,k,1,&G);
	printf("initializeGame(2,k,1,&G) = %d\n", tester);
	if(tester != 0) printf("initialization failed\n");
	else printf("initialization success\n");

	//Ensure that Outpost is in the hand
	G.hand[0][0] = outpost;
	copyG = G;
	tester = cardEffect(outpost,0,0,0,&copyG,0,&bonus);

	int oldOutpost = G.outpostPlayed;
	int newOutpost = copyG.outpostPlayed;

	printf("Old Outpost = %d\n",oldOutpost);
	printf("New Outpost = %d\n",newOutpost);
	if(newOutpost != oldOutpost+1) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");

	int oldPlayedCount = G.playedCardCount;
	int newPlayedCount = copyG.playedCardCount;
	int oldHandCount = G.handCount[0];
	int newHandCount = copyG.handCount[0];

	printf("Old Played Pile = %d\n", oldPlayedCount);
	printf("New Played Pile = %d\n", newPlayedCount);
	printf("Old Hand Count = %d\n", oldHandCount);
	printf("New Hand Count = %d\n",newHandCount);

	if(newPlayedCount != oldPlayedCount+1 && newHandCount != oldHandCount-1)
		 printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");

	 int oldPlayed = 0;
        int newPlayed = 0;
        int i;

        for(i = 0; i < G.playedCardCount; i++){

                if (G.playedCards[i] == outpost)
                        oldPlayed++;
        }

        for(i = 0; i < copyG.playedCardCount; i++){

                if(copyG.playedCards[i] == outpost)
                        newPlayed++;
        }

        printf("Old Played = %d\n",oldPlayed);
        printf("New Played = %d\n",newPlayed);
        if(newPlayed != oldPlayed+1)
                printf("TEST FAILED\n");
        else
                printf("TEST SUCCESS\n");



	if(copyG.handCount[1] != G.handCount[1])
		printf("OTHER PLAYER STATE CHANGED\n");
	else printf("OTHER PLAYER STATE, FINE\n");

return 0;
}

