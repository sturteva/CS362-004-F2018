#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"

#define DEBUG 0
#define NOISY_TEST 1

/*This is designed to test the fullDeckCount function)
*/
int main(){

        int tester;
        int k[10] = {adventurer, council_room, feast, gardens, mine,
               remodel, smithy, village, baron, great_hall};

        struct gameState G;

	printf("----------------BEGIN fullDeckCount TEST--------\n");
	tester = fullDeckCount(1,adventurer, &G);
	printf("Pre-Intilization fullDeckCount, adventurer, &G) = %d\n",tester);
	if(tester != 0) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");

	tester = initializeGame(2,k,1,&G);
	printf("initializeGame(2,k,1,&G) = %d\n", tester);
	if(tester != 0) printf("initialization failed\n");
	else printf("intialization success\n");

	/*At start of a game, only player 1 will have cards in deck until 
	 *the other players have a chance to draw.*/

	tester = fullDeckCount(1,copper,&G);
	printf("fullDeckCount(1,copper,&G) = %d\n", tester);
	if (tester != 7) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");
	
	tester = fullDeckCount(1,estate,&G);
	printf("fullDeckCount(1,estate,&G) = %d\n", tester);
	if (tester != 3) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");

	tester = fullDeckCount(1,adventurer,&G);
	printf("fullDeckCount(1, adventurer, &G) = %d\n", tester);
	if (tester != 0) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");

	/*Manually Adjust the hand of the player to ensure more coverage*/
	int add = 3;
	int deckCount = G.deckCount[1];
	int handCount = G.handCount[1];
	int discardCount = G.discardCount[1];
	int i;

	printf("MANUAL STATE ADJUSTMENTS\n");

	for(i = deckCount; i < deckCount + add; i++){
		G.deckCount[1]++;
		G.deck[1][i] = adventurer;
	}

	for(i = handCount; i < handCount + add; i++){
		G.handCount[1]++;
		G.hand[1][i] = adventurer;		
	}

	for(i = discardCount; i < discardCount + add; i++){
		G.discardCount[1]++;
		G.discard[1][i] = adventurer;
	}
	

	tester = fullDeckCount(1,adventurer,&G);
	printf("fulldeckCount(1,adventurer,&G) = %d\n", tester);
	if(tester != (add*3)) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");
	
	 
return 0;
}
