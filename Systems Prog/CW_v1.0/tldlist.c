/***********************************
 * AUTHORSHIP STATEMENT
 * NAME | UMAR IBRAHIM
 * GUID | 2366908I
 * This is my own work as defined in the 
 * Academic Ethics agreement I have signed
 ***************************************/
#include "date.h"
#include "tldlist.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

struct tldlist {
    Date *start;
    Date *end;

    TLDNode *root;
    
    long count;
};

struct tldnode {
    char tld[10];
    long node_count;

    long height;
    TLDNode *left_node;
    TLDNode *right_node;
};

struct tlditerator {
    int max_size;
    int top;
    TLDNode * stack;
};

/*************************
 *  HELPER FUNCTIONS BELOW
 *************************/
long count_nodes (TLDNode * root) {
    if (root == NULL) {
        return 0;
    }
    else {
        return 1 + count_nodes(root->left_node) + count_nodes(root->right_node);
    }
}

long find_max(long a, long b) {
    if (a > b) {
        return a;
    }
    else {
        return b;
    }
}

long height(TLDNode * node) { // For avl implementation. ignore for now.
    if (node == NULL) {
        return 0;
    }
    return node->height;
} 

TLDNode * lr(TLDNode* r) { // For avl implementation. ignore for now
    //TLDNode * right = (TLDNode *) malloc(sizeof(TLDNode));
    TLDNode * right = r->right_node; 
    TLDNode * right_left;
    if (right != NULL) {
        right_left = right->left_node;
    }
    if (right != NULL && right_left != NULL) {

        right->left_node = r;
        r->right_node = right_left;

        right->height = 1 + find_max(height(right->left_node), height(right->right_node));
        r->height = 1 + find_max(height(r->left_node), height(r->right_node));
        return right;
    }
    else {
        return r;
    }
}

TLDNode * rr(TLDNode* r) {
    TLDNode * left = r->left_node; // May be null
    TLDNode * left_right;
    if (left != NULL) {
        left_right = left->right_node;
    }
    
    if (left != NULL && left_right != NULL) {
        left->right_node = r;
        r->left_node = left_right;

        left->height = 1 + find_max(height(left->left_node), height(left->right_node));
        r->height = 1 + find_max(height(r->left_node), height(r->right_node));

        return left;
    }
    else {
        return r;
    }   
}

int calc_balance(TLDNode * node) {
    if (node == NULL) {
        return 0;
    }
    else {
        return (height(node->left_node) - height(node->right_node));
    }
}

TLDNode * make_node (char * tld) {
    TLDNode * new = (TLDNode *) malloc(sizeof(TLDNode));
    strcpy(new->tld, tld);
    new->left_node = NULL;
    new->right_node = NULL;
    new->height = 1;
    new->node_count = 1;

    return new;
}

TLDNode * tldlist_add_rec(TLDNode * root, char *new_tld) {
    if (root == NULL) { // If NULL, then make a new node and return
       return make_node(new_tld);
    }
    else if (strcmp(root->tld, new_tld) > 0) {
        root->left_node = tldlist_add_rec(root->left_node, new_tld);
    }
    else if (strcmp(root->tld, new_tld) < 0) {
        root->right_node = tldlist_add_rec(root->right_node, new_tld);
    }
    else { 
        // Must be equal
        root->node_count++;
    }
    root->height = 1 + find_max(height(root->left_node), height(root->right_node));

    int balance = calc_balance(root);
    
    // Left - left case
    
    if (balance > 1 && strcmp(new_tld, root->left_node->tld) < 0) {
        return rr(root);
    }
    // Right - Right
    if (balance < -1 && strcmp(new_tld, root->right_node->tld) > 0) {
        return lr(root);
    }

    // Left - right case
    if (balance > 1 && strcmp(new_tld, root->left_node->tld) > 0) { // We have a left-right case
        root->left_node = lr(root->left_node); // Perform a lr so we have a rr case.
        return rr(root);
    }
    
    // Right - left case
    if (balance < -1 && strcmp(new_tld, root->right_node->tld) < 0) { // We have a right left case
        root->right_node = rr(root->right_node); // Perform a rr so we have a lr case.
        return lr(root);
    }
    
    
    return root;
}

void push(TLDIterator * stack, TLDNode * node) {
    if (stack->top <= stack->max_size - 1) {
        stack->top++;
        stack->stack[stack->top] = *node;
    }
}

void preorder(TLDIterator * stack, TLDNode * root) { 
    if (root) {
        push(stack, root);  
        preorder(stack, root->left_node); 
        preorder(stack, root->right_node); 
    } 
}

void tldlist_destroy_rec(TLDNode * root) { // Recursion function
    if (!root) return;                      // To free nodes in our tree
    tldlist_destroy_rec(root->left_node);
    tldlist_destroy_rec(root->right_node);
    free(root);
}

/**
 * HEADER FUNCTIONS
 **/

TLDList *tldlist_create(Date *begin, Date *end) {
    TLDList *avl_tree = (TLDList *) malloc(sizeof(TLDList));
    avl_tree->start = begin;
    avl_tree->end = end;

    avl_tree->root = NULL;
    avl_tree->count = 0;


    return avl_tree;
};



void tldlist_destroy(TLDList *tld) {
    if (!tld) return; // Nothing exists
    tldlist_destroy_rec(tld->root); // Destroy nodes
    free(tld); // List is last one remaining
    
};

int tldlist_add(TLDList *tld, char *hostname, Date *d) {
    
    if (date_compare(d, tld->start) == 1 && date_compare(d, tld->end) == -1) { // If within the dates

        char *last_tld = strrchr(hostname, '.') + 1; // Take the last token delimited by '.', i.e: uk, com etc
        
        if (last_tld) {
            tld->root = tldlist_add_rec(tld->root, last_tld); // Recursion add function.
            
            tld->count++;
            return 1;
        }
        return 0;
    }
    return 0;
};

long tldlist_count(TLDList *tld) {
    return tld->count;
}

TLDIterator *tldlist_iter_create(TLDList *tld) {
    // General premise:
    // Create a stack that contains our tld list.
    // For the 'iter_next()' calls, simply point to the next index of our array. 
    
    TLDIterator * it = (TLDIterator *) malloc(sizeof(TLDIterator));
    it->max_size = count_nodes(tld->root);
    it->top = -1;
    //printf("%d\n", it->max_size);
    it->stack = (TLDNode *) malloc(it->max_size * sizeof(TLDNode));
    // Make stack
    preorder(it, tld->root);
    //printf("%s", tld->root->right_node->tld);
    return it;

}

TLDNode *tldlist_iter_next(TLDIterator *iter) {
    iter->top = iter->top - 1;
    if (iter->top >= -1) { // Check we're not accessing anything outside of our array
        return &(iter->stack[iter->top + 1]);
    }
    else {
        return NULL;
    }
}

void tldlist_iter_destroy(TLDIterator *iter) {
    free(iter->stack);
    free(iter);
}

char *tldnode_tldname(TLDNode *node) {
    return node->tld;
}

long tldnode_count(TLDNode *node) {
    return node->node_count;
}

