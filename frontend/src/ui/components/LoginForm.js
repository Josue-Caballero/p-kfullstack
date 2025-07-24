"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { handleLogin } from "@/app/login/actions";

export default function LoginForm() {
  const router = useRouter();
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  async function onSubmit(e) {
    console.log("[LoginForm] Iniciando submit...");
    e.preventDefault();
    setIsLoading(true);
    setError(null);

    const formData = new FormData(e.target);
    const username = formData.get("username");
    console.log("[LoginForm] Username:", username);

    try {
      console.log("[LoginForm] Llamando handleLogin...");
      await handleLogin(formData);
      console.log("[LoginForm] Login completado");
    } catch (err) {
      console.error("[LoginForm] Error en login:", err);
      if (err.message) {
        setError(err.message);
      } else {
        setError("Usuario o contraseña incorrectos");
      }
    } finally {
      setIsLoading(false);
    }
  }

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-50 via-white to-cyan-50 py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-md w-full space-y-8">
        {/* Logo/Header */}
        <div className="text-center">
          <div className="mx-auto h-20 w-20 bg-gradient-to-r from-blue-600 to-cyan-600 rounded-full flex items-center justify-center shadow-lg transform rotate-3 hover:rotate-0 transition-transform duration-300">
            <svg
              className="h-10 w-10 text-white"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z"
              />
            </svg>
          </div>
          <h2 className="mt-6 text-4xl font-bold text-gray-900 tracking-tight">
            Bienvenido
          </h2>
          <p className="mt-2 text-sm text-gray-600">
            Inicia sesión en tu cuenta para continuar
          </p>
        </div>

        {/* Card 3D */}
        <div className="bg-white/80 backdrop-blur-sm rounded-2xl shadow-2xl transform perspective-1000 hover:shadow-3xl transition-all duration-300 border border-white/20">
          <div className="px-8 py-10">
            {/* Ayuda para desarrollo */}
            <div className="mb-6 p-4 bg-gradient-to-r from-blue-50 to-cyan-50 border border-blue-200 rounded-xl">
              <p className="font-semibold text-blue-800 mb-2">
                Credenciales de prueba:
              </p>
              <div className="space-y-1">
                <p className="text-sm text-blue-600">
                  👤 Usuario: <span className="font-mono">admin</span>
                </p>
                <p className="text-sm text-blue-600">
                  🔑 Contraseña: <span className="font-mono">12345</span>
                </p>
              </div>
            </div>

            <form onSubmit={onSubmit} className="space-y-6">
              {error && (
                <div className="bg-red-50 border-l-4 border-red-400 p-4 rounded-lg animate-pulse">
                  <div className="flex">
                    <div className="flex-shrink-0">
                      <svg
                        className="h-5 w-5 text-red-400"
                        fill="none"
                        stroke="currentColor"
                        viewBox="0 0 24 24"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          strokeWidth="2"
                          d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                        />
                      </svg>
                    </div>
                    <div className="ml-3">
                      <p className="text-sm text-red-700">{error}</p>
                    </div>
                  </div>
                </div>
              )}

              <div className="space-y-5">
                <div className="group">
                  <label
                    htmlFor="username"
                    className="block text-sm font-medium text-gray-700 mb-2"
                  >
                    Usuario
                  </label>
                  <div className="relative">
                    <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                      <svg
                        className="h-5 w-5 text-gray-400 group-focus-within:text-blue-500 transition-colors"
                        fill="none"
                        stroke="currentColor"
                        viewBox="0 0 24 24"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          strokeWidth="2"
                          d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"
                        />
                      </svg>
                    </div>
                    <input
                      id="username"
                      name="username"
                      type="text"
                      required
                      disabled={isLoading}
                      className="appearance-none relative block w-full pl-10 pr-3 py-3 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent focus:z-10 sm:text-sm transition-all duration-200 bg-gray-50 focus:bg-white hover:bg-white shadow-inner disabled:opacity-50"
                      placeholder="Ingresa tu usuario"
                    />
                  </div>
                </div>

                <div className="group">
                  <label
                    htmlFor="password"
                    className="block text-sm font-medium text-gray-700 mb-2"
                  >
                    Contraseña
                  </label>
                  <div className="relative">
                    <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                      <svg
                        className="h-5 w-5 text-gray-400 group-focus-within:text-blue-500 transition-colors"
                        fill="none"
                        stroke="currentColor"
                        viewBox="0 0 24 24"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          strokeWidth="2"
                          d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z"
                        />
                      </svg>
                    </div>
                    <input
                      id="password"
                      name="password"
                      type="password"
                      required
                      disabled={isLoading}
                      className="appearance-none relative block w-full pl-10 pr-3 py-3 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent focus:z-10 sm:text-sm transition-all duration-200 bg-gray-50 focus:bg-white hover:bg-white shadow-inner disabled:opacity-50"
                      placeholder="Ingresa tu contraseña"
                    />
                  </div>
                </div>
              </div>

              <div>
                <button
                  type="submit"
                  disabled={isLoading}
                  className="group relative w-full flex justify-center py-3 px-4 border border-transparent text-sm font-medium rounded-xl text-white bg-gradient-to-r from-blue-600 to-cyan-600 hover:from-blue-700 hover:to-cyan-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 disabled:opacity-50 disabled:cursor-not-allowed transform transition-all duration-200 hover:scale-105 hover:shadow-xl active:scale-95"
                >
                  {isLoading ? (
                    <>
                      <svg
                        className="animate-spin -ml-1 mr-3 h-5 w-5 text-white"
                        fill="none"
                        viewBox="0 0 24 24"
                      >
                        <circle
                          className="opacity-25"
                          cx="12"
                          cy="12"
                          r="10"
                          stroke="currentColor"
                          strokeWidth="4"
                        ></circle>
                        <path
                          className="opacity-75"
                          fill="currentColor"
                          d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
                        ></path>
                      </svg>
                      Iniciando sesión...
                    </>
                  ) : (
                    <>
                      <span className="absolute left-0 inset-y-0 flex items-center pl-3">
                        <svg
                          className="h-5 w-5 text-white group-hover:text-blue-200 transition-colors"
                          fill="none"
                          stroke="currentColor"
                          viewBox="0 0 24 24"
                        >
                          <path
                            strokeLinecap="round"
                            strokeLinejoin="round"
                            strokeWidth="2"
                            d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1"
                          />
                        </svg>
                      </span>
                      Iniciar Sesión
                    </>
                  )}
                </button>
              </div>
            </form>
          </div>
        </div>

        {/* Footer */}
        <div className="text-center">
          <p className="text-xs text-gray-500">
            © 2025 Sistema de Gestión de Proyectos
          </p>
        </div>
      </div>
    </div>
  );
}
