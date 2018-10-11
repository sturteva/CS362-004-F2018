// Added Support for Compiling on Windows Environment
#ifdef __unix__  // or #ifdef linux
#endif
#if defined(_WIN32)  // or #ifdef _WIN32
#define _CRT_SECURE_NO_WARNINGS   // dominion.h is not the first file in the other files so you may have to duplicate this define
#endif
#include "rngs.h"
#include <stdio.h>
#include <stdlib.h> 

int main(int argc, char** argv) {
  if (argc < 3) {
    printf ("Not enough inputs:  seed target\n");
  }

  SelectStream(1);
  PutSeed((long)atoi(argv[1]));
  
  int done = 0;
  int c = 1000000000;
  
  while (!done) {
    c = floor(Random() * 1000000000);
    //    if (c % 100000 == 0) {
    //      printf ("c = %d\n", c);
    //    }
    if (c == atoi(argv[2])) {
      printf ("Found the bug!\n");
      done = 1;
    }
  }
}

