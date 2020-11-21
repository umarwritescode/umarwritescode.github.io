#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {

    struct node {
        int x;
        char * y;
    } typedef node;

    node * arr = (node *) malloc(sizeof(node));
    
    node * arr2 = (node *) malloc(sizeof(node));

    arr2->x = 5;
    arr2->y = "s";

    *arr = *arr2;

    printf("%d", arr->x);

    free(arr2);

    printf("%d", arr->x);

    char name[5];

    name[0] = 's';
    name[1] = 'a';
    name[2] = 'm';
    printf("%s", name);

    if (name[0] == 's') {
        printf("true\n");
    }

    char * test = "teststring";

    char * no_str = "50";

    int to_no;

    to_no = atoi(no_str);
    printf("\n%d\n", to_no);

    char * domain = "www.google.co.uk";

    char *last_tld = strrchr(domain, '.');

    char tld[10];

    if (last_tld != NULL) {
        printf("%s", last_tld+1);
        strcpy(tld, last_tld+1);
    }

    printf("%s\n", tld);

    node * a_test = malloc(sizeof(node));

    a_test->x = 0;
    a_test->y = "x";

    a_test->x += 1;
    a_test->x += 1;

    printf("%d\n\n\n", (a_test->x));

    char a[2];
    char *c = (char *) malloc(sizeof(char));
    c = "ok";


    char doma[10];
    doma[0] = 'u';
    doma[1] = 'k';

    char *str = "com";

    if (strcmp(doma, str) == 0) {
        printf("true");
    }


    node * random = NULL;

    random = malloc(sizeof(node));

    if (random == NULL) {
        printf("it is null");
    }

    else {
        printf("it is not null");
    }



    char *date = "date";

    char *tst = NULL;

    tst = date;

    node * randoo = NULL;

    randoo->x = 5;
    printf("%d", randoo->x);


    return 0;


}


/*
int tldlist_search(TLDNode * root, char *new_tld) {
    if (!root) return 0;
    if (strcmp(root->tld, new_tld) == 0) {
        root->node_count += 1;
        return 1;
    }
    else if ((strcmp(root->tld, new_tld)) > 0) {
        return tldlist_search(root->left_node, new_tld);
    }
    else {
        return tldlist_search(root->right_node, new_tld);
    }
    return 0;
}

bool tldlist_check(TLDNode * root, char *new_tld) {
    if (root == NULL) return false;
    if (strcmp(root->tld, new_tld) == 0) {
        return true;
    }
    else if (strcmp(root->tld, new_tld) == 1) {
        return tldlist_check(root->left_node, new_tld);
    }
    else
    {
        return tldlist_check(root->right_node, new_tld);
    }
    return false;
}
*/