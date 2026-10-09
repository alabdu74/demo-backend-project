import { NavLink } from 'react-router'

const linkClass = ({ isActive }: { isActive: boolean }) =>
  isActive
    ? 'text-brand-500 font-medium'
    : 'text-gray-300 hover:text-white transition'

export default function Navbar() {
  return (
    <nav className="flex items-center gap-6 px-6 py-4 bg-gray-900">
      <span className="text-white font-bold text-lg mr-4">MyApp</span>
      <NavLink to="/" className={linkClass}>
        Products
      </NavLink>
      <NavLink to="/orders" className={linkClass}>
        Orders
      </NavLink>
    </nav>
  )
}