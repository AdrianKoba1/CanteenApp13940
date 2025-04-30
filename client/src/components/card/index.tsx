import type { Meal } from "@/store/apis/meals";
import {
  Card as MuiCard,
  CardActionArea,
  CardContent,
  CardMedia,
  Typography,
  CardActions,
  Box,
  IconButton,
} from "@mui/material";
import type { FC } from "react";

import { add, remove } from "@/store/features/cart/cartSlice";

import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";
import type { RootState } from "@/store";
import { useDispatch, useSelector } from "react-redux";

interface CardProps {
  data: Meal;
}

const Card: FC<CardProps> = ({ data }) => {
  const items = useSelector((state: RootState) => state.cart.items);
  const dispatch = useDispatch();
  const id = Number(data.id);

  const addItem = () => dispatch(add({ id, item: data }));
  const removeItem = () => dispatch(remove({ id }));

  return (
    <MuiCard
      sx={{
        width: "320px",
        height: "400px",
        display: "inline-block",
        backgroundColor: "#592E83",
        margin: "8px",
      }}
    >
      <CardActionArea>
        <CardMedia
          sx={{
            width: "100%",
            height: "190px",
            transition: "transform 0.3s ease-in-out",
            "&:hover": {
              transform: "scale(1.2)",
            },
          }}
          component="img"
          height="140"
          image={data.image}
          alt="meal"
        />
        <CardContent>
          <Typography gutterBottom variant="h5">
            {data.name}
          </Typography>
          <Typography
            sx={{
              height: "80px",
              overflow: "hidden",
              textOverflow: "ellipsis",
              display: "-webkit-box",
              WebkitLineClamp: "4",
              WebkitBoxOrient: "vertical",
            }}
            variant="body2"
          >
            {data.description}
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions
        sx={{
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
          paddingX: "16px",
        }}
      >
        <Typography color="#CAA8F5">${data.price}</Typography>

        {(items[id]?.count ?? 0) > 0 ? (
          <Box sx={{ display: "flex", alignItems: "center", gap: "4px" }}>
            <IconButton onClick={removeItem} size="small">
              <RemoveIcon />
            </IconButton>
            <Typography>{items[id]?.count}</Typography>
            <IconButton size="small" onClick={addItem}>
              <AddIcon />
            </IconButton>
          </Box>
        ) : (
          <IconButton
            onClick={addItem}
            size="small"
            sx={{ bgcolor: "#CAA8F5" }}
          >
            <AddIcon />
          </IconButton>
        )}
      </CardActions>
    </MuiCard>
  );
};

export default Card;
