import Menu from "@/features/menu";
import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/menu')({
  component: Menu,
})


