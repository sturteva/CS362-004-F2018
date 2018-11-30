#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"

#define DEBUG 0
#define NOISY_TEST 1

/*This is designed to test the supplyCount function after intilization)
*/
int main(){

	int tester;
	int k[10] = {adventurer, council_room, feast, gardens, mine,
	       remodel, smithy, village, baron, great_hall};

	struct gameState G;

	printf("-------------------BEGIN supplyCount TEST--------------\n");
	tester = initializeGame(2,k,1,&G);
	printf("initializeGame(2,k,1,&G) = %d\n",tester);
	if(tester != 0) printf("initialization failed\n");
	else printf("initialization success\n");

	/*Tests a non-Victory card was set to 10*/
	tester = supplyCount(adventurer, &G);
	printf("Adventurer Supply Count = %d\n",tester);
	if(tester != 10) printf("Adventurer Supply FAILED\n");
	else printf("Adventurer Supply Success\n");

	/*Tests a 'Victory' Kingdom card supply was set to 8*/
	tester = supplyCount(great_hall,&G);
	printf("Great_Hall Supply Count = %d\n", tester);
	if(tester != 8) printf("Great_Hall Supply FAILED\n");
	else printf("Great_Hall Supply Success\n");

	/*Tests a card not in the Kingdom Cards*/
	tester = supplyCount(cutpurse,&G);
	printf("cutpurse supply count = %d\n",tester);
	if(tester != -1) printf("cutpurse supply FAILED\n");
	else printf("Cutpurse Supply Success\n");

return 0;
}
