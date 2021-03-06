import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.Arrays;
import java.util.Scanner;

/**
 * After spending far too long trying to figure out how to properly read in
 * the matrix text file using the conditions outlined in the office hours
 * recording, I decided to just look for an example of somebody online reading
 * a text matrix and adapt it to my needs.
 * 
 * @author Arta
 *
 */
public class MatrixRead
    {

        String fileName;
        Scanner fileRead;

        public MatrixRead(String fileName) throws FileNotFoundException
            {
                // TODO Auto-generated constructor stub
                this.fileName = fileName;
                this.fileRead = new Scanner(
                        new BufferedReader(new FileReader(fileName)));
            }

        public MatrixRead()
            {
                // TODO Auto-generated constructor stub
            }

        /**
         * This method takes the number at the top of each matrix and sets
         * that as the dimensions (dims). Then, it creates an array based on
         * the dimensions, and sets the finally element to -1 as a marker of
         * incompleteness. It then interprets the matrix. Once it is done, it
         * looks for another dimension.
         * 
         * @param graph
         *            The graph that will be used to calculate paths
         * @return The integer value for the dimensions
         * @throws FileNotFoundException
         *             In the case that the file specified is not found
         */
        public int readMatrix(Graph graph) throws FileNotFoundException
            {
                int dims = 0;
                // May be redundant to have the hasNextLine function and
                // the ready method in main, but it's okay
                if (fileRead.hasNextLine())
                    {
                        dims = fileRead.nextInt();
                        System.out.println("Dimensions: " + dims);
                        fileRead.nextLine();
                        int rows = dims;
                        int columns = dims;
                        int[][] matrix = new int[rows][columns];
                        matrix[rows - 1][columns - 1] = -1;
                        while (matrix[rows - 1][columns - 1] == -1)
                            {
                                for (int i = 0; i < matrix.length; i++)
                                    {
                                        String[] line = fileRead.nextLine().trim()
                                                .split(" ");
                                        for (int j = 0; j < line.length; j++)
                                            {
                                                matrix[i][j] = Integer
                                                        .parseInt(line[j]);
                                            }
                                    }
                            }
                        System.out.println(Arrays.deepToString(matrix));
                        graph.setVertBankSize(dims);
                        exportVertex(matrix, graph);
                    }
                return dims;
            }

        /**
         * Generates vertices from the array, returns them to be stored in
         * main.
         * 
         * @param myArray
         *            2-D array from the matrix which represents the graph
         * 
         * @param graph
         *            The graph being passed from main that will be used for
         *            processing
         * 
         */
        public void exportVertex(int[][] myArray, Graph graph)
            {
                for (int i = 0; i < myArray.length; i++)
                    {
                        // Create a vertex with id of i and
                        // dimensions of myArray.length
                        Vertex vert = new Vertex(i, myArray.length);
                        vert.setEdges(myArray[i]);
                        graph.setVert(i, vert);
                    }
            }

        public boolean ready()
            {
                boolean ready = false;
                if (fileRead.hasNextLine())
                    {
                        ready = true;
                    }
                return ready;
            }

    }