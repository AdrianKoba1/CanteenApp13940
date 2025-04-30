import type { Meal } from "@/store/apis/meals";
import {
  Box,
  Card,
  CardActionArea,
  CardActions,
  CardContent,
  CardMedia,
  Typography,
} from "@mui/material";
import type { FC } from "react";

interface CartProps {
  data: Meal;
}

export const Cart: FC<CartProps> = ({ data }) => (
  <Card
    sx={{
      height: "300px",
      display: "flex",
      backgroundColor: "#592E83",
      margin: "8px",
    }}
  >
    <CardActionArea
      sx={{
        display: "flex",
        height: "100%",
        flexGrow: 1,
        alignItems: "start",
        justifyContent: "start",
      }}
    >
      <CardMedia
        sx={{
          width: "15%",
          height: "100%",
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
        <Typography gutterBottom variant="h4">
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
          variant="h6"
        >
          {data.description}
        </Typography>
      </CardContent>
    </CardActionArea>
    <CardActions
      sx={{
        display: "flex",
        alignItems: "start",
        justifyContent: "space-between",
        paddingX: "16px",
      }}
    >
      <Box
        sx={{
          display: "flex",
          gap: "16px",
          color: "#CAA8F5",
        }}
      >
        <Typography variant="h4">2x</Typography>
        <Typography variant="h4">${data.price}</Typography>
      </Box>
    </CardActions>
  </Card>
);
