#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<time.h>

char inputChar()
{
    //Create a random character from the basic ascii table
    char ranChar = rand() % 127;
    return ranChar;
}

char *inputString()
{
    //create a string that is any lower-case character
    char* ranString;
    ranString = (char*)malloc(6 * sizeof(char));

    
    int i;
    for(i = 0; i < 5; i++){

	//To ensure that we hit the 5 minute mark
	//we will only randomly generate 'r','e','s','t'
        int randInt = rand() % 4 + 1;

	if (randInt == 1) ranString[i] = 'r';
	if (randInt == 2) ranString[i] = 'e';
	if (randInt == 3) ranString[i] = 's';
	if (randInt == 4) ranString[i] = 't';
    }

    //Guarantee the last character is a null character
    ranString[5] = '\0';


    return ranString;
}

void testme()
{
  int tcCount = 0;
  char *s;
  char c;
  int state = 0;
  while (1)
  {
    tcCount++;
    c = inputChar();
    s = inputString();
    printf("Iteration %d: c = %c, s = %s, state = %d\n", tcCount, c, s, state);

    if (c == '[' && state == 0) state = 1;
    if (c == '(' && state == 1) state = 2;
    if (c == '{' && state == 2) state = 3;
    if (c == ' '&& state == 3) state = 4;
    if (c == 'a' && state == 4) state = 5;
    if (c == 'x' && state == 5) state = 6;
    if (c == '}' && state == 6) state = 7;
    if (c == ')' && state == 7) state = 8;
    if (c == ']' && state == 8) state = 9;
    if (s[0] == 'r' && s[1] == 'e'
       && s[2] == 's' && s[3] == 'e'
       && s[4] == 't' && s[5] == '\0'
       && state == 9)
    {
      printf("error ");
      exit(200);
    }
  }
}


int main(int argc, char *argv[])
{
    srand(time(NULL));
    testme();
    return 0;
}
