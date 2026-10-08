import client from './client';

export const login = async (credentials) => {
  const response = await client.post('/auth/login', credentials);

  localStorage.setItem('token', response.data.token);

  return response.data;
};

export const register = async (credentials) => {
  const response = await client.post('/auth/register', credentials);

  return response.data;
};

export const getCurrentUser = async () => {
  const response = await client.get('/users/me');

  return response.data;
};