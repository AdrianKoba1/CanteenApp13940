import { Box, Button, ButtonGroup, Typography } from "@mui/material";
import { Cart } from "./components/Cart";
import { useSelector } from "react-redux";
import type { RootState } from "@/store";
import type { Item } from "@/store/features/cart/cartSlice";

export const CartPage = () => {
  const items = useSelector((state: RootState) => state.cart.items);
  const cartItems: Item[] = Object.values(items);
  const hasItems = !!cartItems.length;
  console.log(items);
  return (
    <Box
      sx={{
        padding: "40px",
        display: "flex",
        flexDirection: "column",
        gap: "16px",
      }}
    >
      <Box sx={{ display: "flex", justifyContent: "space-between" }}>
        <Typography variant="h3">Your Cart</Typography>
        {hasItems && <Typography variant="h3">Total: $500</Typography>}
      </Box>
      <Box>
        {hasItems ? (
          <>
            {cartItems.map((item) => (
              <Cart data={item.item} />
            ))}
          </>
        ) : (
          <Typography
            variant="h5"
            sx={{ fontStyle: "italic", textAlign: "center" }}
          >
            Your cart is empty.
          </Typography>
        )}
        {hasItems && (
          <Box
            justifyContent={"flex-end"}
            sx={{ display: "flex", width: "100%" }}
          >
            <ButtonGroup
              disableElevation
              variant="contained"
              aria-label="Disabled button group"
              size="large"
            >
              <Button color="error">Clear</Button>
              <Button>Order</Button>
            </ButtonGroup>
          </Box>
        )}
      </Box>
    </Box>
  );
};
