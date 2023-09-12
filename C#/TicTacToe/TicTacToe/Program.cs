using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TicTacToe
{
    class Program
    {
        static void printBoard(string[] points)
        {
            Console.WriteLine(points[0] + " | " + points[1] + " | " + points[2]);
            Console.WriteLine("---------");
            Console.WriteLine(points[3] + " | " + points[4] + " | " + points[5]);
            Console.WriteLine("---------");
            Console.WriteLine(points[6] + " | " + points[7] + " | " + points[8]);
        }

        static void Main(string[] args)
        {
            string[] points = {" ", " ", " ", " ", " ", " ", " ", " ", " " };

            int guessCount = 0;
            int side = 1;
            int coor = 0;

            printBoard(points);

            while (guessCount < 9)
            {
                if(side == 1)
                {
                    Console.Write("Player 1 enter coordinates:");
                    string coordinates = Console.ReadLine();
                    if(coordinates.Length == 2 && (coordinates[0] == 1 || coordinates[0] == 2 || coordinates[0] == 3) && (coordinates[1] == 1 || coordinates[1] == 2 || coordinates[1] == 3))
                    {
                        coor = Convert.ToInt32(coordinates);
                        if (coor == 11 && points[0] == " ")
                        {
                            points[0] = "X";
                            guessCount++;
                        }
                        else if (coor == 12 && points[1] == " ")
                        {
                            points[1] = "X";
                            guessCount++;
                        }
                        else if (coor == 13 && points[2] == " ")
                        {
                            points[2] = "X";
                            guessCount++;
                        }
                        else if (coor == 21 && points[3] == " ")
                        {
                            points[3] = "X";
                            guessCount++;
                        }
                        else if (coor == 22 && points[4] == " ")
                        {
                            points[4] = "X";
                            guessCount++;
                        }
                        else if (coor == 23 && points[5] == " ")
                        {
                            points[5] = "X";
                            guessCount++;
                        }
                        else if (coor == 31 && points[6] == " ")
                        {
                            points[6] = "X";
                            guessCount++;
                        }
                        else if (coor == 32 && points[7] == " ")
                        {
                            points[7] = "X";
                            guessCount++;
                        }
                        else if (coor == 33 && points[8] == " ")
                        {
                            points[8] = "X";
                            guessCount++;
                        }
                        side = 2;
                        printBoard(points);
                    }
                    else
                    {
                        Console.WriteLine("Wrong coordinates");
                    }
                }
                else
                {
                    Console.Write("Player 2 enter coordinates:");
                    string coordinates = Console.ReadLine();
                    if (coordinates.Length == 2 && (coordinates[0] == 1 || coordinates[0] == 2 || coordinates[0] == 3) && (coordinates[1] == 1 || coordinates[1] == 2 || coordinates[1] == 3))
                    {
                        coor = Convert.ToInt32(coordinates);
                        if (coor == 11 && points[0] == " ")
                        {
                            points[0] = "O";
                            guessCount++;
                        }
                        else if (coor == 12 && points[1] == " ")
                        {
                            points[1] = "O";
                            guessCount++;
                        }
                        else if (coor == 13 && points[2] == " ")
                        {
                            points[2] = "O";
                            guessCount++;
                        }
                        else if (coor == 21 && points[3] == " ")
                        {
                            points[3] = "O";
                            guessCount++;
                        }
                        else if (coor == 22 && points[4] == " ")
                        {
                            points[4] = "O";
                            guessCount++;
                        }
                        else if (coor == 23 && points[5] == " ")
                        {
                            points[5] = "O";
                            guessCount++;
                        }
                        else if (coor == 31 && points[6] == " ")
                        {
                            points[6] = "O";
                            guessCount++;
                        }
                        else if (coor == 32 && points[7] == " ")
                        {
                            points[7] = "O";
                            guessCount++;
                        }
                        else if (coor == 33 && points[8] == " ")
                        {
                            points[8] = "O";
                            guessCount++;
                        }
                        side = 1;
                        printBoard(points);
                    }
                    else
                    {
                        Console.WriteLine("Wrong coordinates");
                    }
                }
            }
            Console.ReadKey(true);
        }
    }
}
