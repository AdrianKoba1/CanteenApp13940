import type { RootState } from "@/store";
import {
  Badge,
  Box,
  Divider,
  List,
  ListItem,
  ListItemButton,
  ListItemText,
  Typography,
} from "@mui/material";
import { Link, useRouterState } from "@tanstack/react-router";
import { useSelector } from "react-redux";

const Header = () => {
  const routerState = useRouterState();
  const currentPath = routerState.location.pathname;
  const items = useSelector((state: RootState) => state.cart.items);
  const cartItemCount = Object.values(items).reduce(
    (acc, curr) => acc + curr.count,
    0
  );

  // Check if the current path is the login page
  if (currentPath === "/") {
    return null; // Don't render the header on the login page
  }

  const navigation = [
    {
      name: "menu",
      path: "/menu",
    },
    {
      name: "cart",
      path: "/cart",
    },
    {
      name: "orders",
      path: "/orders",
    },
    {
      name: "profile",
      path: "/profile",
    },
  ];

  return (
    <Box
      sx={{
        zIndex: 5,
        display: "flex",
        paddingX: "14px",
        boxShadow: 3,
        position: "fixed",
        width: "100vw",
        height: "70px",
        backgroundColor: "#230C33",
      }}
    >
      <Typography variant="h6" sx={{ my: 2, flexGrow: 1 }}>
        QuickBite
      </Typography>
      <Divider />
      <List sx={{ display: "flex", gap: "4px" }}>
        {navigation.map(({ name, path }) => (
          <ListItem key={name} disablePadding>
            <Badge
              showZero={true}
              invisible={name !== "cart"}
              key={name}
              badgeContent={cartItemCount}
              color="secondary"
            >
              <ListItemButton
                selected={currentPath == path}
                sx={{ textAlign: "center" }}
                component={Link}
                to={path}
              >
                <ListItemText primary={name.toUpperCase()} />
              </ListItemButton>
            </Badge>
          </ListItem>
        ))}
      </List>
    </Box>
  );
};

export default Header;
