import java.io.*;
import java.util.*;

/****************************
*
* COMP251 template file
*
* Assignment 1, Question 2
*
*****************************/


public class DisjointSets {

    private int[] par;
    private int[] rank;
    
    /* contructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSets(int n) {
        if (n>0) {
            par = new int[n];
            rank = new int[n];
            for (int i=0; i<this.par.length; i++) {
                par[i] = i;
            }
        }
    }
    
    public String toString(){
        int pari,countsets=0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i=0; i<this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari]==null) {
                setstrings[pari] = String.valueOf(i);
                countsets+=1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i=0; i<this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }
    
    /* find representative of element i */
    public int find(int i) {
        //followed pseudocode in slides
        //check if i is the root of the tree
        if (this.par[i] == i){
            //if so, i is the representative, so return it
            return i;
        }
        //otherwise, implement path compression and return the result
        else {
            this.par[i] = this.find(par[i]);
            return this.par[i];
        }
        
    }

    /* merge sets containing elements i and j */
    public int union(int i, int j) {
        //find the representative of each disjoint set
        int prevRepI = this.find(i);
        int prevRepJ = this.find(j);

        //check if the rank of i is less than the rank of j
        if (this.rank[prevRepI] < this.rank[prevRepJ]){
            //if so, merge i into j
            this.par[prevRepI] = prevRepJ;
            return prevRepI;
        }
        //check if the rank of j is less than the rank of i
        else if (this.rank[prevRepJ] < this.rank[prevRepI]){
            //if so, merge j into i
            this.par[prevRepJ] = prevRepI;
            return prevRepJ;
        }
        //otherwise, they have the same rank, so as per instructions, merge i into j
        else {
            this.par[prevRepI] = prevRepJ;
            //update the rank of j
            this.rank[prevRepJ]++;
            return prevRepJ;
        }

    }
    
    public static void main(String[] args) {
        
        DisjointSets myset = new DisjointSets(6);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 1");
        myset.union(2,1);
        System.out.println(myset);
        System.out.println("-> Union 4 and 5");
        myset.union(4,5);
        System.out.println(myset);
        System.out.println("-> Union 3 and 1");
        myset.union(3,1);
        System.out.println(myset);
        System.out.println("-> Union 2 and 4");
        myset.union(2,4);
        System.out.println(myset);
        
    }

}
