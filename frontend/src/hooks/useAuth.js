"use client";
import { useState, useEffect } from "react";
import Cookies from "js-cookie";

export function useAuth() {
  const [token, setToken] = useState(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    // Leer token de las cookies al montar el componente
    const storedToken = Cookies.get("token");
    console.log(
      "[useAuth] Token del cliente:",
      storedToken ? "ENCONTRADO" : "NO_ENCONTRADO"
    );
    setToken(storedToken || null);
    setIsLoading(false);
  }, []);

  const logout = () => {
    console.log("[useAuth] Eliminando token del cliente");
    Cookies.remove("token", { path: "/" });
    setToken(null);
  };

  return {
    token,
    isAuthenticated: !!token,
    isLoading,
    logout,
  };
}
