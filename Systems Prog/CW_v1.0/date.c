/***********************************
 * AUTHORSHIP STATEMENT
 * NAME | UMAR IBRAHIM
 * GUID | 2366908I
 * This is my own work as defined in the 
 * Academic Ethics agreement I have signed
 ***************************************/

#include <string.h>
#include <stdio.h> 
#include <stdlib.h> 
#include <stdbool.h>
#include "date.h"

struct date {
    int year;
    int month;
    int day;
};

Date *date_create(char *datestr) {

    Date *date = malloc(sizeof(Date));
    char * token = strtok(datestr, "/");
    /*
    * int aux variable allows us to keep track of how many
    * assignments have been made, and which assignment to make.
    *  0 := day
    *  1 := month
    *  2 := year
    */ 
    int aux = 0; 
    while (token != NULL) {
        if (aux == 0) {
            date->day = atoi(token);
        }
        else if (aux == 1) {
            date->month = atoi(token);
        }
        else if (aux == 2) {
            date->year = atoi(token);
        }
        token = strtok(NULL, "/");
        aux++;
    }
     return date; // Returns pointer
};

Date *date_duplicate(Date *d) {
    Date *duplicate = (Date *) malloc(sizeof(Date));
    *duplicate = *d;
    return duplicate;  // location of our to-be duplicate
};

int date_compare(Date *date1, Date *date2) {
    /*
    * General premise: 
    * Start with the year, check if either date1 or date 2 
    * is superior. If neither, then be elimination must be 
    * equal. Thus, move to month and so forth.
    * If we arrive at the day and neither is greater than the other
    * then by elimination we have they are equal.
    */
    if (date1->year > date2->year) {
        return 1;
    }
    else if (date1->year < date2->year) {
        return -1;
    }
    else if (date1->month > date2->month) {
        return 1;
    }
    else if (date1->month < date2->month) {
        return -1;
    }
    else if (date1->day > date2->day) {
        return 1;
    }
    else if (date1->day < date2->day) {
        return -1;
    }
    else {
        return 0;
    }
}

void date_destroy(Date *d) {
    if (d) {
        free(d);
    }
};