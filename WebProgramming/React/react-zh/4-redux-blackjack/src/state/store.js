import { configureStore } from "@reduxjs/toolkit";
import { blackjackReducer } from "./blackjackSlice/blackjackReducer";

export const store = configureStore({
    reducer: blackjackReducer,
});