package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.text.Position;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static int calcWidth (int y, int length){
        if (y < length) return (y + 1)* 2;
        else return (2 * length - y) * 2;
    }

    private static int calcOffsetX (int y, int length){
        if (y < length){
            return length - y - 1;
        }else{
            return y - length;
        }
    }

    private static void fillLine (TETile[][] world, Position start, int width, TETile T){
        for(int i = start.y; i < width; i++){
            world[start.x][i] = T;
        }
    }

    /** this method is used to draw a Hexagon at Position with length(length must be greater than 2) */
    public static void addHexagon(TETile[][] world, int length, Position p, TETile T){
        if (length < 2){
            //cant be less than 2, throw an exception plz
        }
        for(int i = 0; i < length * 2; i++){
            int thisY = p.y + i;
            int thisX = p.x + calcOffsetX(thisY, length);
            Position thisP = new Position(thisX,thisY);
            int width = calcWidth(thisY, length);
            fillLine(world, thisP, width, T);
        }

    }

    public static void drawTesselation(TETile[][] world, int length, Position p){
    }

    public static void main(int argc, String[] args){
        TERenderer test_re = new TERenderer();
        test_re.initialize(50,50);

        Position p = new Position(13,13);
        TETile[][] tset_world = new TETile[50][50];
        addHexagon(tset_world, 4, p, Tileset.GRASS );

        test_re.renderFrame(tset_world);
    }
}
