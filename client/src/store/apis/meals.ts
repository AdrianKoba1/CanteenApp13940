// Need to use the React-specific entry point to import createApi
import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

export type Meal = {
  id: number;
  name: string;
  available: boolean;
  description: string;
  image: string;
  price: number;
  categoryId: number;
};

// Define a service using a base URL and expected endpoints
export const mealApi = createApi({
  reducerPath: "mealApi",
  baseQuery: fetchBaseQuery({
    baseUrl: "http://localhost:1234/api/menuItem/",
  }),
  endpoints: (builder) => ({
    getMeals: builder.query<Meal[], void>({
      query: () => `/getAll`,
    }),
  }),
});

export const { useGetMealsQuery } = mealApi;
