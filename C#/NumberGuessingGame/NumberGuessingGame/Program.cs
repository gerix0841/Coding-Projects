using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NumberGuessingGame
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Enter a max number: ");
            int max = Convert.ToInt32(Console.ReadLine());

            Random random = new Random();
            int nbm = random.Next(0, max);

            int guess;

            do
            {
                Console.Write("Enter a number between 0 and " + max + ": ");
                guess = Convert.ToInt32(Console.ReadLine());

                if(guess > nbm)
                {
                    Console.WriteLine("Too high");
                }
                else if(guess < nbm)
                {
                    Console.WriteLine("Too low");
                }
                else
                {
                    Console.WriteLine("Congrats!");
                    Console.WriteLine(guess + " = " + nbm);
                }
            } while(guess != nbm);
            Console.WriteLine("Thanks for playing");
            Console.ReadKey(true);
        }
    }
}
