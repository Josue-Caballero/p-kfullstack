import { cookies } from "next/headers";

export async function loginUser({ username, password }) {
  console.log("[AuthService] Iniciando loginUser...");
  const baseUrl = process.env.NEXT_PUBLIC_API_BASE_URL;
  console.log("[AuthService] URL BASE:", baseUrl);

  const res = await fetch(`${baseUrl}/auth/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ username, password }),
  });

  if (!res.ok) {
    console.log("[AuthService] Error en login:", res.status, res.statusText);
    const errorText = await res.text();
    console.log("[AuthService] Error response:", errorText);
    throw new Error("Credenciales inválidas");
  }

  const data = await res.json();
  console.log("[AuthService] Respuesta completa del servidor:", data);

  const cookieStore = await cookies();
  console.log("[AuthService] Obteniendo cookieStore...");

  // Tu API retorna expiration_time como timestamp, pero parece estar mal
  // Usar siempre 24 horas por defecto para evitar problemas
  console.log(
    "[AuthService] Expiration time del servidor:",
    data.expiration_time
  );
  const expirationDate = new Date(Date.now() + 24 * 60 * 60 * 1000); // 24 horas

  console.log("[AuthService] Fecha de expiración calculada:", expirationDate);

  // Guardar token en cookie
  cookieStore.set("token", data.token, {
    path: "/",
    expires: expirationDate,
    httpOnly: false, // Para acceso desde cliente
    secure: process.env.NODE_ENV === "production",
    sameSite: "lax",
  });

  // También guardar refresh_token si existe
  if (data.refresh_token) {
    cookieStore.set("refresh_token", data.refresh_token, {
      path: "/",
      expires: expirationDate,
      httpOnly: true, // Más seguro para refresh token
      secure: process.env.NODE_ENV === "production",
      sameSite: "lax",
    });
  }

  console.log("[AuthService] Cookies guardadas exitosamente");

  // Verificar que se guardó
  const tokenCheck = cookieStore.get("token")?.value;
  console.log(
    "[AuthService] Verificación token:",
    tokenCheck ? "GUARDADO" : "ERROR"
  );

  return data;
}

export async function logoutUser() {
  console.log("[AuthService] Iniciando logout...");
  const cookieStore = await cookies();

  // Eliminar ambos tokens
  cookieStore.delete("token");
  cookieStore.delete("refresh_token");

  console.log("[AuthService] Cookies eliminadas");
}
