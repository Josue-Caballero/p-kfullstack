"use server";
import { loginUser, logoutUser } from "@/infraestructure/login/authService";
import { redirect } from "next/navigation";

export async function handleLogin(formData) {
  console.log("[ServerAction] handleLogin iniciado");
  const username = formData.get("username");
  const password = formData.get("password");
  console.log("[ServerAction] Credenciales:", { username, password: "***" });

  try {
    const data = await loginUser({ username, password });
    console.log("[ServerAction] loginUser completado exitosamente");

    // Redirigir desde el servidor después de guardar la cookie
    console.log("[ServerAction] Redirigiendo a dashboard desde servidor");
    redirect("/dashboard");
  } catch (err) {
    console.error("[ServerAction] Error en loginUser:", err.message);
    return { success: false, error: "Usuario o contraseña incorrectos" };
  }
}

export async function handleLogout() {
  console.log("[ServerAction] handleLogout iniciado");
  await logoutUser();
  console.log("[ServerAction] handleLogout completado");
  redirect("/login");
}
