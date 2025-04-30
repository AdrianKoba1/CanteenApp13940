import type { Meal } from "@/store/apis/meals";
import { createSlice } from "@reduxjs/toolkit";
import type { PayloadAction } from "@reduxjs/toolkit";

export interface Item {
  count: number;
  item: Meal;
}

interface Items {
  [id: number]: Item;
}

export interface CartState {
  items: Items;
}

const initialState: CartState = {
  items: {},
};

export const cartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {
    add: (state, action: PayloadAction<{ id: number; item: Meal }>) => {
      const id = action.payload.id;
      const item = state.items[id] ?? { count: 0, item: action.payload.item };
      const count = item.count ?? 0;

      item.count = count + 1;
      state.items[id] = item;
    },
    remove: (state, action: PayloadAction<{ id: number }>) => {
      const id = action.payload.id;
      if (state.items[id]) {
        const count = state.items[id].count ?? 0;
        if (count > 0) {
          state.items[id].count = count - 1;
        } else {
          delete state.items[id];
        }
      }
    },
    // New clearCart action
    clearCart: (state) => {
      state.items = {}; // Clear all items in the cart
    },
  },
});

export const { add, remove, clearCart } = cartSlice.actions;

export default cartSlice.reducer;
