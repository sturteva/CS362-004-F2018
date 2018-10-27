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

	printf("------------------Begin Adventurer Card Test-----------\n");
	tester = initializeGame(2,k,1,&G);
	printf("intializeGame(2,k,1,&G) = %d\n", tester);
	if(tester != 0) printf("intiliazation failed\n");
	else printf("initialization success\n");

	copyG = G;
	tester = cardEffect(adventurer,0,0,0,&copyG,0,&bonus);

	int i;
	int oldTreasureCount = 0;
	int newTreasureCount = 0;

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
	if(oldDiscardCount == newDiscardCount) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");

	//Count the adventurer discard #
	int oldAdvDisCount = 0;
	int newAdvDisCount = 0;

	for(i = 0; i < G.discardCount[0]; i++){
		if(G.discard[0][i] == adventurer) oldAdvDisCount++;
	}

	for(i =0; i < copyG.discardCount[0]; i++){
		if(copyG.discard[0][i] == adventurer) newAdvDisCount++;
	}

	int difference = newAdvDisCount - oldAdvDisCount;
	printf("Old Adventurer Discard Count = %d\n",oldAdvDisCount);
	printf("New Adventurer Discard Count = %d\n",newAdvDisCount);
	if(difference < 1) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n"); 

	if(copyG.handCount[0] != G.handCount[0]+1) 
		printf("HAND SIZE OFF, TEST FAILED\n");
	else
		printf("HAND SIZE ADJUSTED CORRECTLY, TEST SUCCESS\n");


	if(copyG.handCount[1] != G.handCount[1])
		printf("OTHER PLAYER CHANGED, TEST FAILED\n");
	else
		printf("OTHER PLAYER UNCHANGED, TEST SUCCESS\n");

	
	return 0;
}
