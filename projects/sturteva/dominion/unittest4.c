#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"

#define DEBUG 0
#define NOISY_TEST 1

/*This is designed to test the isGameOver function)
 *  * */
int main(){

        int tester;
        int k[10] = {adventurer, council_room, feast, gardens, mine,
               remodel, smithy, village, baron, great_hall};

        struct gameState G;

	printf("------------------Begin whoseTurn TEST-------------\n");
	tester = whoseTurn(&G);
	printf("Pre-Intialization Test = %d\n", tester);
	if(tester != 0) printf ("TEST FAILED\n");
	else printf("TEST SUCCESS\n");

	tester = initializeGame(2,k,1,&G);
	printf("intializeGame(2,k,1,&G) = %d\n", tester);
	if(tester != 0) printf("intialization failed\n");
	else printf("initialization success\n");

	tester = whoseTurn(&G);
	printf("whoseTurn(&G) = %d\n", tester);
	if(tester != 0) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");

	printf("MANUALLY UPDATE G.whoseTurn = 4\n");
	G.whoseTurn = 4;
	tester = whoseTurn(&G);
	if(tester != 4) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");

	printf("MANUALLY UPDATE G.whoseTurn = -1\n");
	G.whoseTurn = -1;
	tester = whoseTurn(&G);
	if(tester != -1) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");

return 0;
}
