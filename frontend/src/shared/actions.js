"use server";
import Header from "./Header";
import { cookies } from "next/headers";

export default async function HeaderServer() {
  console.log("[HeaderServer] Iniciando HeaderServer...");

  const cookieStore = await cookies();
  console.log("[HeaderServer] CookieStore obtenido");

  // Listar TODAS las cookies disponibles
  const allCookies = cookieStore.getAll();
  console.log("[HeaderServer] Todas las cookies disponibles:", allCookies);

  const token = cookieStore.get("token")?.value || null;
  console.log(
    "[HeaderServer] Token específico:",
    token ? "TOKEN_ENCONTRADO" : "NULL"
  );

  if (token) {
    console.log("[HeaderServer] Token encontrado, longitud:", token.length);
  } else {
    console.log("[HeaderServer] ERROR: No se encontró el token");
  }

  return <Header token={token} />;
}
