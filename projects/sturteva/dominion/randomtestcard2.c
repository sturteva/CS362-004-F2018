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

	int seed = (int) time(NULL);
	srand((unsigned) seed);
        int tester;
	int bonus = 0;
        int k[10] = {adventurer, council_room, feast, gardens, mine,
               remodel, smithy, village, baron, great_hall};

        struct gameState G;
	struct gameState copyG;

	printf("-------------Begin Random Smithy Card TEST----------\n");
	int n;
	for(n = 0; n < 2000; n++){
	tester = initializeGame(2,k,seed,&G);
	printf("intializeGame(2,k,%d,&G) = %d\n", seed,tester);
	if(tester != 0) printf("initialization failed\n");
	else printf("initialization success\n");

	//Randomly select player
	int currentPlayer = (int) rand() % 2;
	G.whoseTurn = currentPlayer;

	//Randomly adjust the deckCount, discardCount & handCount of player
	int count = rand() % MAX_DECK;
//	G.discardCount[currentPlayer] = rand() % MAX_DECK;
	G.handCount[currentPlayer] = rand() % MAX_HAND + 1;

	//Fill Player's Deck
	int deck; 
	for(deck = 0; deck < G.deckCount[currentPlayer]; deck++){
		//Place random card into deck
		G.deck[currentPlayer][deck] = rand() % 27; 
	}
	
	//Fill Player's Hand
	int hand;
	for(hand = 0; hand < count; hand++){
		drawCard(currentPlayer, &G);
	}
	//Ensure smithy is in hand
	int cardPos = rand() % G.handCount[currentPlayer];
	G.hand[currentPlayer][cardPos] = smithy;

	copyG = G;
	tester = cardEffect(smithy,0,0,0,&copyG,cardPos,&bonus);
	
	int discardPileDif = copyG.discardCount[currentPlayer] - G.discardCount[currentPlayer];
	printf("discard Pile Difference = %d\n", discardPileDif);
	if(discardPileDif != 1) printf("TEST FAILED -- LOOK FOR 1\n");
	else printf("TEST SUCCESS\n");

	int handsize = copyG.handCount[currentPlayer] - G.handCount[currentPlayer];
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

	int otherPlayer;
	if(currentPlayer == 1) otherPlayer = 0;
	else otherPlayer = 1;


	if(copyG.handCount[otherPlayer] != G.handCount[otherPlayer] && copyG.discardCount[otherPlayer] != G.discardCount[otherPlayer])
		printf("OTHER PLAYER STATE CHANGED!\n");
	}
	return 0;
}
	
	

