import { Login } from '@/components/login'
import { createLazyFileRoute } from '@tanstack/react-router'

export const Route = createLazyFileRoute('/login/')({
  component: Login,
})
