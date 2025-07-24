"use client";
import { React } from "react";
import { handleLogout } from "@/app/login/actions";
import { useAuth } from "@/hooks/useAuth";

const Header = ({ token: serverToken }) => {
  const { token: clientToken, logout: clientLogout } = useAuth();

  // Usar el token del servidor si existe, sino el del cliente
  const token = serverToken || clientToken;
  console.log("[Header] Token servidor:", serverToken ? "SI" : "NO");
  console.log("[Header] Token cliente:", clientToken ? "SI" : "NO");
  console.log("[Header] Token final:", token ? "SI" : "NO");

  const salir = async () => {
    console.log("[Header] Iniciando logout...");
    try {
      // El server action maneja todo: eliminar cookies y redireccionar
      await handleLogout();
      console.log("[Header] Logout completado");
    } catch (error) {
      console.error("[Header] Error en logout:", error);
      // Si falla el servidor, al menos limpiar cliente y redirigir manualmente
      clientLogout();
      window.location.href = "/login";
    }
  };

  if (!token) {
    return null; // No mostrar header si no hay token
  }

  return (
    <header
      style={{
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        padding: "1rem 2rem",
        backgroundColor: "#1e3a8a",
        color: "#fff",
      }}
    >
      <h2 style={{ margin: 0 }}>Bienvenido al sistema Krugger</h2>
      <button
        onClick={salir}
        style={{
          backgroundColor: "#dc2626",
          color: "#fff",
          border: "none",
          padding: "0.5rem 1rem",
          borderRadius: "4px",
          cursor: "pointer",
        }}
      >
        Salir
      </button>
    </header>
  );
};

export default Header;
