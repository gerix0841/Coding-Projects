/* eslint-disable no-unused-vars */

import { createSlice } from "@reduxjs/toolkit"

const initialState = {
    deck: [],
    hands: {
       player: [],
       dealer: []
    },
    isGameStarted: false,
    isPlayerRoundDone: false,
    roundWinner: null,
    winnings: {
       player: 0,
       dealer: 0
    }
}

const blackjackSlice = createSlice({
    name: 'blackjack',
    initialState,
    reducers: {
        newGame: (state,{payload}) => {
            state.deck = payload.deck;
            state.hands = payload.hands;
            state.isGameStarted = payload.isGameStarted;
            state.isPlayerRoundDone = payload.isPlayerRoundDone;
            state.roundWinner = payload.roundWinner;
            state.winnings = payload.winnings;
        },
        nextCard: (state,{payload: index}) => {
            state.hands.player.push(state.deck[state.deck.length-1]);
        },
        playerFinished: (state) => {
            state.isPlayerRoundDone = true
        },
        dealerRound: (state,{payload: index}) => {
            
        }
    }
})

// reducer
export const blackjackReducer = blackjackSlice.reducer;

// actions
export const { newGame, nextCard, playerFinished, dealerRound } = blackjackSlice.actions;

// selectors


