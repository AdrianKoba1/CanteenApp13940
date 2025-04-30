import Header from "@/components/header";
import { store } from "@/store";
import { createTheme, ThemeProvider } from "@mui/material";
import { Outlet, createRootRoute } from "@tanstack/react-router";
import { TanStackRouterDevtools } from "@tanstack/react-router-devtools";

import { Provider } from "react-redux";

const theme = createTheme({
  typography: {
    allVariants: {
      color: "#fff",
    },
  },
  components: {
    MuiListItemButton: {
      styleOverrides: {
        root: {
          "&.Mui-selected": {
            color: "white",
            backgroundColor: "#441763",
          },
          "&:hover": {
            backgroundColor: "#441763",
          },
        },
      },
    },
    MuiIconButton: {
      styleOverrides: {
        root: {
          "&:hover": {
            backgroundColor: "#B080EF",
          },
        },
      },
    },
  },
});

export const Route = createRootRoute({
  component: () => (
    <Provider store={store}>
      <ThemeProvider theme={theme}>
        <div className="bg-[#230C33]">
          <Header />
          <div className="pt-[70px] min-h-[100vh]">
            <Outlet />
          </div>
          <TanStackRouterDevtools />
        </div>
      </ThemeProvider>
    </Provider>
  ),
});
