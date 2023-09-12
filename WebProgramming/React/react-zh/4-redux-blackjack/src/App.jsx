import { shuffle } from 'lodash';
import './App.css'
import Hand from './view/Hand';
import { newGame, nextCard, playerFinished, dealerRound } from "./state/blackjackSlice";
import { useDispatch } from "react-redux";

function App() {
  const handOfDealer = [0, 1, 2, 3];
  const handOfPlayer = [38, 13];
  const isPlayerDone = true;
  const winner = 'Player';

  const oldDeck = [];

  for(let i = 0;i < 52;i++){
    oldDeck[i] = i;
  }

  const deck = shuffle(oldDeck);
  console.log(deck);

  const handleNewGame = () => {
    newGame({
      deck: deck,
      hands: {
        player: [],
        dealer: []
      },
      isGameStarted: true,
      isPlayerRoundDone: false,
      roundWinner: null,
      winnings: {
        player: 0,
        dealer: 0
     }
    });
  };

  return (
    <div className='container'>
      <h1>Simple Blackjack</h1>
      <h2>Dealer (0):</h2>
      <Hand cards={handOfDealer}></Hand>
      <button disabled={!isPlayerDone}>Play Dealer</button>
      <h2>Player (1):</h2>
      <Hand cards={handOfPlayer}></Hand>
      <button disabled={isPlayerDone}>Get New Card</button>
      <button disabled={isPlayerDone}>Stop</button>
      <button onClick={handleNewGame}>New Game</button>
      {winner && (<p className='result'>{winner} won!</p>)}
    </div>
  )
}

export default App
