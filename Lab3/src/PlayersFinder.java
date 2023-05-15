import java.awt.Point;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


interface IPlayersFinder {

    /**
     * Search for players locations at the given photo
     * @param photo
     *     Two dimension array of photo contents
     *     Will contain between 1 and 50 elements, inclusive
     * @param team
     *     Identifier of the team
     * @param threshold
     *     Minimum area for an element
     *     Will be between 1 and 10000, inclusive
     * @return
     *     Array of players locations of the given team
     */
    java.awt.Point[] findPlayers(String[] photo, int team, int threshold); 
}


public class PlayersFinder implements IPlayersFinder{
    /*
       Implement your class here
    */
    int[][] visited;
    int countCell = 0, right = 0, left = 0, up = 0, down = 0;
    
    @Override
    public Point[] findPlayers(String[] photo, int team, int threshold) {
        int countPlayers = 0; // number of bounding boxes
        int rows = photo.length;
        int columns = photo[0].length();
        char[][] grid= new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++){
                grid[i][j] = photo[i].charAt(j);
            }
        }
        visited = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++){
                visited[i][j] = 0;
            }
        }
        Point[] points = new Point[100];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(0, 0);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++){
                if (visited[i][j] != 1 && grid[i][j] == (char)(team + 48)){
                    right   = j;
                    left    = j;
                    up      = i;
                    down    = i;
                    markCurrentPlayer(grid, rows, columns, i, j, team);
                    if ((countCell*4) >= threshold){
                        points[countPlayers].x = ((2*right + 1) + (2*left + 1))/2;
                        points[countPlayers].y = ((2*up + 1) + (2*down + 1))/2;
                        countPlayers++;
                    }
                }
                countCell = 0;
                right = 0;
                left = 0;
                up = 0;
                down = 0;
            }
        }
        Point[] finalPoints = new Point[countPlayers];
        for(int i = 0; i < finalPoints.length; i++){
            finalPoints[i] = new Point(0, 0);
        }
        for(int i = 0; i < countPlayers; i++){
            finalPoints[i] = points[i];
        }
        Point temp = new Point();
        //sorting points ascinding x
        for(int i = 0; i < finalPoints.length ; i++){
            for(int j = i + 1; j < finalPoints.length; j++){
                if(finalPoints[i].x >= finalPoints[j].x){
                    temp = finalPoints[i];
                    finalPoints[i] = finalPoints[j];
                    finalPoints[j] = temp;
                }
                if(finalPoints[i].x == finalPoints[j].x){
                    if(finalPoints[i].y >= finalPoints[j].y){
                        temp = finalPoints[i];
                        finalPoints[i] = finalPoints[j];
                        finalPoints[j] = temp;
                    }  
                }
            }
        }
        return finalPoints;
    }
    private void markCurrentPlayer(char[][] grid, int rows, int columns, int i, int j, int team) {
        if (i >= 0 && j >= 0 && i < rows && j < columns && visited[i][j] != 1){ //check boundries
            if(grid[i][j] == (char)(team + 48)){
                visited[i][j] = 1;
                countCell++;
                if(j > right) right = j;
                if(j < left) left = j;
                if(i > down) down = i;
                if(i < up) up = i;
                markCurrentPlayer(grid, rows, columns, i+1, j, team);
                markCurrentPlayer(grid, rows, columns, i, j+1, team);
                markCurrentPlayer(grid, rows, columns, i-1, j, team);
                markCurrentPlayer(grid, rows, columns, i, j-1, team);
            }
        }                     
    }
    public static void main(String[] args) {
        /* Implement main method to parse the input from stdin and print output to stdout */
        Scanner sc1 = new Scanner(System.in);
        String sin1 = sc1.nextLine().replaceAll("", "");
        String[] s1 = sin1.split(", ");
        int rows = Integer.parseInt(s1[0]);
        int columns = Integer.parseInt(s1[1]);
        //Scanner sc2 = new Scanner(System.in);
        String[] s2 = new String[rows];
        for (int i = 0; i < rows; i++) {
            s2[i] = sc1.nextLine();
        }
        //Scanner sc3 = new Scanner(System.in);
        int teamID = sc1.nextInt();
        int threshold = sc1.nextInt();
        Point[] position = new PlayersFinder().findPlayers(s2, teamID, threshold);
        System.out.print("[");
        for(int i = 0; i < position.length; i++){
            System.out.print("(");
            System.out.print(position[i].x);
            System.out.print(", ");
            System.out.print(position[i].y);
            System.out.print(")");
            if(i != position.length - 1)
                System.out.print(", ");
        }
        System.out.print("]");
    }
}

