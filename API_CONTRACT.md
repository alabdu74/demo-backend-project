
**Base URL (local):** `http://localhost:8081`
**Swagger UI:** `http://localhost:8081/swagger-ui.html`
**OpenAPI spec:** `http://localhost:8081/v3/api-docs`

All requests and responses use **JSON**.
Content-Type: `application/json`

---

## 🔐 Authentication

### POST `/api/auth/register`
Create a new user (role defaults to `USER`).

**Request:**
```json
{
  "username": "alice",
  "password": "secret123"
}
Response 200:

json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
Errors:

400 — validation error

409 — username already exists

POST /api/auth/login
Authenticate and get a JWT.

Request:

json
{
  "username": "admin",
  "password": "admin123"
}
Response 200:

json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
Errors:

401 — invalid credentials

🔑 How to Authenticate Requests
For any protected endpoint, include the JWT in the header:

text
Authorization: Bearer <token>
Example:

text
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
Store the token in localStorage after login and attach it to every request via an axios interceptor (see example at the bottom).

📦 Products
GET /api/products — PUBLIC (no token)
List all products.

Response 200:

json
[
  {
    "id": 1,
    "name": "Laptop",
    "description": "14-inch ultrabook, 16GB RAM",
    "price": 999.99,
    "quantity": 10,
    "category": "ELECTRONICS",
    "createdAt": "2026-10-06T09:44:50.382013"
  }
]
Category enum values: ELECTRONICS, CLOTHING, FOOD, OTHER

GET /api/products/{id} — PUBLIC
Get a single product by ID.

Response 200: one product object (same shape as above)

Errors:

404 — product not found

POST /api/products — ADMIN ONLY
Create a new product.

Request:

json
{
  "name": "Wireless Keyboard",
  "description": "Compact Bluetooth keyboard",
  "price": 49.99,
  "quantity": 25,
  "category": "ELECTRONICS"
}
Response 200: the created product with id and createdAt

Errors:

400 — validation error

401 — missing/invalid token

403 — not an admin

PUT /api/products/{id} — ADMIN ONLY
Update an existing product. Same request body as POST.

Response 200: the updated product

Errors: 400, 401, 403, 404

DELETE /api/products/{id} — ADMIN ONLY
Delete a product.

Response 204: No Content

Errors: 401, 403, 404

👤 Users
GET /api/users/me — REQUIRES LOGIN
Get the currently authenticated user.

Response 200:

json
{
  "id": 1,
  "username": "admin",
  "role": "ADMIN"
}
Role enum values: ADMIN, USER

Errors:

401 — missing/invalid token

Use this after login to know if the user is an ADMIN and show/hide the admin menu.

🛒 Orders
POST /api/orders — REQUIRES LOGIN
Create a new order for the current user. Status starts as PENDING.

Request:

json
{
  "items": [
    { "productId": 1, "quantity": 2 },
    { "productId": 2, "quantity": 1 }
  ]
}
Response 200:

json
{
  "id": 1,
  "userId": 1,
  "username": "admin",
  "status": "PENDING",
  "total": 2029.97,
  "items": [
    {
      "productId": 1,
      "productName": "Laptop",
      "quantity": 2,
      "priceAtPurchase": 999.99,
      "subtotal": 1999.98
    },
    {
      "productId": 2,
      "productName": "Wireless Mouse",
      "quantity": 1,
      "priceAtPurchase": 29.99,
      "subtotal": 29.99
    }
  ],
  "createdAt": "2026-10-06T10:15:00",
  "updatedAt": "2026-10-06T10:15:00"
}
Status enum values: PENDING, PAID, SHIPPED, DELIVERED, CANCELLED

Errors:

400 — validation error (empty items, quantity < 1)

401 — missing/invalid token

404 — product not found

GET /api/orders/my — REQUIRES LOGIN
Get the current user's orders (newest first).

Response 200: array of order objects (same shape as POST response)

GET /api/orders/{id} — REQUIRES LOGIN
Get a single order by ID.

Response 200: one order object

Errors: 404

GET /api/orders — ADMIN ONLY
Get all orders in the system.

Response 200: array of all order objects

Errors:

401 — missing/invalid token

403 — not an admin

PUT /api/orders/{id}/status — ADMIN ONLY
Update an order's status.

Query parameter: status (required)

Example:

text
PUT /api/orders/1/status?status=SHIPPED
Response 200: the updated order

Errors: 400, 401, 403, 404

🛡️ Admin
GET /api/admin/users — ADMIN ONLY
List all users.

Response 200:

json
[
  { "id": 1, "username": "admin", "role": "ADMIN" },
  { "id": 2, "username": "user",  "role": "USER"  }
]
⚠️ Note: Password field may appear in the response. Ignore it on the frontend.

DELETE /api/admin/users/{id} — ADMIN ONLY
Delete a user.

Response 204: No Content

Errors: 401, 403, 404

GET /api/admin/dashboard — ADMIN ONLY
Returns a plain string: "Welcome to Admin Dashboard".

Errors: 401, 403

💳 Payments (ArifPay)
POST /api/payments/checkout — REQUIRES LOGIN
Create an ArifPay checkout session.

Query parameters:

productName (string, required)

amount (decimal, required)

Example:

text
POST /api/payments/checkout?productName=Laptop&amount=999.99
Response 200: ArifPay session object (contains checkout URL / session ID)

GET /api/payments/verify/{sessionId} — REQUIRES LOGIN
Verify payment status.

Response 200: payment status object

❌ Error Format
All error responses follow this shape:

json
{
  "timestamp": "2026-10-06T10:20:00",
  "status": 400,
  "error": "Bad Request",
  "message": "price must be positive",
  "path": "/api/products"
}
🌐 CORS
The backend allows requests from these origins:

http://localhost:3000 (React default)

http://localhost:5173 (Vite default)

http://localhost:4200 (Angular default)

If your dev server runs on a different port, tell the backend owner to add it.

🔌 Example Frontend Setup (Axios)
src/api/client.js
javascript
import axios from "axios";

const client = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || "http://localhost:8081/api",
});

// Attach JWT on every request
client.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

// Handle 401 by redirecting to login
client.interceptors.response.use(
  (res) => res,
  (err) => {
    if (err.response?.status === 401) {
      localStorage.removeItem("token");
      window.location.href = "/login";
    }
    return Promise.reject(err);
  }
);

export default client;
.env
text
VITE_API_BASE_URL=http://localhost:8081/api
Usage
javascript
import client from "../api/client";

// Public
const { data: products } = await client.get("/products");

// Login
const { data } = await client.post("/auth/login", { username, password });
localStorage.setItem("token", data.token);

// Current user
const { data: me } = await client.get("/users/me");

// Create order
const { data: order } = await client.post("/orders", {
  items: [{ productId: 1, quantity: 2 }],
});

// My orders
const { data: myOrders } = await client.get("/orders/my");
👤 Demo Accounts
Username	Password	Role
admin	admin123	ADMIN
user	user123	USER
Use these for testing.

✅ Endpoint Summary Table
Method	Endpoint	Auth	Role
POST	/api/auth/register	❌	—
POST	/api/auth/login	❌	—
GET	/api/products	❌	—
GET	/api/products/{id}	❌	—
POST	/api/products	✅	ADMIN
PUT	/api/products/{id}	✅	ADMIN
DELETE	/api/products/{id}	✅	ADMIN
GET	/api/users/me	✅	any
POST	/api/orders	✅	any
GET	/api/orders/my	✅	any
GET	/api/orders/{id}	✅	any
GET	/api/orders	✅	ADMIN
PUT	/api/orders/{id}/status	✅	ADMIN
GET	/api/admin/users	✅	ADMIN
DELETE	/api/admin/users/{id}	✅	ADMIN
GET	/api/admin/dashboard	✅	ADMIN
POST	/api/payments/checkout	✅	any
GET	/api/payments/verify/{sessionId}	✅	any
text

---

### 🎯 How to Add It to the Repo

Run this from your repo root:

```bash
cd ~/Desktop/demo2

cat > API_CONTRACT.md <<'ENDOFFILE'
(paste the whole contract above here)
ENDOFFILE

git add API_CONTRACT.md
git commit -m "docs: add API contract for frontend developer"
git push origin develop)
ENDOFFILE

git add API_CONTRACT.md
git commit -m "docs: add API contract for frontend developer"
git push origin develop