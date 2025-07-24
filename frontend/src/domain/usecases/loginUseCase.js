// domain/usecases/loginUseCase.js
import { loginUser, logoutUser } from "@/infraestructure/login/authService";

export async function login(username, password) {
  return await loginUser({ username, password });
}

export async function logout() {
  await logoutUser();
}
