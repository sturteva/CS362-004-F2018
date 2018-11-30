#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"

#define DEBUG 0
#define NOISY_TEST 1

/*This is designed to test the isGameOver function)
 * */
int main(){

        int tester;
        int k[10] = {adventurer, council_room, feast, gardens, mine,
               remodel, smithy, village, baron, great_hall};

        struct gameState G;

        printf("----------------BEGIN isGameOver TEST--------\n");
	tester = isGameOver(&G);
	printf("Pre-Intialization Test = %d\n",tester);
	if(tester != 1) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");

	tester = initializeGame(2,k,1,&G);
	printf("initializeGame(2,k,1,&G) = %d\n", tester);
        if(tester != 0) printf("initialization failed\n");
        else printf("intialization success\n");

	tester = isGameOver(&G);
	printf("isGameOver(&G) after Intialization = %d\n", tester);
	if(tester != 0) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");
	

	printf("set G.supplyCount[province] = 0\n");
	G.supplyCount[province] = 0;
	tester = supplyCount(province, &G);
	if(tester != 0) printf("UPDATE FAILED\n");
	else printf("UPDATE SUCCESS\n");

	tester = isGameOver(&G);
	printf("Zero Province isGameOver Test = %d\n",tester);
	if(tester != 1) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");

	printf("reset G.supplyCount[province] = 8\n");
	G.supplyCount[province] = 8;
        tester = supplyCount(province, &G);
        if(tester != 8) printf("UPDATE FAILED\n");
        else printf("UPDATE SUCCESS\n");

	printf("Set G.supplyCount[remodel] = 0\n");
	G.supplyCount[remodel] = 0;
	tester = supplyCount(remodel, &G);
	if(tester != 0) printf("UPDATE FAILED\n");
	else printf("UPDATE SUCCESS\n");
	
	printf("Set G.supplyCount[mine] = 0\n");
	G.supplyCount[mine] = 0;
	tester = supplyCount(mine, &G);
	if(tester != 0) printf("UPDATE FAILED\n");
	else printf("UPDATE SUCCESS\n");

	tester = isGameOver(&G);
	printf("isGameOver(&G) after 2 supply piles are Zero = %d\n",tester);
	if (tester != 0) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n");

	printf("Set G.supplyCount[adventurer] = 0\n");
	G.supplyCount[adventurer] = 0;
	tester = supplyCount(adventurer, &G);
	if(tester != 0) printf("UPDATE FAILED\n");
	else printf("UPDATE SUCCESS\n");

	tester = isGameOver(&G);
	printf("isGameOver(&G) after 3 supply piles are Zero = %d\n",tester);
	if(tester != 1) printf("TEST FAILED\n");
	else printf("TEST SUCCESS\n"); 


return 0;
}
