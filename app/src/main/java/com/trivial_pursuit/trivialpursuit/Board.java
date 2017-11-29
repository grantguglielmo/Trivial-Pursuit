package com.trivial_pursuit.trivialpursuit;

import java.util.ArrayList;

import static com.trivial_pursuit.trivialpursuit.Globs.Colors.BLUE;
import static com.trivial_pursuit.trivialpursuit.Globs.Colors.GREEN;
import static com.trivial_pursuit.trivialpursuit.Globs.Colors.ORANGE;
import static com.trivial_pursuit.trivialpursuit.Globs.Colors.PINK;
import static com.trivial_pursuit.trivialpursuit.Globs.Colors.PURPLE;
import static com.trivial_pursuit.trivialpursuit.Globs.Colors.YELLOW;

/**
 * Created by grant on 11/29/2017.
 */

public class Board {
    public BoardNode centerNode;
    public ArrayList<BoardNode> allNodes = new ArrayList<BoardNode>();

    public Board(){
        BoardNode p;
        BoardNode n;

        BoardNode ye;
        BoardNode gr;
        BoardNode pu;
        BoardNode pi;
        BoardNode bl;
        BoardNode or;

        //Center Node
        centerNode = new BoardNode(BLUE, false, true, false);
        p = centerNode;
        allNodes.add(p);

        //Left-Middle Spoke
        p = n = new BoardNode(PINK, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(PURPLE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(GREEN, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(ORANGE, false, false, false, p);
        allNodes.add(p);
        ye = p = n = new BoardNode(YELLOW, true, false, false, p);
        allNodes.add(p);

        //Y-P Circle
        p = n = new BoardNode(ORANGE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, true, p);
        allNodes.add(p);
        p = n = new BoardNode(GREEN, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(PINK, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, true, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, false, p);
        allNodes.add(p);
        pu = p = n = new BoardNode(PURPLE, true, false, false, p);
        allNodes.add(p);

        //P-G Circle
        p = n = new BoardNode(BLUE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, true, p);
        allNodes.add(p);
        p = n = new BoardNode(ORANGE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(YELLOW, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, true, p);
        allNodes.add(p);
        p = n = new BoardNode(PINK, false, false, false, p);
        allNodes.add(p);
        gr = p = n = new BoardNode(GREEN, true, false, false, p);
        allNodes.add(p);

        //G-O Circle
        p = n = new BoardNode(PINK, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, true, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(PURPLE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, true, p);
        allNodes.add(p);
        p = n = new BoardNode(YELLOW, false, false, false, p);
        allNodes.add(p);
        or = p = n = new BoardNode(ORANGE, true, false, false, p);
        allNodes.add(p);

        //O-B Circle
        p = n = new BoardNode(YELLOW, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, true, p);
        allNodes.add(p);
        p = n = new BoardNode(PINK, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(GREEN, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, true, p);
        allNodes.add(p);
        p = n = new BoardNode(PURPLE, false, false, false, p);
        allNodes.add(p);
        bl = p = n = new BoardNode(BLUE, true, false, false, p);
        allNodes.add(p);

        //B-P Circle
        p = n = new BoardNode(PURPLE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, true, p);
        allNodes.add(p);
        p = n = new BoardNode(YELLOW, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(ORANGE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, true, p);
        allNodes.add(p);
        p = n = new BoardNode(GREEN, false, false, false, p);
        allNodes.add(p);
        pi = p = n = new BoardNode(PINK, true, false, false, p);
        allNodes.add(p);

        //P-Y Circle
        p = n = new BoardNode(GREEN, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, true, p);
        allNodes.add(p);
        p = n = new BoardNode(PURPLE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, true, p);
        allNodes.add(p);
        p = n = new BoardNode(ORANGE, false, false, false, p);
        allNodes.add(p);
        ye.add(p);

        //Left-Top Spoke
        p = centerNode;
        p = n = new BoardNode(YELLOW, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(GREEN, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(ORANGE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(PINK, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, false, p);
        allNodes.add(p);
        pu.add(p);

        //Right-Top Spoke
        p = centerNode;
        p = n = new BoardNode(PURPLE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(ORANGE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(YELLOW, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(PINK, false, false, false, p);
        allNodes.add(p);
        gr.add(p);

        //Right-Middle Spoke
        p = centerNode;
        p = n = new BoardNode(GREEN, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(BLUE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(PINK, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(PURPLE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(YELLOW, false, false, false, p);
        allNodes.add(p);
        or.add(p);

        //Right-Bottom Spoke
        p = centerNode;
        p = n = new BoardNode(ORANGE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(PINK, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(YELLOW, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(GREEN, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(PURPLE, false, false, false, p);
        allNodes.add(p);
        bl.add(p);

        //Left-Bottom Spoke
        p = centerNode;
        p = n = new BoardNode(BLUE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(YELLOW, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(PURPLE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(ORANGE, false, false, false, p);
        allNodes.add(p);
        p = n = new BoardNode(GREEN, false, false, false, p);
        allNodes.add(p);
        pi.add(p);
    }
}
