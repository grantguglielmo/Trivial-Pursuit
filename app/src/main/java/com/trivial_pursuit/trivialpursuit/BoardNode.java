package com.trivial_pursuit.trivialpursuit;

import java.util.ArrayList;

/**
 * Created by grant on 11/29/2017.
 */

public class BoardNode {
    public Globs.Colors cat;
    public boolean wedge;
    public boolean center;
    public boolean rollAgain;
    public ArrayList<BoardNode> adjNode = new ArrayList<BoardNode>();

    public BoardNode(Globs.Colors c, boolean w, boolean ce, boolean r){
        cat = c;
        wedge = w;
        center = ce;
        rollAgain = r;
    }

    public BoardNode(Globs.Colors c, boolean w, boolean ce, boolean r, BoardNode prev){
        cat = c;
        wedge = w;
        center = ce;
        rollAgain = r;
        add(prev);
    }

    public void add(BoardNode prev){
        adjNode.add(prev);
        prev.adjNode.add(this);
    }

    public ArrayList<BoardNode> adjList(int distance, BoardNode prev){
        ArrayList<BoardNode> arr = new ArrayList<BoardNode>();
        if(distance == 0){
            arr.add(this);
            return arr;
        }

        for(BoardNode b : adjNode){
            if(b == prev){
                continue;
            }
            ArrayList<BoardNode> addArr = b.adjList(distance - 1, this);
            for (BoardNode c : addArr) {
                arr.add(c);
            }
        }
        return arr;
    }
}
