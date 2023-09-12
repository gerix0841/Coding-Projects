using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RockPaperScissors
{
    class Program
    {
        static void Main(string[] args)
        {
            Random rnd = new Random();

            string bot,user;
            bool end = false;

            do
            {
                Console.Write("Enter rock,paper or scissors:");
                user = Console.ReadLine();
                int nmb = rnd.Next(1, 4);
                if (nmb == 1)
                {
                    bot = "rock";
                }
                else if (nmb == 2)
                {
                    bot = "paper";
                }
                else
                {
                    bot = "scissors";
                }

                if (user == bot)
                {
                    Console.WriteLine("Draw");
                    Console.WriteLine("You:" + user);
                    Console.WriteLine("Bot:" + bot);
                }
                else if((user == "rock" && bot == "paper") || (user == "paper" && bot == "scissors") || (user == "scissors" && bot == "rock"))
                {
                    Console.WriteLine("You lost!");
                    Console.WriteLine("You:" + user);
                    Console.WriteLine("Bot:" + bot);
                    end = true;
                }
                else
                {
                    Console.WriteLine("You won!");
                    Console.WriteLine("You:" + user);
                    Console.WriteLine("Bot:" + bot);
                    end = true;
                }
            } while(!end);
            Console.WriteLine("Thanks for playing!");
            Console.ReadKey(true);
        }
    }
}
