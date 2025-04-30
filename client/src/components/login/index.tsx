// src/components/Login.tsx
import React, { useState } from "react";
import {
  Box,
  Card,
  CardContent,
  TextField,
  Button,
  Typography,
  Container,
  Avatar,
  FormControlLabel,
  Checkbox,
  useTheme,
  useMediaQuery,
} from "@mui/material";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import { useRouter } from "@tanstack/react-router"; // <-- Import useRouter from TanStack Router

export const Login: React.FC = () => {
  const [creds, setCreds] = useState({
    username: "",
    password: "",
    remember: false,
  });
  const [error, setError] = useState<string | null>(null);
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down("sm"));
  const { navigate } = useRouter(); // <-- Use the navigate method from TanStack Router

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value, checked, type } = e.target;
    setCreds({
      ...creds,
      [name]: type === "checkbox" ? checked : value,
    });
    setError(null);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const { username, password } = creds;
    if (!username || !password) {
      setError("Both fields are required");
      return;
    }
    console.log("Logging in with:", creds);

    // Dummy login success — navigate to the menu (root)
    navigate({ to: "/menu" }); // Navigate to '/menu' after login
  };

  return (
    <Box
      sx={{
        minHeight: "100vh",
        background: "linear-gradient(135deg, #230C33 0%, #230C33 100%)",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        p: 2,
      }}
    >
      <Container maxWidth="sm">
        <Card
          elevation={10}
          sx={{
            borderRadius: 4,
            backgroundColor: "rgba(255, 255, 255, 0.06)",
            backdropFilter: "blur(10px)",
          }}
        >
          <CardContent sx={{ p: isMobile ? 4 : 6 }}>
            <Box sx={{ textAlign: "center", mb: 4 }}>
              <Avatar
                sx={{
                  m: "0 auto",
                  bgcolor: "#B080EF",
                  width: 72,
                  height: 72,
                }}
              >
                <LockOutlinedIcon sx={{ fontSize: 40, color: "#ffffff" }} />
              </Avatar>
              <Typography
                variant="h3"
                component="h1"
                sx={{ mt: 2, fontWeight: "bold", color: "#B080EF" }}
              >
                Welcome to QuickBite
              </Typography>
              <Typography variant="h6" sx={{ mt: 1, color: "#C7C7C7" }}>
                Please login to your account
              </Typography>
            </Box>

            {error && (
              <Typography color="error" align="center" sx={{ mb: 2 }}>
                {error}
              </Typography>
            )}

            <Box
              component="form"
              onSubmit={handleSubmit}
              sx={{ display: "flex", flexDirection: "column", gap: 3 }}
            >
              {/* Username Field */}
              <Box>
                <Typography
                  variant="subtitle1"
                  sx={{ mb: 1, fontWeight: 600, color: "#ffffff" }}
                >
                  Username
                </Typography>
                <TextField
                  name="username"
                  value={creds.username}
                  onChange={handleChange}
                  variant="outlined"
                  size="medium"
                  fullWidth
                  placeholder="Enter your username"
                  InputProps={{ style: { backgroundColor: "#ffffff" } }}
                />
              </Box>

              {/* Password Field */}
              <Box>
                <Typography
                  variant="subtitle1"
                  sx={{ mb: 1, fontWeight: 600, color: "#ffffff" }}
                >
                  Password
                </Typography>
                <TextField
                  name="password"
                  type="password"
                  value={creds.password}
                  onChange={handleChange}
                  variant="outlined"
                  size="medium"
                  fullWidth
                  placeholder="Enter your password"
                  InputProps={{ style: { backgroundColor: "#ffffff" } }}
                />
              </Box>

              {/* Keep Me Logged In */}
              <Box>
                <Typography
                  variant="subtitle1"
                  sx={{ mb: 1, fontWeight: 600, color: "#ffffff" }}
                >
                  Options
                </Typography>
                <FormControlLabel
                  control={
                    <Checkbox
                      name="remember"
                      checked={creds.remember}
                      onChange={handleChange}
                      sx={{
                        color: "#B080EF",
                        "&.Mui-checked": { color: "#B080EF" },
                      }}
                    />
                  }
                  label="Keep me logged in"
                />
              </Box>

              {/* Submit Button */}
              <Button
                type="submit"
                variant="contained"
                size="large"
                fullWidth
                sx={{
                  py: 1.5,
                  fontSize: "1.2rem",
                  fontWeight: 600,
                  backgroundColor: "#B080EF",
                  "&:hover": { backgroundColor: "#A16EE5" },
                }}
              >
                Log In
              </Button>
            </Box>
          </CardContent>
        </Card>
      </Container>
    </Box>
  );
};
