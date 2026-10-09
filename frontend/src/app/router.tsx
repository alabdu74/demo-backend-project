import { createBrowserRouter } from 'react-router'
import MainLayout from '../components/layout/MainLayout'
import { ProductsPage } from '../features/products'
import { OrdersPage } from '../features/orders'
import { LoginPage, RegisterPage } from '../features/auth'

export const router = createBrowserRouter([
  { path: '/login', element: <LoginPage /> },
  { path: '/register', element: <RegisterPage /> },
  {
    path: '/',
    element: <MainLayout />,
    children: [
      { index: true, element: <ProductsPage /> },
      { path: 'orders', element: <OrdersPage /> },
    ],
  },
])